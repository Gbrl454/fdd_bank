import { DetailMoeda } from './detailMoeda.model';

export interface DetailTransf {
  id: number;
  sucesso: boolean;
  motivo?: string;
  valor: number;
  idContaOrigem: number;
  idContaDestino: number;
  moeda: DetailMoeda;
  horarioTranferencia: Date;
}
