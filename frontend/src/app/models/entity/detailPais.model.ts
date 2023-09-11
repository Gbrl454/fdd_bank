import { DetailMoeda } from './detailMoeda.model';

export interface DetailPais {
  nome: string;
  idioma: string;
  utc: number;
  moeda: DetailMoeda;
}
