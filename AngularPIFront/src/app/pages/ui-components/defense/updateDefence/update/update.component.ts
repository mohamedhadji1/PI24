import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { defense } from 'src/app/core/Defense';
import { DefenceService } from 'src/app/services/defence.service';

@Component({
  selector: 'app-update',
  templateUrl: './update.component.html',
  styleUrls: ['./update.component.scss']
})
export class UpdateComponent {
  constructor(
    public dialogref: MatDialogRef<UpdateComponent>,
    @Inject(MAT_DIALOG_DATA) public data: defense,
    private taskService: DefenceService
  ) {}
  updateTask(defense: defense): void {
    this.taskService.updatedefense(this.data.idDef, defense).subscribe(
      (response) => {
        console.log('Defence updated successfully:', response);
        this.dialogref.close(true);
      },
      (error) => {
        console.error('Error updating defence:', error);
      }
    );
  }
  onClose(): void {
    this.dialogref.close(false); 
  }
}
