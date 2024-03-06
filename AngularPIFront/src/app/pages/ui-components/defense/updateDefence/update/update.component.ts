import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { defense } from 'src/app/core/Defense';
import { DefenceService } from 'src/app/services/defence.service';
import { CalendarModule } from 'angular-calendar'; // Import the CalendarModule
import { CalendarMonthViewDay } from 'angular-calendar';

@Component({
  selector: 'app-update',
  templateUrl: './update.component.html',
  styleUrls: ['./update.component.scss']
})
export class UpdateComponent {
  viewDate: Date = new Date(); // Define the viewDate property
  usedDates: Date[] = [];

  constructor(
    public dialogref: MatDialogRef<UpdateComponent>,
    @Inject(MAT_DIALOG_DATA) public data: defense,
    private DefenceService: DefenceService
  ) {}
  updateTask(defense: defense): void {
        this.DefenceService.updateDefense(this.data.idDef, defense).subscribe(
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
  dayModifier(day: CalendarMonthViewDay): void {
    if (this.isDateUsed(day.date)) {
      day.cssClass = 'date-used';
    }
  }
getUsedDates(): void {
  this.DefenceService.getUsedDates().subscribe(
    (dates: Date[]) => {
      this.usedDates = dates;
    },
    (error) => {
      console.error('Error fetching used dates:', error);
    }
  );
}

// Vérifier si une date est déjà utilisée
isDateUsed(date: Date): boolean {
  return this.usedDates.some((usedDate) => usedDate.getTime() === date.getTime());
}
isDateDisabled(): boolean {
  return this.usedDates.some(usedDate => usedDate.toDateString() === this.data.dateDefense.toDateString());
}
}
