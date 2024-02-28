import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { MatDialog } from '@angular/material/dialog';
import { CreateComponent } from 'src/app/pages/ui-components/defense/createdefense/create/create.component';
import { ObjectId } from 'mongoose';
import { DefenceService } from 'src/app/services/defence.service';
import { defense } from 'src/app/core/Defense';
import { FormBuilder, FormGroup } from '@angular/forms';
import { tap, catchError } from 'rxjs/operators';
import { UpdateComponent } from './updateDefence/update/update.component';
import { CommonModule } from '@angular/common';
@Component({
  selector: 'app-defense',
  templateUrl: './defense.component.html',
  styleUrls: ['./defense.component.scss'],
  providers:[DefenceService]
})
export class DefenseComponent implements OnInit{
  defences: defense[] ;
  searchtext:any ; 
  
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
        },
        error: (error: any) => {
            console.error('Error fetching defences:', error);
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
openUpdateDialog(DefenceId: number): void {
  const dialogRef = this.dialog.open(UpdateComponent, {
    data: { DefenceId: DefenceId, ...this.defences.find(defence => defence.idDef === DefenceId) }
  });

  dialogRef.afterClosed().subscribe(result => {
    console.log('The dialog was closed');
    this.fetchDefence();
  });
}

  
  
}
  
  


