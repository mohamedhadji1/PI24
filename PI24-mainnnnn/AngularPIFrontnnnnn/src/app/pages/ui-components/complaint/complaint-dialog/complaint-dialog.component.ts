import { Component, ElementRef, Inject, OnInit, ViewChild } from '@angular/core';
import { Complaint, TypeRec } from 'src/app/core/Complaint';
import { Response } from 'src/app/core/Response';
import { Router } from '@angular/router';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ComplaintService } from 'src/app/services/complaint.service';
import { ResponseService } from 'src/app/services/response.service';
import { MatTableDataSource } from '@angular/material/table';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatPaginator } from '@angular/material/paginator';
import { timeout } from 'rxjs';
import * as $ from 'jquery';
import { ComplaintChartsComponent } from '../complaint-charts/complaint-charts.component';

@Component({
  selector: 'app-complaint-dialog',
  templateUrl: './complaint-dialog.component.html',
  styleUrls: ['./complaint-dialog.component.scss']
})
export class ComplaintDialogComponent implements OnInit  {
  complaints: Complaint[]=[];
  complaintForm: FormGroup;
  status='b'
  filterShearch='';
  dataSource :any;
  @ViewChild('barChartCanvas') barChartCanvas: ElementRef;
  @ViewChild('pieChartCanvas') pieChartCanvas: ElementRef;
  constructor(private complaintService: ComplaintService,private responseService: ResponseService, private router: Router, public dialog: MatDialog,private fb: FormBuilder, public dialogRef: MatDialogRef<ComplaintDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { complaint: Complaint },
    ) { }

  initForm(comp: Complaint): void {
    console.log(comp);
    this.status = comp.status;
    this.complaintForm = this.fb.group({
      idComp: [comp.idComp],
      typeRec: [comp.typeRec],
      description: [comp.description, Validators.required],
      dateComplaint: [comp.dateComplaint, Validators.required],
      name: [comp.name, Validators.required],
      lastname: [comp.lastname, Validators.required],
      email: [comp.email, [Validators.required, Validators.email]],
      status: [comp.status, Validators.required],
      message: [comp.message]
    });
   
    // Open the modal
    
  }
  ngOnInit(): void {
    this.initForm(this.data.complaint);
  }

  saveComplaint(){
    this.complaintService.updateComplaint(this.complaintForm.value.idComp,this.complaintForm.value).subscribe(
      res => {
        console.log(res);
      },
      error => {
        console.error('Error updating Complaints:', error);
      }
    );
    
    setTimeout(()=>{location.reload()},2000)
  }
  fetchComplaints(): void {
    this.complaintService.getAllComplaints().subscribe(
      complaints => {
        console.log(complaints[0]);
        
        this.complaints = complaints;
        this.dataSource = new MatTableDataSource(complaints);

      },
      error => {
        console.error('Error fetching Complaints:', error);
      }
    );
  }
  showChart(): void {
    this.dialog.open(ComplaintChartsComponent, {
      width: '400px', // Adjust width as needed
      height: '400px', // Adjust height as needed
    });
  }
  getComplaints() {
    this.complaintService.getAllComplaints().subscribe((src: Complaint[]) => {
      console.log(src);
      this.complaints = src;
    });
  }  
  displayedColumns: string[] = [
    'description',	'type','dateComplaint' ,'name',	'lastname','email',	'status', 'rating',	'idComp'
  ];

  applyFilter() {
    
    this.dataSource.filter = this.filterShearch.trim().toLowerCase();
  }
  deleteComplaint(idComp : number) {
    if (confirm('Voulez-vous supprimer cette complaint ?')) {
      this.complaintService.deleteComplaint(idComp).subscribe(() => {
        alert('Suppression réussie');
        window.location.reload();
      });
    }
  }

 
 
}
