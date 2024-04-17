import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Complaint, TypeRec } from 'src/app/core/Complaint';
import { Response } from 'src/app/core/Response';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { ComplaintService } from 'src/app/services/complaint.service';
import { ResponseService } from 'src/app/services/response.service';
import { MatTableDataSource } from '@angular/material/table';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatPaginator } from '@angular/material/paginator';
import { timeout } from 'rxjs';
import { ComplaintChartsComponent } from './complaint-charts/complaint-charts.component';




@Component({
  selector: 'app-complaint',
  templateUrl: './complaint.component.html',
  styleUrls: ['./complaint.component.scss']
})
export class ComplaintComponent implements OnInit{
  complaints: Complaint[]=[];
  complaintForm: FormGroup;
  status='b'
  filterShearch=''
@ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
@ViewChild('barChartCanvas') barChartCanvas: ElementRef;
  @ViewChild('pieChartCanvas') pieChartCanvas: ElementRef;
  constructor(private complaintService: ComplaintService,private responseService: ResponseService, private router: Router, public dialog: MatDialog,private fb: FormBuilder) { }

  ngOnInit() {
    this.complaintForm = this.fb.group({
      idComp: [''],
      typeRec: [''],
      description: ['', Validators.required],
      dateComplaint: [new Date(), Validators.required],
      name: ['', Validators.required],
      lastname: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      status: ['IN_PROGRESS', Validators.required],
      message: ['']
    });
  
    this.fetchComplaints();
    this.dataSource.paginator = this.paginator;
  }
    showChart(): void {
      this.dialog.open(ComplaintChartsComponent, {
        width: '400px', // Adjust width as needed
        height: '400px', // Adjust height as needed
      });
    }
/*    openADDDialog(idComp: number): void {
      const dialogRef = this.dialog.open(ResponseComponent, {
        data: { idComp: idComp }
      });
  
      dialogRef.afterClosed().subscribe(result => {
        console.log('The dialog was closed');
        this.fetchComplaints();
      });
    }
*/
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
  dataSource :any

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
        this.dataSource.paginator = this.paginator;

      },
      error => {
        console.error('Error fetching Complaints:', error);
      }
    );
  }
  
}