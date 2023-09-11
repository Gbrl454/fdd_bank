import { AuthService } from './../../service/auth/auth.service';
import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { DetailAgencia } from 'src/app/models/entity/detailAgencia.model';
import { DetailBanco } from 'src/app/models/entity/detailBanco.model';
import { RegisterUser } from 'src/app/models/entity/registerUser.model';
import { AgenciaService } from 'src/app/service/agencia/agencia.service';
import { BancoService } from 'src/app/service/banco/banco.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss'],
})
export class RegisterComponent implements OnInit {
  formRegister!: FormGroup;
  sec: number = 3;
  tipConta: number = 1;
  title: string = '???';
  senha: string = 'senha';
  confSenha: string = 'confSenha';
  bancos: DetailBanco[] = [];
  agencias: DetailAgencia[] = [];
  idBanco: number = 0;
  possuirContaNormal: boolean = false;
  possuirContaEspecial: boolean = false;
  possuirContaEspecialCartaoDeCredito: boolean = false;
  possuirContaPremium: boolean = false;
  possuirContaPremiumCartaoDeCredito: boolean = false;
  possuirContaPremiumLIS: boolean = false;

  @ViewChild('passwordInput') passwordInput: ElementRef | undefined;
  @ViewChild('confirmPasswordInput') confirmPasswordInput:
    | ElementRef
    | undefined;
  private passwordEye: boolean = false;
  private confirmPasswordEye: boolean = false;

  ngOnInit(): void {
    this.goSec(this.sec);
    this.onListBancos();

    this.formRegister = this.formBuilder.group({
      banco: [null, Validators.compose([Validators.required])],
      agencia: [null, Validators.compose([Validators.required])],
    });
  }

  constructor(
    private bancoService: BancoService,
    private agenciaService: AgenciaService,
    private formBuilder: FormBuilder
  ) {}

  goSec(sec: number) {
    this.sec = sec;

    this.title =
      this.sec == 1
        ? 'Preencha Seus Dados Pessoais'
        : this.sec == 2
        ? 'Preencha Seus Dados de Login'
        : this.sec == 3
        ? 'Crie Sua(s) Conta(s)'
        : '???';
  }

  isSec(sec: number) {
    return this.sec == sec ? 'blueGray' : 'gray';
  }

  goTipConta(tipConta: number) {
    this.tipConta = tipConta;
  }

  isTipConta(tipConta: number){
    return this.tipConta == tipConta ?  'blueGray':'';
  }


  samePassword(): boolean {
    return this.senha === this.confSenha;
  }

  passwordEyeIs(): string {
    return this.passwordEye ? 'open' : 'close';
  }

  changePasswordEye() {
    this.passwordEye = !this.passwordEye;
    const inputElement: HTMLInputElement = this.passwordInput?.nativeElement;
    inputElement.type = this.passwordEye ? 'text' : 'password';
  }

  confirmPasswordEyeIs(): string {
    return this.confirmPasswordEye ? 'open' : 'close';
  }

  changeConfirmPasswordEye() {
    this.confirmPasswordEye = !this.confirmPasswordEye;
    const inputElement: HTMLInputElement =
      this.confirmPasswordInput?.nativeElement;
    inputElement.type = this.confirmPasswordEye ? 'text' : 'password';
  }

  onListBancos() {
    this.bancoService.listAll().subscribe((data) => {
      this.bancos = data;
    });
  }

  onListAgencias() {
    this.agencias = [];
    this.idBanco = this.formRegister.get('banco')?.value;
    if (this.idBanco != null) {
      this.agenciaService.listAllByIdBanco(this.idBanco).subscribe((data) => {
        this.agencias = data;
      });
    }
  }

  terContaNormal() {
    this.possuirContaNormal = !this.possuirContaNormal;
  }

  isPossuirContaNormal(): string {
    return this.possuirContaNormal ? 'green' : 'red';
  }

  terContaEspecial() {
    this.possuirContaEspecial = !this.possuirContaEspecial;
  }

  isPossuirContaEspecial(): string {
    return this.possuirContaEspecial ? 'green' : 'red';
  }

  terContaEspecialCartaoDeCredito() {
    this.possuirContaEspecialCartaoDeCredito =
      !this.possuirContaEspecialCartaoDeCredito;
  }

  isPossuirContaEspecialCartaoDeCredito(): string {
    return this.possuirContaEspecialCartaoDeCredito ? 'green' : 'red';
  }

  terContaPremium() {
    this.possuirContaPremium = !this.possuirContaPremium;
  }

  isPossuirContaPremium(): string {
    return this.possuirContaPremium ? 'green' : 'red';
  }

  terContaPremiumCartaoDeCredito() {
    this.possuirContaPremiumCartaoDeCredito =
      !this.possuirContaPremiumCartaoDeCredito;
  }

  isPossuirContaPremiumCartaoDeCredito(): string {
    return this.possuirContaPremiumCartaoDeCredito ? 'green' : 'red';
  }

  terContaPremiumLIS() {
    this.possuirContaPremiumLIS = !this.possuirContaPremiumLIS;
  }

  isPossuirContaPremiumLIS(): string {
    return this.possuirContaPremiumLIS ? 'green' : 'red';
  }
}
