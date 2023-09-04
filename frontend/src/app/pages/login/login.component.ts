import { Component, ViewChild, ElementRef, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Auth } from 'src/app/models/entity/auth.model';
import { AuthService } from 'src/app/service/auth/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit {
  login: Auth = {
    login: '',
    senha: '',
  };

  @ViewChild('passwordInput') passwordInput: ElementRef | undefined;
  formLogin!: FormGroup;
  private passwordEye: boolean = false;

  constructor(
    private authService: AuthService,
    private router: Router,
    private formBuilder: FormBuilder
  ) {}

  ngOnInit(): void {
    this.formLogin = this.formBuilder.group({
      login: ['', Validators.compose([Validators.required])],
      senha: ['', Validators.compose([Validators.required])],
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
    try {
      const result = await this.authService.login(this.login);
      console.log(`Login efetuado: ${result}`);

      this.router.navigate(['']);
    } catch (error) {
      console.error(error);
    }
  }
}
