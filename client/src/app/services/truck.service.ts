import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Truck } from '../models/Truck';
import { environment } from 'src/environments/environment';
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

  save(truck: Truck): Observable<Truck> {
    return this.httpClient.post<Truck>(this._BASE_URL, truck, { headers: this._HEADERS });
  }

  findOne(id: number): Observable<Truck> {
    return this.httpClient.get<Truck>(`${this._BASE_URL}/${id}`, { headers: this._HEADERS });
  }

  findAllFuels(): Observable<string[]> {
    return this.httpClient.get<string[]>(`${this._BASE_URL}/fuels`, { headers: this._HEADERS });
  }
}
