import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Auth } from 'src/app/models/entity/auth.model';
import { RegisterUser } from 'src/app/models/entity/registerUser.model';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(private router: Router) {}

  login(user: Auth) {
    return new Promise((resolve) => {
      window.localStorage.setItem('token', 'meu-token');
      resolve(true);
    });
  }

  createAccount(account: RegisterUser) {
    return new Promise((resolve) => {
      resolve(true);
    });
  }

  logout() {
    window.localStorage.clear();
    this.router.navigate(['login']);
  }
}
