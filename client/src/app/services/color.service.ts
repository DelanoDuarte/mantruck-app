import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { delay } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { Color } from '../models/Color';
import { BaseService } from './base.service';

@Injectable({
  providedIn: 'root'
})
export class ColorService extends BaseService {

  _BASE_URL = `${environment.apiUrl}/color`;

  constructor(private httpClient: HttpClient) {
    super();
  }

  findAllByDescriptionTerm(color: string): Observable<Color[]> {
    const httpParams = new HttpParams({})
      .set('color', color);
    return this.httpClient.get<Color[]>(this._BASE_URL, { headers: this._HEADERS, params: httpParams }).pipe(delay(500));
  }
}
