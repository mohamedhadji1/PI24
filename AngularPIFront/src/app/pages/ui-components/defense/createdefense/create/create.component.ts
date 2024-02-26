import { Time } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { Route, Router } from '@angular/router';
import { defense } from 'src/app/core/Defense';
import { User } from 'src/app/core/User';
import { DefenceService } from 'src/app/services/defence.service';

@Component({
  selector: 'app-create',
  templateUrl: './create.component.html',
  styleUrls: ['./create.component.scss']
})
export class CreateComponent {
  id: number  ; 

  dateDefence: Date;
  timeDefense: string ; 
  numeroDeClasse: number;
  numeroDeBloc :string ; 
  nomDeJuret:User;

  nomDeEncadrent:string ;

  remarque :string = '';
  
  constructor(private router: Router,private http: HttpClient,private  defenceService: DefenceService,private fb:FormBuilder,private dialog: MatDialog)
{

 
}
addDefense(): void {
  const newDefense: defense = {
    idDef : this.id ,
    dateDefense: this.dateDefence,
    timeDefense:this.timeDefense,
    numeroDeBloc :this.numeroDeBloc , 
    numeroDeClasse: this.numeroDeClasse,
    nomDeJuret: this.nomDeJuret ,
    nomDeEncadrent:this.nomDeEncadrent ,
remarque:this.remarque
  };
  this.defenceService.createDefence(newDefense).subscribe(
    (response) => {
      console.log('Task added successfully:', response);
      this.router.navigate(['/ui-components/defense']);
    },
    (error) => {
      console.error('Error adding defense:', error);
    }
  );
}

}




