import { Role, User } from './User';
import { Time } from '@angular/common';

export interface defense {
  idDef: number;
  dateDefense: Date;
  timeDefense: string;
  numeroDeBloc: string;
  numeroDeClasse: number;
 nomDeJuret?: Partial<User>;
  UserStudent?: Partial<User>;
  nomDeEncadrent: string;
  remarque: string;
  Role: Role;
  duration?: number;
}

  
 
  