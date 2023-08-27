import { Component, ViewChild, ElementRef, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/service/auth/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit {
  formLogin!: FormGroup;
  login = {
    login: '',
    senha: '',
  };

  private passwordEye: boolean = false;

  @ViewChild('passwordInput') passwordInput: ElementRef | undefined;

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

  async onSubmit() {
    try {
      const result = await this.authService.login(this.login);
      console.log(`Login efetuado: ${result}`);

      this.router.navigate(['']);
    } catch (error) {
      console.error(error);
    }
  }

  passwordEyeIs(): string {
    return this.passwordEye ? 'open' : 'close';
  }

  changepasswordEye() {
    this.passwordEye = !this.passwordEye;
    const inputElement: HTMLInputElement = this.passwordInput?.nativeElement;
    inputElement.type = this.passwordEye ? 'text' : 'password';
  }

  // habilitarBotao(): string {
  //   return this.formLogin.valid ? "botao" : "botao__desabilitado";
  // }
}
