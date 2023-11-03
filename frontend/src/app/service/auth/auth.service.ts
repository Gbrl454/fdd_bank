import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import jwtDecode from 'jwt-decode';
import { environment } from 'src/ environments/environment';
import { Auth } from 'src/app/models/entity/auth.model';
import { RegisterUser } from 'src/app/models/entity/registerUser.model';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(private router: Router, private http: HttpClient) {}

  async login(user: Auth) {
    const result = await this.http
      .post<any>(`${environment.api}/login`, user)
      .toPromise();
    if (result && result.token) {
      window.localStorage.setItem('token', result.token);
      window.localStorage.setItem('idUser', result.id);
      return true;
    }

    return false;
  }

  async createAccount(account: RegisterUser) {
    const result = await this.http
      .post<RegisterUser>(`${environment.api}/usuarios`, account)
      .toPromise();
    return result;
  }

  getAuthorizationToken() {
    const token = window.localStorage.getItem('token');
    return token;
  }

  getTokenExpirationDate(token: string): Date | null {
    const decoded: any = jwtDecode(token);
    if (decoded.exp === undefined) return null;

    const date = new Date(0);
    date.setUTCSeconds(decoded.exp);
    return date;
  }

  isTokenExpired(token?: string): boolean {
    if (!token) return true;

    const date = this.getTokenExpirationDate(token);
    if (date === undefined || date === null) return false;

    return !(date.valueOf() > new Date().valueOf());
  }

  isUserLoggedIn() {
    const token = this.getAuthorizationToken();
    if (!token) return false;
    else return !this.isTokenExpired(token);
  }

  logout() {
    window.localStorage.clear();
    this.router.navigate(['login']);
  }
}
