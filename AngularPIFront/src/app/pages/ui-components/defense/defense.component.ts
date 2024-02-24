import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { MatDialog } from '@angular/material/dialog';
import { CreateComponent } from 'src/app/pages/ui-components/defense/createdefense/create/create.component';
import { ObjectId } from 'mongoose';
import { DefenceService } from 'src/app/services/defence.service';
import { defense } from 'src/app/core/Defense';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { tap, catchError } from 'rxjs/operators';

@Component({
  selector: 'app-defense',
  templateUrl: './defense.component.html',
  styleUrls: ['./defense.component.scss'],
  providers:[DefenceService]
})
export class DefenseComponent implements OnInit{
  defences: defense[] = [];
  //DefenceForm:FormGroup ; 
  showForm: boolean = true;
  idDef: ObjectId;
 
  //title ="defence-app";
  //defences: Object[]=[] ; 
  constructor(private http: HttpClient,private  defenceService: DefenceService,private fb:FormBuilder,private dialog: MatDialog)
   {


    }
    openCreateDialog(): void {
      const dialogRef = this.dialog.open(CreateComponent, {
        width: '400px', // Largeur de la boîte de dialogue
        data: {} // Données que vous souhaitez passer au composant de création
      });
  
      dialogRef.afterClosed().subscribe(result => {
        console.log('The dialog was closed');
      });
    }
  

    ngOnInit() {
      console.log('On init....');
      //this.defenceService.getAllDefence().subscribe((data: defense[]) => {
        //this.defences = data;
       this.fetchDefence() ; 
      

  }
  fetchDefence(): void {
    this.defenceService.getAllDefence().subscribe({
      next: (defences: defense[]) => {
        this.defences = defences;
        console.log('defences:', this.defences); // Vérifier les données récupérées
  
        // Parcourir les défenses pour afficher les identifiants
        this.defences.forEach(defense => {
          console.log('defense:', defense); // Afficher l'objet defense complet
          const idDefString: string = JSON.stringify(defense.idDef); // Convertir ObjectId en chaîne de caractères
          console.log('idDefString:', idDefString);

        });
        
      
      },
      error: (error: any) => {
        console.error('Error fetching defense:', error);
      }
    });

  
  }
  deleteDefence(DefenceId: number): void {
    if (window.confirm('Are you sure you want to delete this Defence?')) {
        this.defenceService.deleteDefence(DefenceId).pipe(
            tap(() => {
                this.defences = this.defences.filter(defence => defence.idDef !== DefenceId);
            }),
            catchError(error => {
                console.error('Error deleting Defence:', error);
                throw error; // Rethrow the error to be caught by the subscriber
            })
        ).subscribe();
    }
}


  
  
}
  //hidden = false;

  //toggleBadgeVisibility() {
    //this.hidden = !this.hidden;
  


