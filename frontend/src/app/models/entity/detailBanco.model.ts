import { DetailPais } from './detailPais.model';

export interface DetailBanco {
  id: number;
  nome: string;
  pais: DetailPais;
}
