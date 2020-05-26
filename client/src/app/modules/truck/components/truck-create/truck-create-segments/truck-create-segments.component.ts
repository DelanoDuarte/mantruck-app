import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';
import { concat, Observable, of, Subject } from 'rxjs';
import { catchError, distinctUntilChanged, switchMap, tap } from 'rxjs/operators';
import { Segment } from 'src/app/models/Segment';
import { SegmentService } from 'src/app/services/segment.service';

@Component({
  selector: 'app-truck-create-segments',
  templateUrl: './truck-create-segments.component.html',
  styleUrls: ['./truck-create-segments.component.css']
})
export class TruckCreateSegmentsComponent implements OnInit {

  @Input()
  segments: Segment[];

  segmentsAsync$: Observable<Segment[]>;
  segmentInput$ = new Subject<string>();
  segmentLoading = false;

  @Output()
  segmentsSelected = new EventEmitter<Segment[]>();

  constructor(private segmentService: SegmentService) { }

  ngOnInit(): void {
    if (this.segments) {
      this.segmentsAsync$ = of(this.segments);
    }
    this.findSegments();
  }

  trackByDescription(item: Segment) {
    return item.id;
  }

  emitSegmentsSelected(segments: any[]) {
    this.segmentsSelected.emit(segments);
  }

  private findSegments() {
    this.segmentsAsync$ = concat(
      of([]), // default items
      this.segmentInput$.pipe(
        distinctUntilChanged(),
        tap(() => this.segmentLoading = true),
        switchMap(term => this.segmentService.findAllByDescriptionTerm(term).pipe(
          catchError(() => of([])), // empty list on error
          tap(() => this.segmentLoading = false)
        ))
      )
    );
  }
}
