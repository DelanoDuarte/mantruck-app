import { HttpHeaders } from '@angular/common/http';

// Base service to handle possible common configuration between the services.
export class BaseService {
    _HEADERS = new HttpHeaders({ 'Content-Type': 'application/json; charset=utf-8' });
}
