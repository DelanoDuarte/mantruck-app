import {
  AfterViewChecked, ChangeDetectorRef, Component,
  EventEmitter, Input, OnInit, Output, OnChanges, SimpleChanges
} from '@angular/core';
import { concat, Observable, of, Subject } from 'rxjs';
import { catchError, distinctUntilChanged, switchMap, tap } from 'rxjs/operators';
import { Segment } from 'src/app/models/Segment';
import { SegmentService } from 'src/app/services/segment.service';

@Component({
  selector: 'app-truck-create-segments',
  templateUrl: './truck-create-segments.component.html',
  styleUrls: ['./truck-create-segments.component.css']
})
export class TruckCreateSegmentsComponent implements OnInit, OnChanges {

  @Input()
  segments: Segment[];

  segmentsAsync$: Observable<Segment[]>;
  segmentInput$ = new Subject<string>();
  segmentLoading = false;

  @Output()
  segmentsSelected = new EventEmitter<Segment[]>();

  selectedSegments: Segment[] = [];

  constructor(private segmentService: SegmentService, private cdRef: ChangeDetectorRef) { }

  ngOnInit(): void {
    this.findSegments();
  }

  ngOnChanges(changes: SimpleChanges) {
    if (changes.segments.currentValue !== changes.segments.previousValue) {
      this.selectedSegments = this.segments;
      this.cdRef.detectChanges();
    }
  }

  trackByDescription(item: Segment) {
    return item.id;
  }

  emitSegmentsSelected(segments: any[]) {
    console.log(segments);
    this.segmentsSelected.emit(segments);
    this.selectedSegments = segments;
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
