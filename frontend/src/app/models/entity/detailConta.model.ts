import { DetailMoeda } from './detailMoeda.model';

export interface DetailConta {
  id: number;
  idAgencia: number;
  idUsuario: number;
  moeda: DetailMoeda;
  saldo: number;
  tipo: TipoConta;
  cartao_de_credito: boolean;
  saldo_cartao_de_credito: number;
  lis: boolean;
  saldo_lis: number;
}
