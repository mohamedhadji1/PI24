import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { evaluation } from 'src/app/core/Evaluation';
import { EvaluationService } from 'src/app/services/evaluation.service';

@Component({
  selector: 'app-update',
  templateUrl: './update.component.html',
  styleUrls: ['./update.component.scss']
})
export class UpdateComponentt {
  constructor(
    public dialogref: MatDialogRef<UpdateComponentt>,
    @Inject(MAT_DIALOG_DATA) public data: evaluation,
    private evaluationService: EvaluationService
  ) {}
  updateEvaluation(evaluation: evaluation): void {
    this.evaluationService.updateEvaluation(this.data.id, evaluation).subscribe(
      (response) => {
        console.log('Task updated successfully:', response);
        this.dialogref.close(true);
      },
      (error) => {
        console.error('Error updating task:', error);
      }
    );
  }
  onClose(): void {
    this.dialogref.close(false); 
  }
}
