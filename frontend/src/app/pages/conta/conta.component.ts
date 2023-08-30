import { Moeda } from './../../models/entity/moeda.model';
import { Component } from '@angular/core';
import { Transferencia } from 'src/app/models/entity/transferencia.model';

@Component({
  selector: 'app-conta',
  templateUrl: './conta.component.html',
  styleUrls: ['./conta.component.scss'],
})
export class ContaComponent {
  transferencias: Transferencia[] = [];

  moeda: Moeda = {
    nome: 'Real',
    simbolo: 'R$',
    multiplicador: 1,
  };

  trasf: Transferencia = {
    id: 1,
    sucesso: true,
    motivo: 'Equilibrios insuficientes para realizar la transferencia',
    valor: 123456,
    idContaOrigem: 1,
    idContaDestino: 1,
    moeda: this.moeda,
    horarioTranferencia: new Date(),
  };

  constructor() {
    this.transferencias = [
      this.trasf,
      this.trasf,
      this.trasf,
      this.trasf,
      this.trasf,
      this.trasf,
    ];
  }

  sucessoMsg(transferencia: Transferencia): string {
    return transferencia.sucesso ? 'Okay' : transferencia.motivo;
  }

  sucessoClass(transferencia: Transferencia): string {
    return transferencia.sucesso ? 'sucesso' : 'fail';
  }
}
