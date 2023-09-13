import { TransferenciaService } from './../../service/transferencia/transferencia.service';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MsgErros } from 'src/app/models/entity/MsgErros.model';
import { DetailBanco } from 'src/app/models/entity/detailBanco.model';
import { DetailConta } from 'src/app/models/entity/detailConta.model';
import { DetailFullUser } from 'src/app/models/entity/detailFullUser.model';
import { DetailMoeda } from 'src/app/models/entity/detailMoeda.model';
import { DetailPais } from 'src/app/models/entity/detailPais.model';
import { RegisterTransf } from 'src/app/models/entity/registerTransf.model';
import { UserService } from 'src/app/service/user/user.service';
import { TipoConta } from 'src/app/util/enums/TipoConta';
import { Util } from 'src/app/util/util';

@Component({
  selector: 'app-transferencia',
  templateUrl: './transferencia.component.html',
  styleUrls: ['./transferencia.component.scss'],
})
export class TransferenciaComponent {
  transf: RegisterTransf = {
    idDConta: 0,
    valor: 0,
  };

  user: DetailFullUser = {
    id: 0,
    nome: '',
  };

  moeda: DetailMoeda = {
    nome: '',
    simbolo: '',
    multiplicador: 0,
  };

  pais: DetailPais = {
    nome: '',
    idioma: '',
    utc: 0,
    moeda: this.moeda,
  };

  banco: DetailBanco = {
    id: 0,
    nome: '',
    pais: this.pais,
  };

  conta: DetailConta = {
    id: 0,
    tipo: null,
    saldo: 0,
    cartaoDeCredito: false,
    saldoCartaoDeCredito: 0,
    lis: false,
    saldoLis: 0,
    agencia: null,
    banco: this.banco,
    usuario: null,
  };

  formTransf!: FormGroup;
  msgErros: MsgErros = {};
  sec: number = 1;
  sucesso: boolean = false;

  constructor(
    private formBuilder: FormBuilder,
    private transferenciaService: TransferenciaService,
    private ut: Util,
    private userService: UserService
  ) {}

  ngOnInit(): void {
    this.getUser();
    this.formTransf = this.formBuilder.group({
      valor: [
        null,
        Validators.compose([
          Validators.required,
          Validators.min(0.01),
          Validators.max(999999999.99),
        ]),
      ],
      idDestino: [
        null,
        Validators.compose([Validators.required, Validators.min(1)]),
      ],
    });
  }

  habilitarBotao(): string {
    return this.formTransf.valid ? 'botao' : 'botao_desabilitado';
  }

  async onSubmit() {
    this.transf.tipoConta = this.sec - 1;

    this.transferenciaService.register(this.transf).then((data: any) => {
      console.log(data.result);

      if (data) {
        try {
          this.sucesso = data.result.sucesso;
          if (!this.sucesso) {
            this.msgErros.transfGeral = [data.result.motivo!];
          }

          return;
        } catch (error) {
          if (data.message) this.msgErros.transfGeral = [data.message];
        }
        if (data.message != undefined) return;
      }
    });
  }

  getUser() {
    this.userService.getUser().then((data: any) => {
      if (data) {
        try {
          this.user.id = data.id;
          this.user.nome = data.nome;
          const contas: DetailConta[] = data.contas;
          for (let i = 0; i < contas.length; i++) {
            if (contas[i].tipo!.toString() == 'NORMAL')
              this.user.contaNormal = contas[i];
            if (contas[i].tipo!.toString() == 'ESPECIAL')
              this.user.contaEspecial = contas[i];
            if (contas[i].tipo!.toString() == 'PREMIUM')
              this.user.contaPremium = contas[i];
          }

          if (this.user.contaPremium !== undefined) this.goSec(3);
          if (this.user.contaEspecial !== undefined) this.goSec(2);
          if (this.user.contaNormal !== undefined) this.goSec(1);
          return;
        } catch (error) {
          console.log(error);
        }
        if (data.message != undefined) return;
      }
    });
  }

  goSec(sec: number) {
    this.sec = sec;

    if (sec == 1) {
      this.conta = this.user.contaNormal!;
    } else if (sec == 2) {
      this.conta = this.user.contaEspecial!;
    } else if (sec == 3) {
      this.conta = this.user.contaPremium!;
    }

    this.banco = this.conta.banco;
    this.pais = this.banco.pais;
    this.moeda = this.pais.moeda;
  }

  isSec(sec: number) {
    return this.sec == sec ? 'blueGray' : 'gray';
  }
}
