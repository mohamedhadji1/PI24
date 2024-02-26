import { User } from './User';
import { Time } from '@angular/common';

export interface defense {
  idDef: number;
  dateDefense :Date;
  timeDefense :string ;
  numeroDeBloc :string  ;
  numeroDeClasse: number;
    nomDeJuret: User;
    nomDeEncadrent: string;
    remarque: string;
  }
  
 
  