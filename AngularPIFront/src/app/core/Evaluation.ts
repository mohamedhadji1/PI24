import { defense } from './Defense';
import { User } from './User';
import { Date, ObjectId } from 'mongoose';

export interface evaluation 
{
      id: number;
      idDefense :defense ; 
       tutor : User;
      student  :User;
       note :  number ; 
      description : string ;
}