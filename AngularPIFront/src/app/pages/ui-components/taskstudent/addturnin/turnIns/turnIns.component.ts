import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TurnIn } from 'src/app/core/TurnIn';
import { TurnInService } from 'src/app/services/TurnIn.service';

@Component({
  selector: 'app-turnIns',
  templateUrl: './turnIns.component.html',
  styleUrls: ['./turnIns.component.css']
})
export class TurnInsComponent implements OnInit {
  turns: TurnIn[];
  constructor(private tuninservice : TurnInService, private router: Router) { }

  ngOnInit() {
    this.fetchTurnIn();
  }
  fetchTurnIn(): void {
    this.tuninservice.getAllTurnIns().subscribe(
      turns => {
        this.turns = turns;
      },
      error => {
        console.error('Error fetching turns:', error);
      }
    );
  }
}
