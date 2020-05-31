import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Truck } from '../models/Truck';
import { BaseService } from './base.service';


@Injectable({
  providedIn: 'root'
})
export class TruckService extends BaseService {

  _BASE_URL = `${environment.apiUrl}/truck`;

  constructor(private httpClient: HttpClient) {
    super();
  }

  findAll(): Observable<Truck[]> {
    return this.httpClient.get<Truck[]>(this._BASE_URL, { headers: this._HEADERS });
  }

  findAllPaginated(page, size): Observable<any> {
    const httpParams = new HttpParams({}).set('page', page).set('size', size);
    return this.httpClient.get<any>(`${this._BASE_URL}/paginated`, { headers: this._HEADERS, params: httpParams });
  }

  findAllWithFilterAndPaginated(truck: Truck, page, size): Observable<any> {
    const httpParams = new HttpParams({}).set('page', page).set('size', size);
    return this.httpClient.post<any>(`${this._BASE_URL}/find`, truck, { headers: this._HEADERS, params: httpParams });
  }

  save(truck: Truck): Observable<Truck> {
    return this.httpClient.post<Truck>(this._BASE_URL, truck, { headers: this._HEADERS });
  }

  findOne(id: number): Observable<Truck> {
    return this.httpClient.get<Truck>(`${this._BASE_URL}/${id}`, { headers: this._HEADERS });
  }

  deleteOne(id: number): Observable<any> {
    return this.httpClient.delete<Truck>(`${this._BASE_URL}/${id}`, { headers: this._HEADERS });
  }

  findAllFuels(): Observable<string[]> {
    return this.httpClient.get<string[]>(`${this._BASE_URL}/fuels`, { headers: this._HEADERS });
  }

  findAllRanges(): Observable<string[]> {
    return this.httpClient.get<string[]>(`${this._BASE_URL}/ranges`, { headers: this._HEADERS });
  }
}
