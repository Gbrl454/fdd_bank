import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/ environments/environment';
import { RegisterConta } from 'src/app/models/entity/registerConta.model';
import { RegisterUser } from 'src/app/models/entity/registerUser.model';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  constructor(private router: Router, private http: HttpClient) {}

  register(user: RegisterUser): Promise<any> {
    return new Promise((resolve) => {
      this.http.post(`${environment.api}/usuarios`, user).subscribe(
        async (res: any) => {
          resolve({ result: res });
        },
        (error) => {
          resolve({ message: error.replaceAll('"', '') });
        }
      );
    });
  }

  getUser(): Promise<any> {
    return new Promise((resolve) => {
      this.http.get(`${environment.api}/usuarios`).subscribe(
        async (res: any) => {
          resolve({
            id: res.id,
            nome: res.nome,
            contas: res.detalhamentoContas,
          });
        },
        (error) => {
          resolve({ message: error.replaceAll('"', '') });
        }
      );
    });
  }
}
