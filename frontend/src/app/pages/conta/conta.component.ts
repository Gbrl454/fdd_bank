import { UserService } from './../../service/user/user.service';
import { TransferenciaService } from './../../service/transferencia/transferencia.service';
import { Component, OnInit } from '@angular/core';
import { DetailTransf } from 'src/app/models/entity/detailTransf.model';
import { Util } from 'src/app/util/util';
import { DetailFullUser } from 'src/app/models/entity/detailFullUser.model';
import { DetailConta } from 'src/app/models/entity/detailConta.model';
import { DetailMoeda } from 'src/app/models/entity/detailMoeda.model';
import { DetailBanco } from 'src/app/models/entity/detailBanco.model';
import { DetailPais } from 'src/app/models/entity/detailPais.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-conta',
  templateUrl: './conta.component.html',
  styleUrls: ['./conta.component.scss'],
})
export class ContaComponent implements OnInit {
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

  transferencias: DetailTransf[] = [];
  sec: number = 1;
  constructor(
    private transferenciaService: TransferenciaService,
    private userService: UserService,
    private ut: Util,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.onList();
    this.getUser();
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
          window.localStorage.setItem('idUser', `${this.user.id}`);
          this.router.navigate(['/register']);
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

  sucessoMsg(transferencia: DetailTransf): string {
    return transferencia.sucesso ? 'Okay' : transferencia.motivo!;
  }

  sucessoClass(transferencia: DetailTransf): string {
    return transferencia.sucesso ? 'sucesso' : 'fail';
  }

  getSaldoTotal(conta: DetailConta) {
    return conta == null || conta == undefined
      ? 0
      : conta.saldo + conta.saldoCartaoDeCredito + conta.saldoLis;
  }

  private onList() {
    this.transferenciaService.listar().subscribe((items) => {
      const dataAux = items;

      let data: DetailTransf[] = [];
      for (let i = 0; i < dataAux.length; i++) {
        data.push(dataAux[i]);
      }
      this.transferencias = data;
    });
  }
}
