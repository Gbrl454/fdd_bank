import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { environment } from 'src/ environments/environment';
import { DetailTransf } from 'src/app/models/entity/detailTransf.model';
import { RegisterTransf } from 'src/app/models/entity/registerTransf.model';

@Injectable({
  providedIn: 'root',
})
export class TransferenciaService {
  constructor(private router: Router, private http: HttpClient) {}
  listar(): Observable<DetailTransf[]> {
    return this.http.get<DetailTransf[]>(`${environment.api}/transferencias`);
  }

  register(transf: RegisterTransf): Promise<any> {
    return new Promise((resolve) => {
      this.http.post(`${environment.api}/transferencias`, transf).subscribe(
        async (res: any) => {
          resolve({ result: res });
        },
        (error) => {
          resolve({ message: error.replaceAll('"', '') });
        }
      );
    });
  }
}
