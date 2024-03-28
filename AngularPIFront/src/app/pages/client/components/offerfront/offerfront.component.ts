import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { OfferService } from 'src/app/services/offer.service';
import { Offer } from 'src/app/core/Offer';

@Component({
  selector: 'app-offer',
  templateUrl: './offerfront.component.html',
  styleUrls: ['./offerfront.component.scss']
})
export class OfferfrontComponent implements OnInit {
  offers: Offer[];
  companyId: number

  constructor(
    private offerService: OfferService,
    private router: Router,
    public dialog: MatDialog,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    console.log("fetching offers of company");
    const companyId = this.route.snapshot.params["id"];
    this.companyId=companyId;
    console.log(companyId);
    this.offerService.getOffersByCompany(companyId).subscribe(offers => {
      this.offers = offers;
    });
  }
}

  