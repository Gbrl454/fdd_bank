import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { environment } from 'src/ environments/environment';
import { DetailAgencia } from 'src/app/models/entity/detailAgencia.model';

@Injectable({
  providedIn: 'root'
})
export class AgenciaService {
  constructor(private router: Router, private http: HttpClient) {}

  listAllByIdBanco(id:number): Observable<DetailAgencia[]> {
    return this.http.get<DetailAgencia[]>(`${environment.api}/agencias/${id}`);
  }
}
