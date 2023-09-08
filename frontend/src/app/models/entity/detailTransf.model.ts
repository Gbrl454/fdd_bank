import { DetailMoeda } from './detailMoeda.model';

export interface DetailTransf {
  id: number;
  sucesso: boolean;
  motivo?: string;
  valor: number;
  nomeUserContaOrigem: string;
  nomeUserContaDestino: string;
  moeda: DetailMoeda;
  horarioTranferencia: Date;
}
