import { AuthService } from './../../service/auth/auth.service';
import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RegisterUser } from 'src/app/models/entity/registerUser.model';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss'],
})
export class RegisterComponent implements OnInit {
  formRegister!: FormGroup;

  account: RegisterUser = {
    nome: 'gbrl',
    email: 'gbrl@teste',
    login: 'gbrl',
    senha: '123',
  };

  @ViewChild('passwordInput') passwordInput: ElementRef | undefined;
  @ViewChild('confPasswordInput') confPasswordInput: ElementRef | undefined;
  formLogin!: FormGroup;
  private passwordEye: boolean = false;
  private confPasswordEye: boolean = false;

  constructor(
    private authService: AuthService,
    private formBuilder: FormBuilder
  ) {}

  ngOnInit(): void {
    this.formRegister = this.formBuilder.group({
      nome: [null, Validators.compose([Validators.required])],
      email: [
        null,
        Validators.compose([Validators.required, Validators.email]),
      ],
      senha: [null, Validators.compose([Validators.required])],
      confSenha: [null, Validators.compose([Validators.required])],
    });
  }

  habilitarBotao(): string {
    return this.formRegister.valid ? 'botao' : 'botao_desabilitado';
  }

  senhasIguais() {
    let senha: string = this.formRegister.get('senha')?.value;
    let confSenha: string = this.formRegister.get('confSenha')?.value;
    return senha === confSenha;
  }

  passwordEyeIs(): string {
    return this.passwordEye ? 'open' : 'close';
  }

  changePasswordEye() {
    this.passwordEye = !this.passwordEye;
    const inputElement: HTMLInputElement = this.passwordInput?.nativeElement;
    inputElement.type = this.passwordEye ? 'text' : 'password';
  }

  confPasswordEyeIs(): string {
    return this.confPasswordEye ? 'open' : 'close';
  }

  changeConfPasswordEye() {
    this.confPasswordEye = !this.confPasswordEye;
    const inputElement: HTMLInputElement =
      this.confPasswordInput?.nativeElement;
    inputElement.type = this.confPasswordEye ? 'text' : 'password';
  }

  async onSubmit() {
    try {
      const result = await this.authService.createAccount(this.account);
      console.log(result);
    } catch (error) {
      console.error(error);
    }
  }
}
