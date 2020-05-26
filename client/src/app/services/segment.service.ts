import { Injectable } from '@angular/core';
import { BaseService } from './base.service';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Segment } from '../models/Segment';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { delay } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class SegmentService extends BaseService {

  _BASE_URL = `${environment.apiUrl}/segment`;

  constructor(private httpClient: HttpClient) {
    super();
  }

  findAllByDescriptionTerm(description: string): Observable<Segment[]> {
    const httpParams = new HttpParams({})
      .set('description', description);
    return this.httpClient.get<Segment[]>(this._BASE_URL, { headers: this._HEADERS, params: httpParams }).pipe(delay(500));
  }
}
