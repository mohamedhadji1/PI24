import { Time } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { Route, Router } from '@angular/router';
import { defense } from 'src/app/core/Defense';
import { Role, User } from 'src/app/core/User';
import { DefenceService } from 'src/app/services/defence.service';
import { UserService } from 'src/app/services/user.service';
import * as emailjs from 'emailjs-com';

@Component({
  selector: 'app-create',
  templateUrl: './create.component.html',
  styleUrls: ['./create.component.scss']
})
export class CreateComponent implements OnInit{
  id: number  ; 

  dateDefence: Date;
  timeDefense: string ; 
  numeroDeClasse: number;
  numeroDeBloc :string ; 
  nomDeJuret:User;
  UserStudent :User ;
  nomDeEncadrent:string ;
  remarque :string = '';
  userList: User[] = [];
  //selectedDefenseId: number ;
  //userList: defense[] ;
  usedDefenseIds: User[] ;
  selectedUserId: number;
  //selectedUserId: number ;
  //userList: User[] ;
  //userIds: number[] = [];
  //selectedUserId: number ; 
  constructor(private router: Router,private userService:UserService ,private http: HttpClient,private  defenceService: DefenceService,private fb:FormBuilder,private dialog: MatDialog)
  {
    this.numeroDeClasse = this.generateRandomClasse();
    this.numeroDeBloc = this.generateRandomBloc();
   
    this.timeDefense=this.generateRandomTime() ; 
    //this.userList= [] ;

  }
  
 
ngOnInit ()
{
 //this.loadusers() ;
 //this.loadUsedUsers() ;
 //console.log(this.userList);
//this.addDefense() ; 
this.loadUsers();
}
loadUsers(): void {
  this.userService.getUsers().subscribe(
    (users: User[]) => {
      this.userList = users; // Utiliser userList au lieu de usedDefenseIds
    },
    (error) => {
      console.error('Erreur lors de la récupération des utilisateurs:', error);
    }
  );
}
/*addDefense(): void {
  this.timeDefense = this.generateRandomTime();

  console.log('UserList:', this.userList);
  console.log('SelectedUserId:', this.selectedUserId);

  const selectedUser: User | undefined = this.userList.find(user => user.id === +this.selectedUserId);
  if (!selectedUser) {
    console.error('Selected user not found');
    return;
  }

  console.log('SelectedUser:', selectedUser);
  const email: string = selectedUser.email;
  const newDefense: defense = {
    idDef: this.id,
    dateDefense: this.dateDefence,
    timeDefense: this.timeDefense,
    numeroDeBloc: this.numeroDeBloc,
    numeroDeClasse: this.numeroDeClasse,
    nomDeJuret: this.nomDeJuret, // Utilize the selected user object
    UserStudent: selectedUser,
    nomDeEncadrent: this.nomDeEncadrent,
    remarque: this.remarque
  };

  if (!newDefense.idDef) {
    newDefense.numeroDeBloc = this.generateRandomBloc();
    newDefense.numeroDeClasse = this.generateRandomClasse();
  }

  this.defenceService.createDefence(newDefense).subscribe(
    (response) => {
      console.log('newDefense:', newDefense);
      console.log('defense added successfully:', response);
    
      this.selectedUserId =  1;
      this.router.navigate(['/ui-components/defense']);
    },
    (error) => {
      console.error('Error adding defense:', error);
    }
  );
}
*/
addDefense(): void {
  this.timeDefense = this.generateRandomTime();

  console.log('UserList:', this.userList);
  console.log('SelectedUserId:', this.selectedUserId);

  const selectedUser: User | undefined = this.userList.find(user => user.id === +this.selectedUserId);
  if (!selectedUser) {
    console.error('Selected user not found');
    return;
  }

  console.log('SelectedUser:', selectedUser);
  const email: string = selectedUser.email;
  console.log('email:', email);
  const newDefense: defense = {
    idDef: this.id,
    dateDefense: this.dateDefence,
    timeDefense: this.timeDefense,
    numeroDeBloc: this.numeroDeBloc,
    numeroDeClasse: this.numeroDeClasse,
    nomDeJuret: this.nomDeJuret, // Utilize the selected user object
    UserStudent: selectedUser,
    nomDeEncadrent: this.nomDeEncadrent,
    remarque: this.remarque
  };

  if (!newDefense.idDef) {
    newDefense.numeroDeBloc = this.generateRandomBloc();
    newDefense.numeroDeClasse = this.generateRandomClasse();
  }

  emailjs.send('service_vxn2zgg', 'template_30ljq0h', {
    to_email: email,
    from_name: 'Esprit',
    message: `YOUR DEFENSE IS: Date: ${newDefense.dateDefense}, Heure: ${newDefense.timeDefense}, Bloc: ${newDefense.numeroDeBloc}, Salle: ${newDefense.numeroDeClasse}`
}, 'q1LkbNrd-XG2TeyVd')
  
  
  .then((response) => {
    console.log('E-mail sent successfully:', response);

    // Ajouter la défense après l'envoi de l'e-mail
    this.defenceService.createDefence(newDefense).subscribe(
      (response) => {
        console.log('newDefense:', newDefense);
        console.log('defense added successfully:', response);
      
        this.selectedUserId =  1;
        this.router.navigate(['/ui-components/defense']);
      },
      (error) => {
        console.error('Error adding defense:', error);
      }
    );
  }, (error) => {
    console.error('Error sending e-mail:', error);
  });
}








generateRandomTime(): string {
  const startHour = 13;
  const endHour = 15;
  const startMinute = 0;
  const endMinute = 30;

  const randomHour = Math.floor(Math.random() * (endHour - startHour + 1)) + startHour;
  const randomMinute = Math.floor(Math.random() * (endMinute - startMinute + 1)) + startMinute;

  return `${randomHour.toString().padStart(2, '0')}:${randomMinute.toString().padStart(2, '0')}`;
}


generateRandomBloc(): string {
  const blocs = ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "M"];
  const randomIndex = Math.floor(Math.random() * blocs.length);
  return blocs[randomIndex];
}

generateRandomClasse(): number {
  return Math.floor(Math.random() * 15) + 1; // Valeurs de 01 à 15
}


}




