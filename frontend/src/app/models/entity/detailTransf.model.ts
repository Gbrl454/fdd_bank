import { DetailConta } from './detailConta.model';
import { DetailMoeda } from './detailMoeda.model';

export interface DetailTransf {
  id: number;
  sucesso: boolean;
  motivo?: string;
  valor: number;
  ContaOrigem: DetailConta;
  ContaDestino: DetailConta;
  moeda: DetailMoeda;
  horarioTranferencia: Date;
}
