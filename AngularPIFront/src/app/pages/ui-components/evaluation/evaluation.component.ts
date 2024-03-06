import { HttpClient } from '@angular/common/http';
import { Component, createComponent } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { evaluation } from 'src/app/core/Evaluation';
import { EvaluationService } from 'src/app/services/evaluation.service';
import { CreateComponentt } from 'src/app/pages/ui-components/evaluation/create/create.component' ; 
import { UpdateComponentt } from './update/update.component';
import { catchError, tap } from 'rxjs';
import { HistoriqueDefense } from 'src/app/core/HistoriqueDefense';
import { DefenceService } from 'src/app/services/defence.service';
import { User } from 'src/app/core/User';
@Component({
  selector: 'app-evaluation',
  templateUrl: './evaluation.component.html',
  styleUrls: ['./evaluation.component.scss'] ,
  providers:[EvaluationService]
})
export class EvaluationComponent {
Evaluations :evaluation[] ; 
historiqueDefenseArray: HistoriqueDefense[] ;
query: string;
historiqueDialogShown: boolean = false;
ressource: HistoriqueDefense; // Déclarer la propriété ressource
userList: User[] = [];
searchPerformed = false;
selectedUserIdSuper: number;
constructor(private http: HttpClient,private  evaluationService: EvaluationService,    private defenceService: DefenceService,
  private fb:FormBuilder,private dialog: MatDialog)
   {


    }
    ngOnInit() {
      console.log('On init....');
      //this.defenceService.getAllDefence().subscribe((data: defense[]) => {
        //this.defences = data;
      this.fetchEvaluation() ; 
     // this.getHistoriqueDefenseId(evaluation) ;
     this.loadHistoriqueDefenses()   ;
    /* this.defenceService.searchHistoriques('numeroDeClasse').subscribe(
      (results) => {
        this.historiqueDefenseArray = results;
      },
      (error) => {
        console.error('Erreur lors de la recherche:', error);
      }
    );*/
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
  loadHistoriqueDefenses(): void {
    this.defenceService.getAllHistoriqueDefense().subscribe(
      (historiqueDefenses: HistoriqueDefense[]) => {
        this.historiqueDefenseArray = historiqueDefenses;
        console.log('historiqueDefenseArray:', this.historiqueDefenseArray);
      },
      (error) => {
        console.error('Erreur lors de la récupération de l\'historique des défenses:', error);
      }
    );
  }
  /*getHistoriqueDefenseId(evaluation: evaluation): number {
    // Logique pour récupérer l'ID de l'entité historiqueDefense correspondant à cette évaluation
    // Par exemple, vous pouvez parcourir votre tableau d'historiqueDefense et comparer les données pour trouver la correspondance
    // Supposons que votre tableau d'historiqueDefense s'appelle historiqueDefenseArray
    const matchingHistoriqueDefense = this.historiqueDefenseArray.find(historiqueDefense => historiqueDefense.idDef === evaluation.defense?.idDef);
    if (matchingHistoriqueDefense) {
      return matchingHistoriqueDefense.idDef;
    } else {
      return -1; // Si aucune correspondance n'est trouvée, retournez une valeur par défaut (par exemple, -1)
    }
  }*/
  /*getHistoriqueDefenseId(evaluation: evaluation): number {
    console.log('evaluation.defense?.idDef:', evaluation.defense?.idDef);
    console.log('historiqueDefenseArray:', this.historiqueDefenseArray);

    if (evaluation.defense?.idDef === undefined) {
        return -1; // Si idDef est undefined, retournez une valeur par défaut
    }

    const matchingHistoriqueDefense = this.historiqueDefenseArray.find(historiqueDefense => historiqueDefense.idDef === evaluation.defense?.idDef);
    if (matchingHistoriqueDefense) {
      return matchingHistoriqueDefense.idDef;
    } else {
      return -1; // Si aucune correspondance n'est trouvée, retournez une valeur par défaut (par exemple, -1)
    }
}*/


 /* fetchEvaluation(): void {
    this.evaluationService.getAllEvaluation().subscribe({
        next: (evaluations: evaluation[]) => {
            this.Evaluations = evaluations;
        },
        error: (error: any) => {
            console.error('Error fetching Evaluation', error);
        }
    });
}*/
fetchEvaluation(): void {
  this.evaluationService.getAllEvaluation().subscribe({
      next: (evaluations: evaluation[]) => {
          this.Evaluations = evaluations;
          this.Evaluations.forEach(evaluation => {
              // Mettre à jour numeroStockee pour chaque évaluation si nécessaire
             // evaluation.numeroStockee = evaluation.defense?.idDef ?? -1;
          });
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
getHistoriqueDefenseId(evaluation: evaluation): number {
/*  if (!evaluation.defense?.idDef) {
      return -1; // Si l'ID de la défense est indéfini, retournez une valeur par défaut
  }*/

  const matchingHistoriqueDefense = this.historiqueDefenseArray.find(historiqueDefense => historiqueDefense.idDef === evaluation.HistoriqueDefense?.idDef);
  if (matchingHistoriqueDefense) {
      return matchingHistoriqueDefense.idDef;
  } else {
      return -1; // Si aucune correspondance n'est trouvée, retournez une valeur par défaut (par exemple, -1)
  }
}

search(): void {
  if (!this.evaluation) {
    this.historiqueDefenseArray = []; // Réinitialiser la liste si l'évaluation est indéfinie
    return;
  }

  const historiqueDefenseId = this.getHistoriqueDefenseId(this.evaluation);

  if (historiqueDefenseId === -1) {
    this.historiqueDefenseArray = [];
  } else {
    this.defenceService.(historiqueDefenseId).subscribe(
      historique => {
        this.historiqueDefenseArray = [historique];
      },
      error => {
        console.error('Error getting historique defense:', error);
      }
    );
  }
}
checkForHistoriqueMatch(value: string) {
  // Reset dialog shown flag if value is changed
  if (this.ressource.nomDeEncadrent !== value && this.ressource.UserStudent !== value) {
      this.historiqueDialogShown = false;
  }

  // Check if the dialog has already been shown, if not, proceed
  if (!this.historiqueDialogShown) {
      this.defenceService.searchHistoriques(value).subscribe(matches => {
          if (matches.length > 0) {
              this.historiqueDialogShown = true; // Prevent dialog from showing again
              const dialogRef = this.dialog.open(EvaluationComponent, {
                  width: '250px',
                  data: { historiqueId: matches[0].idDef,  chapterId: this.historiqueDefenseArray } // Assuming matches[0] is a HistoriqueDefense object
              });

          
          }
      });
  }
}

}
