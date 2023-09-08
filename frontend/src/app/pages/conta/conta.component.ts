import { TransferenciaService } from './../../service/transferencia/transferencia.service';
import { Component } from '@angular/core';
import { DetailTransf } from 'src/app/models/entity/detailTransf.model';

@Component({
  selector: 'app-conta',
  templateUrl: './conta.component.html',
  styleUrls: ['./conta.component.scss'],
})
export class ContaComponent {
  transferencias: DetailTransf[] = [];

  constructor(private transferenciaService: TransferenciaService) {
    this.onList();
  }

  sucessoMsg(transferencia: DetailTransf): string {
    return transferencia.sucesso ? 'Okay' : transferencia.motivo!;
  }

  sucessoClass(transferencia: DetailTransf): string {
    return transferencia.sucesso ? 'sucesso' : 'fail';
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
