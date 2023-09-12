import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/ environments/environment';
import { RegisterConta } from 'src/app/models/entity/registerConta.model';

@Injectable({
  providedIn: 'root',
})
export class ContaService {
  constructor(private router: Router, private http: HttpClient) {}

  register(conta: RegisterConta): Promise<any> {
    return new Promise((resolve) => {
      this.http.post(`${environment.api}/contas`, conta).subscribe(
        async (res: any) => {
          console.log(res);

          resolve({ result: res });
        },
        (error) => {
          resolve({ message: error.replaceAll('"', '') });
        }
      );
    });
  }
}
