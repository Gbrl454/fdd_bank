import { UserService } from './../../service/user/user.service';
import { DetailBanco } from './../../models/entity/detailBanco.model';
import { RegisterUser } from './../../models/entity/registerUser.model';
import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { DetailAgencia } from 'src/app/models/entity/detailAgencia.model';
import { RegisterConta } from 'src/app/models/entity/registerConta.model';
import { AgenciaService } from 'src/app/service/agencia/agencia.service';
import { BancoService } from 'src/app/service/banco/banco.service';
import { MsgErros } from 'src/app/models/entity/MsgErros.model';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss'],
})
export class RegisterComponent implements OnInit {
  user: RegisterUser = {
    nome: '',
    email: '',
    login: '',
    senha: '',
  };

  msgErros: MsgErros = {};

  contaNormal: RegisterConta = {
    idUser: 0,
    idAgencia: 0,
    moeda: '',
    saldo: 0,
    tipo: '',
    cartao_de_credito: false,
    saldo_cartao_de_credito: 0,
    lis: false,
    saldo_lis: 0,
  };
  contaEspecial: RegisterConta = {
    idUser: 0,
    idAgencia: 0,
    moeda: '',
    saldo: 0,
    tipo: '',
    cartao_de_credito: false,
    saldo_cartao_de_credito: 0,
    lis: false,
    saldo_lis: 0,
  };
  contaPremium: RegisterConta = {
    idUser: 0,
    idAgencia: 0,
    moeda: '',
    saldo: 0,
    tipo: '',
    cartao_de_credito: false,
    saldo_cartao_de_credito: 0,
    lis: false,
    saldo_lis: 0,
  };

  formRegister!: FormGroup;
  sec: number = 1;
  tipConta: number = 1;
  title: string = '???';
  userConfirmSenha: string = '';
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
      nome: [null, Validators.compose([Validators.required])],
      email: [
        null,
        Validators.compose([Validators.required, Validators.email]),
      ],
      login: [null, Validators.compose([Validators.required])],
      senha: [null, Validators.compose([Validators.required])],
      confirmSenha: [null, Validators.compose([Validators.required])],

      banco: [null, Validators.compose([Validators.required])],
      agencia: [null, Validators.compose([Validators.required])],
    });
  }

  constructor(
    private bancoService: BancoService,
    private agenciaService: AgenciaService,
    private formBuilder: FormBuilder,
    private userService: UserService
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

  transformarStringParaVetor(
    json: string
  ): { campo: string; mensagem: string }[] {
    try {
      const entrada = JSON.parse(json);
      const resultado: { campo: string; mensagem: string }[] = [];

      entrada.forEach((item: { campo: any; mensagem: any }) => {
        resultado.push({ campo: item.campo, mensagem: item.mensagem });
      });

      return resultado;
    } catch (error) {
      throw new Error('Não é uma lista');
    }
  }

  async onSubmit() {
    this.msgErros = {};
    this.userService.register(this.user).then((data: any) => {
      if (data) {
        try {
          const results = this.transformarStringParaVetor(data.message);

          let vNome: string[] = [];
          let vEmail: string[] = [];
          let vLogin: string[] = [];
          let vSenha: string[] = [];

          for (let i = 0; i < results.length; i++) {
            if (results[i].campo == 'nome') vNome.push(results[i].mensagem);
            if (results[i].campo == 'email') vEmail.push(results[i].mensagem);
            if (results[i].campo == 'login') vLogin.push(results[i].mensagem);
            if (results[i].campo == 'senha') vSenha.push(results[i].mensagem);
          }
          this.msgErros.registerUserNome = vNome;
          this.msgErros.registerUserEmail = vEmail;
          this.msgErros.registerUserLogin = vLogin;
          this.msgErros.registerUserSenha = vSenha;
          return;
        } catch (error) {
          if (data.message) this.msgErros.registerUserGeral = [data.message];
        }
        if (data.message != undefined) return;

        // Usuario Cadastrado
        console.log(data.result.id);
      }
    });
    // console.log(this.msgErros);
    // console.log(this.user);

    // console.log(this.contaNormal);
    // console.log(this.contaEspecial);
    // console.log(this.contaPremium);
  }

  isSec(sec: number) {
    return this.sec == sec ? 'blueGray' : 'gray';
  }

  goTipConta(tipConta: number) {
    this.tipConta = tipConta;
  }

  isTipConta(tipConta: number) {
    return this.tipConta == tipConta ? 'blueGray' : '';
  }

  samePassword(): boolean {
    if (this.user.senha == '') return true;
    return this.user.senha === this.userConfirmSenha;
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
    this.bancoService.listAll().then((data: any) => {
      if (data) {
        if (data.result instanceof Array) this.bancos = data.result;
        else
          this.msgErros.registerUserBanco = data.message
            .toString()
            .split('"')
            .filter(function (i: any) {
              return i;
            });
      }
    });
  }

  onListAgencias() {
    this.agencias = [];
    this.idBanco = this.formRegister.get('banco')?.value;

    if (this.idBanco != null) {
      this.agenciaService.listAllByIdBanco(this.idBanco).then((data: any) => {
        if (data) {
          if (data.result instanceof Array) this.agencias = data.result;
          else
            this.msgErros.registerUserAgencia = data.message
              .toString()
              .split('"')
              .filter(function (i: any) {
                return i;
              });
        }
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
