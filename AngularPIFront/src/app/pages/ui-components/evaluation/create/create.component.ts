import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/core/User';
import { EvaluationComponent } from '../evaluation.component';
import { FormBuilder } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { evaluation } from 'src/app/core/Evaluation';
import { EvaluationService } from 'src/app/services/evaluation.service';
import { defense } from 'src/app/core/Defense';

@Component({
  selector: 'app-createe',
  templateUrl: './create.component.html',
  styleUrls: ['./create.component.scss']
})
export class CreateComponentt {
  id: number;
  idDefense :defense ; 
  tutor : User;
 student  :User;
  note :  number ; 
 description : string ;
 constructor(private router: Router,private http: HttpClient,private  EvaluationService: EvaluationService,private fb:FormBuilder,private dialog: MatDialog)
 {
 }
 addevaluation(): void {
  const newevaluation: evaluation = {
  id:this.id , 
  idDefense:this.idDefense ,
  tutor :this.tutor  , 
  student :this.student , 
  note : this.note, 
  description :this.description
  };
  this.EvaluationService.createEvaluation(newevaluation).subscribe(
    (response) => {
      console.log('Task added successfully:', response);
      this.router.navigate(['/ui-components/evaluation']);
    },
    (error) => {
      console.error('Error adding Evaluation:', error);
    }
  );
}

}
