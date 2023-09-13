import { TipoConta } from 'src/app/util/enums/TipoConta';

export interface RegisterTransf {
  idOConta?: number;
  idDConta: number;
  valor: number;
  tipoConta?: TipoConta;
}
