import { Injectable } from '@angular/core';
import { User } from 'src/app/models/entity/user.model';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor() {}

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
}
