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
import { DefenceService } from 'src/app/services/defence.service';

@Component({
  selector: 'app-createe',
  templateUrl: './create.component.html',
  styleUrls: ['./create.component.scss']
})

export class CreateComponentt {
  id: number;
  defense: defense; 
  tutor: User;
  student: User;
  note: number; 
  description: string;
  selectedDefenseId: number ;
  defenseList: defense[] ;
  usedDefenseIds: number[] = [];
  

  
  constructor(
    private router: Router,
    private http: HttpClient,
    private defenceService: DefenceService,
    private evaluationService: EvaluationService,
    private fb: FormBuilder,
    private dialog: MatDialog
  ) {
    this.defenseList = [];
  }
  
  ngOnInit(): void {
   /* this.defenceService.getAllDefence().subscribe(
      (defenses: defense[]) => {
        this.defenseList = defenses;
      },
      (error) => {
        console.error('Erreur lors de la récupération des défenses:', error);
      }
    );*/
    this.loadDefenses() ; 
    this.loadUsedDefenseIds();
  }

  loadDefenses(): void {
    this.defenceService.getAllDefence().subscribe(
      (defenses: defense[]) => {
        this.defenseList = defenses;
      },
      (error) => {
        console.error('Erreur lors de la récupération des défenses:', error);
      }
    );
  }
  loadUsedDefenseIds(): void {
    // Appel à la méthode du service pour récupérer les IDs de défense déjà utilisés
    this.evaluationService.getUsedDefenseIds().subscribe(
      (usedIds: number[]) => {
        this.usedDefenseIds = usedIds;
      },
      (error) => {
        console.error('Erreur lors de la récupération des IDs de défense utilisés:', error);
      }
    );
  }
  

  addevaluation(): void {
    const newevaluation: evaluation = {
      id: this.id, 
      defense: { idDef: this.selectedDefenseId }, // Utilisez l'ID de la défense sélectionnée
      tutor: this.tutor, 
      student: this.student, 
      note: this.note, 
      description: this.description
    };
    
    this.evaluationService.createEvaluation(newevaluation).subscribe(
      (response) => {
        console.log('Tâche ajoutée avec succès :', response);
        // Supprimez l'élément sélectionné de la liste des défenses après l'ajout réussi
        if (this.selectedDefenseId !== null) {
          this.defenseList = this.defenseList.filter(defense => defense.idDef !== this.selectedDefenseId);
        }
        // Réinitialisez l'ID sélectionné à null
        this.selectedDefenseId =0;
        // Naviguez vers la page d'évaluation
        this.router.navigate(['/ui-components/evaluation']);
      },
      (error) => {
        console.error('Erreur lors de l\'ajout de l\'évaluation :', error);
      }
    );
  }
  
}
