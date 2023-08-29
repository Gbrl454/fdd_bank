import { AuthService } from 'src/app/service/auth/auth.service';
import { Component } from '@angular/core';

@Component({
  selector: 'app-template-auth',
  templateUrl: './template-auth.component.html',
  styleUrls: ['./template-auth.component.scss'],
})
export class TemplateAuthComponent {
  constructor(private authService: AuthService) {}

  logout() {
    this.authService.logout();
  }
}
