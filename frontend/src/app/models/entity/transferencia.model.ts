import { Moeda } from './moeda.model';

export interface Transferencia {
  id: number;
  sucesso: boolean;
  motivo: string;
  valor: number;
  idContaOrigem: number;
  idContaDestino: number;
  moeda: Moeda;
  horarioTranferencia: Date;
}
