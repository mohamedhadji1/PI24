import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { User } from '../User';
import { UserService } from 'src/app/services/user.service';


@Component({
  selector: 'app-update-user',
  templateUrl: './update-user.component.html',
  styleUrls: ['./update-user.component.scss']
})
export class UpdateUserComponent {
  constructor(
    public dialogRef: MatDialogRef<UpdateUserComponent>,
    @Inject(MAT_DIALOG_DATA) public data: User,
    private userservice: UserService
  ) {}
 
  updateUser(user: User): void {
    this.userservice.updateUser(this.data.id, user).subscribe(
      (response: any) => {
        console.log('User updated successfully:', response);
        this.dialogRef.close(true);
      },
      (error: any) => {
        console.error('Error updating user:', error);
      }
    );
  }
  
  onClose(): void {
    this.dialogRef.close(false); 
  }
}
