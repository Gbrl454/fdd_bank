import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/entity/user.model';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(private router: Router) {}

  login(user: User) {
    return new Promise((resolve) => {
      window.localStorage.setItem('token', 'meu-token');
      resolve(true);
    });
  }

  createAccount(account: User) {
    return new Promise((resolve) => {
      resolve(true);
    });
  }

  logout() {
    window.localStorage.clear();
    this.router.navigate(['login']);
  }
}
