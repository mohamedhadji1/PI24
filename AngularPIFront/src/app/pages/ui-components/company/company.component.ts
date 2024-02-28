import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { Company } from 'src/app/core/Company';
import { CompanyService } from 'src/app/services/company.service';
import { AddCompanyComponent } from './add-company/add-company.component';
import { UpdateCompanyComponent } from './update-company/update-company.component';

@Component({
  selector: 'app-company',
  templateUrl: './company.component.html',
  styleUrls: ['./company.component.scss']
})
export class CompanyComponent implements OnInit {
  companies: Company[];
  constructor(private CompanyService: CompanyService, private router: Router, public dialog: MatDialog) { }
  openAddDialog(): void {
    const dialogRef = this.dialog.open(AddCompanyComponent, {});

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      this.fetchCompanies();
    });
  }
  Offer(company_id: number):void{
    this.router.navigate(["/ui-components/offer", company_id]);
  }
  openUpdateDialog(id: number): void {
    const dialogRef = this.dialog.open(UpdateCompanyComponent, {
      data: { id: id, ...this.companies.find(Company => Company.id === id) }
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      this.fetchCompanies();
    });
  }
  ngOnInit(): void {
    this.fetchCompanies();
  }

  fetchCompanies(): void {
    this.CompanyService.getAllCompanies().subscribe(
        companies => {
        this.companies = companies;
      },
      error => {
        console.error('Error fetching companies:', error);
      }
    );
  }
  deleteCompany(id: number): void {
    if (confirm('Are you sure you want to delete this company?')) {
      this.CompanyService.deleteCompany(id).subscribe(
        () => {
          this.companies = this.companies.filter(Company => Company.id !== id);
        },
        error => {
          console.error('Error deleting task:', error);
        }
      );
    }
  }

  hidden = false;

  toggleBadgeVisibility() {
    this.hidden = !this.hidden;
  }
}
