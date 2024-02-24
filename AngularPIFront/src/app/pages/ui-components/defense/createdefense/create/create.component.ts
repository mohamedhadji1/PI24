import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { Route, Router } from '@angular/router';
import { Number } from 'mongoose';
import { defense } from 'src/app/core/Defense';
import { DefenceService } from 'src/app/services/defence.service';

@Component({
  selector: 'app-create',
  templateUrl: './create.component.html',
  styleUrls: ['./create.component.scss']
})
export class CreateComponent {
  //id: Number ; 

  dateDefence :Date;

  numeroDeClasse: number;

  nomDeJuret:String;

  nomDeEncafrent:String;

  remarque :String;
  constructor(private router: Router,private http: HttpClient,private  defenceService: DefenceService,private fb:FormBuilder,private dialog: MatDialog)
{

}
addDefense(): void {
  const newDefense: defense = {
    dateDefence: this.dateDefence,
    numeroDeClasse: this.numeroDeClasse,
    nomDeJuret: this.nomDeJuret,
    nomDeEncafrent: this.nomDeEncafrent,
    remarque: this.remarque,
  };

  this.defenceService.createDefence(newDefense).subscribe({
    next: (response) => {
      console.log('Defense added successfully:', response);
      this.router.navigateByUrl('/ui-components/defense', { skipLocationChange: true }).then(() => {
        this.router.navigate(['/ui-components/defense']);
      });
    },
    error: (error) => {
      console.error('Error adding defense:', error);
    }
  });
}



}
