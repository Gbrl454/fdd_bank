import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { environment } from 'src/ environments/environment';
import { DetailAgencia } from 'src/app/models/entity/detailAgencia.model';

@Injectable({
  providedIn: 'root',
})
export class AgenciaService {
  constructor(private router: Router, private http: HttpClient) {}

  listAllByIdBanco(id: number): Promise<any> {
    return new Promise((resolve) => {
      this.http.get(`${environment.api}/agencias/${id}`).subscribe(
        async (res: any) => {
          resolve({ result: res });
        },
        (error) => {
          resolve({ message: error });
        }
      );
    });
  }
}
