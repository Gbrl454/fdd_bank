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

  listAll(): Promise<any> {
    return new Promise((resolve) => {
      this.http.get(`${environment.api}/bancos`).subscribe(
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
