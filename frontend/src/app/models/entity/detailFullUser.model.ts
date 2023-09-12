import { DetailConta } from './detailConta.model';

export interface DetailFullUser {
  id: number;
  nome: string;
  contaNormal?: DetailConta;
  contaEspecial?: DetailConta;
  contaPremium?: DetailConta;
}
