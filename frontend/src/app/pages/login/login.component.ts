import { Auth } from './../../models/entity/auth.model';
import { Component, ViewChild, ElementRef, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/service/auth/auth.service';
import { Util } from 'src/app/util/util';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit {
  login: Auth = { login: '', senha: '' };

  @ViewChild('passwordInput') passwordInput: ElementRef | undefined;
  formLogin!: FormGroup;
  private passwordEye: boolean = false;
  error: string = '';

  constructor(
    private authService: AuthService,
    private router: Router,
    private formBuilder: FormBuilder,
    private ut: Util
  ) {}

  ngOnInit(): void {
    this.formLogin = this.formBuilder.group({
      login: [null, Validators.compose([Validators.required])],
      senha: [null, Validators.compose([Validators.required])],
    });
  }

  passwordEyeIs(): string {
    return this.passwordEye ? 'open' : 'close';
  }

  changepasswordEye() {
    this.passwordEye = !this.passwordEye;
    const inputElement: HTMLInputElement = this.passwordInput?.nativeElement;
    inputElement.type = this.passwordEye ? 'text' : 'password';
  }

  habilitarBotao(): string {
    return this.formLogin.valid ? 'botao' : 'botao_desabilitado';
  }

  async onSubmit() {
    this.error = '';
    try {
      const result = await this.authService.login(this.login);
      this.router.navigate(['']);
    } catch (error) {
      this.error = 'Usuário e/ou Senha  inválido(s)';
    }
  }
}
