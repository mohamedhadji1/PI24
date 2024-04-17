import { Component, OnInit,ViewChild } from '@angular/core';
import { MatSnackBar, MatSnackBarConfig } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { Response } from 'src/app/core/Response';
import { ResponseService } from 'src/app/services/response.service';
import { Complaint, TypeRec } from 'src/app/core/Complaint';
import { MatDialog } from '@angular/material/dialog';
import { ComplaintService } from 'src/app/services/complaint.service';
import { MatTableDataSource } from '@angular/material/table';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatPaginator } from '@angular/material/paginator';
import { timeout } from 'rxjs';
import { SatisfactionLevel } from 'src/app/core/SatisfactionLevel';

@Component({
  selector: 'app-response',
  templateUrl: './response.component.html',
  styleUrls: ['./response.component.scss']
})

export class ResponseComponent {

  showDropdown: boolean = false;
  complaints: Complaint[]=[];
  responses: Response[]=[];
  responseId: string;
  complaintForm:any
  filterShearch='';
  note: SatisfactionLevel;
  description: string;
  dataSource = null
  displayedColumns: string[] = [
    'description',	'type','message' ,'name',	'lastname','email',	'status', 'rating'];
  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  constructor(    private snackBar: MatSnackBar  // Injecter MatSnackBar ici
  ,private complaintService: ComplaintService,private responseService: ResponseService, private router: Router, public dialog: MatDialog,private fb: FormBuilder,private route: ActivatedRoute) { }
  ngOnInit() {
    this.getResponse();
    this.getComplaint();
    this.dataSource.paginator = this.paginator;
    this.route.paramMap.subscribe(params => {
      this.responseId = params.get('id');
    });
    };

getComplaint(){
  this.complaintService.getComplaintByUserId(2).subscribe(
    complaints => {
      
      this.complaints = complaints;
      this.dataSource = new MatTableDataSource(complaints);
      this.dataSource.paginator = this.paginator;

    },
    error => {
      console.error('Error fetching Complaints:', error);
    }
  );
}

getResponse(){
  this.responseService.getResponseByUserId(2).subscribe(
    responses => {
      console.log(responses)
      this.responses = responses;
      this.dataSource = new MatTableDataSource(responses);
      this.dataSource.paginator = this.paginator;

    },
    error => {
      console.error('Error fetching Complaints:', error);
    }
  );
}



giveNote(idRep: number, note: string) {
  if (note && note.toUpperCase() === 'SATISFIED') {
    this.responseService.giveNote(idRep, note).subscribe(
      updatedResponse => {
        console.log('Note updated successfully:', updatedResponse);
        const config = new MatSnackBarConfig();
        config.duration = 2000;
        this.snackBar.open('Note updated successfully', 'Close', config);
      },
      error => {
        console.error('Error updating note:', error);
        // Optionally, you can show an error message
        const config = new MatSnackBarConfig();
        config.duration = 2000;
        this.snackBar.open('Error updating note', 'Close', config);
      }
    );
  } else {
    const config = new MatSnackBarConfig();
    config.duration = 2000;
    this.snackBar.open('Note can only be set to TREATED', 'Close', config);
  }
}
  applyFilter() {
    
    this.dataSource.filter = this.filterShearch.trim().toLowerCase();
  }
}
