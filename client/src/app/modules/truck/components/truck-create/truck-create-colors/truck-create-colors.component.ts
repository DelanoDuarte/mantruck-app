import { Component, OnInit, Input, Output, EventEmitter, ChangeDetectorRef, OnChanges, SimpleChanges } from '@angular/core';
import { Color } from 'src/app/models/Color';
import { Observable, Subject, concat, of } from 'rxjs';
import { ColorService } from 'src/app/services/color.service';
import { distinctUntilChanged, tap, switchMap, catchError } from 'rxjs/operators';

@Component({
  selector: 'app-truck-create-colors',
  templateUrl: './truck-create-colors.component.html',
  styleUrls: ['./truck-create-colors.component.css']
})
export class TruckCreateColorsComponent implements OnInit, OnChanges {

  @Input()
  colors: Color[];

  colorsAsync$: Observable<Color[]>;
  colorInput$ = new Subject<string>();
  colorLoading = false;

  @Output()
  colorsSelected = new EventEmitter<Color[]>();

  selectedColors: Color[] = [];

  constructor(private colorService: ColorService, private cdRef: ChangeDetectorRef) { }

  ngOnInit(): void {
    this.findSegments();
  }

  ngOnChanges(changes: SimpleChanges) {
    if (changes.colors.currentValue !== changes.colors.previousValue) {
      this.selectedColors = this.colors;
      this.cdRef.detectChanges();
    }
  }

  trackById(item: Color) {
    return item.id;
  }

  emitColorsSelected(colors: any[]) {
    this.colorsSelected.emit(colors);
    this.selectedColors = colors;
  }

  private findSegments() {
    this.colorsAsync$ = concat(
      of([]), // default items
      this.colorInput$.pipe(
        distinctUntilChanged(),
        tap(() => this.colorLoading = true),
        switchMap(term => this.colorService.findAllByDescriptionTerm(term).pipe(
          catchError(() => of([])), // empty list on error
          tap(() => this.colorLoading = false)
        ))
      )
    );
  }
}
