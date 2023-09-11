import { DetailBanco } from './detailBanco.model';

export interface DetailAgencia {
  id: number;
  nome: string;
  banco: DetailBanco;
}
