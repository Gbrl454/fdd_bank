import { TipoConta } from 'src/app/util/enums/TipoConta';
import { DetailMoeda } from './detailMoeda.model';
import { DetailAgencia } from './detailAgencia.model';
import { DetailBanco } from './detailBanco.model';
import { DetailUser } from './detailUser.model';

export interface DetailConta {
  id: number;
  tipo: TipoConta|null;
  saldo: number;
  cartaoDeCredito: boolean;
  saldoCartaoDeCredito: number;
  lis: boolean;
  saldoLis: number;
  agencia: DetailAgencia|null;
  banco: DetailBanco;
  usuario: DetailUser|null;
}
