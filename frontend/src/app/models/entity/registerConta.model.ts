import { TipoConta } from 'src/app/util/enums/TipoConta';

export interface RegisterConta {
  tipo: TipoConta;
  saldo?: number;
  cartao_de_credito?: boolean;
  saldo_cartao_de_credito?: number;
  lis?: boolean;
  saldo_lis?: number;
  idUsuario?: number;
  idAgencia?: number;
}
