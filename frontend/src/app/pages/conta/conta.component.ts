import { DetailMoeda } from '../../models/entity/detailMoeda.model';
import { Component } from '@angular/core';
import { DetailTransf } from 'src/app/models/entity/detailTransf.model';

@Component({
  selector: 'app-conta',
  templateUrl: './conta.component.html',
  styleUrls: ['./conta.component.scss'],
})
export class ContaComponent {
  transferencias: DetailTransf[] = [];

  moeda: DetailMoeda = {
    nome: 'Real',
    simbolo: 'R$',
    multiplicador: 1,
  };

  trasf: DetailTransf = {
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

  sucessoMsg(transferencia: DetailTransf): string {
    return transferencia.sucesso ? 'Okay' : transferencia.motivo!;
  }

  sucessoClass(transferencia: DetailTransf): string {
    return transferencia.sucesso ? 'sucesso' : 'fail';
  }
}
