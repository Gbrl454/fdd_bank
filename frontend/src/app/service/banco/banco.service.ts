import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { DetailBanco } from 'src/app/models/entity/detailBanco.model';

@Injectable({
  providedIn: 'root',
})
export class BancoService {
  constructor(private router: Router, private http: HttpClient) {}

  listAll(): Observable<DetailBanco[]> {
    return this.http.get<DetailBanco[]>(`${environment.api}/bancos`);
  }
}
