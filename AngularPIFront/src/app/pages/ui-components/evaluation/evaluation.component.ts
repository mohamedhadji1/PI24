import { HttpClient } from '@angular/common/http';
import { Component, createComponent } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { evaluation } from 'src/app/core/Evaluation';
import { EvaluationService } from 'src/app/services/evaluation.service';
import { CreateComponentt } from 'src/app/pages/ui-components/evaluation/create/create.component' ; 
import { UpdateComponentt } from './update/update.component';
import { catchError, tap } from 'rxjs';

@Component({
  selector: 'app-evaluation',
  templateUrl: './evaluation.component.html',
  styleUrls: ['./evaluation.component.scss'] ,
  providers:[EvaluationService]
})
export class EvaluationComponent {
Evaluations :evaluation[] ; 

constructor(private http: HttpClient,private  evaluationService: EvaluationService,private fb:FormBuilder,private dialog: MatDialog)
   {


    }
    ngOnInit() {
      console.log('On init....');
      //this.defenceService.getAllDefence().subscribe((data: defense[]) => {
        //this.defences = data;
      this.fetchEvaluation() ; 
  }
 openCreateDialogEval(): void {
    const dialogRef = this.dialog.open(CreateComponentt, {
      width: '400px', // Largeur de la boîte de dialogue
      data: {} // Données que vous souhaitez passer au composant de création
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }
  fetchEvaluation(): void {
    this.evaluationService.getAllEvaluation().subscribe({
        next: (evaluations: evaluation[]) => {
            this.Evaluations = evaluations;
        },
        error: (error: any) => {
            console.error('Error fetching Evaluation', error);
        }
    });
}
openUpdateDialogEval(evaluationId: number): void {
  const dialogRef = this.dialog.open(UpdateComponentt, {
    data: { evaluationId: evaluationId, ...this.Evaluations.find(evaluation => evaluation.id === evaluationId) }
  });

  dialogRef.afterClosed().subscribe(result => {
    console.log('The dialog was closed');
    this.fetchEvaluation();
  });
}
deleteEvaluation(EvaluationId: number): void {
  if (window.confirm('Are you sure you want to delete this Evaluation?')) {
      this.evaluationService.deleteEvaluation(EvaluationId).pipe(
          tap(() => {
              this.Evaluations = this.Evaluations.filter(evaluation => evaluation.id !== EvaluationId);
          }),
          catchError(error => {
              console.error('Error deleting Evaluation:', error);
              throw error; // Rethrow the error to be caught by the subscriber
          })
      ).subscribe();
  }
}
}
