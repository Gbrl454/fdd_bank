export interface RegisterConta{
  idUser: number;
  idAgencia: number;
  moeda: string;
  saldo: number;
  tipo: string;
  cartao_de_credito: boolean;
  saldo_cartao_de_credito: number;
  lis: boolean;
  saldo_lis: number;
}
