import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { OfferService } from 'src/app/services/offer.service';
import { Offer } from 'src/app/core/Offer';

@Component({
  selector: 'app-offer',
  templateUrl: './offer.component.html',
  styleUrls: ['./offer.component.scss']
})
export class OfferComponent implements OnInit {
  offers: Offer[];

  constructor(
    private offerService: OfferService,
    private router: Router,
    public dialog: MatDialog,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    console.log("fetching offers of company");
    const companyId = this.route.snapshot.params["id"];
    console.log(companyId);
    this.offerService.getOffersByCompany(companyId).subscribe(offers => {
      this.offers = offers;
    });
  }
}