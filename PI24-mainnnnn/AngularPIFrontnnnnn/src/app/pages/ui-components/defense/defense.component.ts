import { AfterViewInit, ChangeDetectorRef, Component, ElementRef, EventEmitter, OnInit, Output, QueryList, ViewChild, ViewChildren } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { MatDialog } from '@angular/material/dialog';
import { CreateComponent } from 'src/app/pages/ui-components/defense/createdefense/create/create.component';
import { DefenceService } from 'src/app/services/defence.service';
import { defense } from 'src/app/core/Defense';
import { FormBuilder, FormGroup } from '@angular/forms';
import { tap, catchError } from 'rxjs/operators';
import { UpdateComponent } from './updateDefence/update/update.component';
import { CommonModule } from '@angular/common';
import { Angular2CsvComponent, Angular2CsvService } from 'angular2-csv';
import { Angular2CsvModule } from 'angular2-csv';
import { MapsComponent } from './maps/maps.component';
import * as L from 'leaflet';
import 'leaflet-routing-machine';
import { Chart } from 'chart.js';
import { forkJoin } from 'rxjs';
import dayGridPlugin from '@fullcalendar/daygrid';
import timeGridPlugin from '@fullcalendar/timegrid'; // Ensure this is imported
import interactionPlugin from '@fullcalendar/interaction'; // Ensure this is imported
import {
  ChartComponent,
  ApexAxisChartSeries,
  ApexChart,
  ApexXAxis,
  ApexTitleSubtitle,
  ApexDataLabels
} from "ng-apexcharts";
import { HistoriqueDefense } from 'src/app/core/HistoriqueDefense';
export type ChartOptions = {
  series: ApexAxisChartSeries;
  chart: ApexChart;
  xaxis: ApexXAxis;
  title: ApexTitleSubtitle;
};
import {
  ApexLegend,
  ApexStroke,
  ApexTooltip,
  ApexYAxis,
  ApexGrid,
  ApexPlotOptions,
  ApexFill,
  ApexMarkers,
  ApexResponsive,
} from 'ng-apexcharts';
import { CalendarrComponent } from './calendarr/calendarr.component';

export interface salesOverviewChart {
  series: ApexAxisChartSeries;
  chart: ApexChart;
  dataLabels: ApexDataLabels;
  plotOptions: ApexDataLabels;
  yaxis: ApexYAxis;
  xaxis: ApexXAxis;
  fill: ApexFill;
  tooltip: ApexTooltip;
  stroke: ApexStroke;
  legend: ApexLegend;
  grid: ApexGrid;
  marker: ApexMarkers;
}

export interface monthlyChart {
  series: ApexAxisChartSeries;
  chart: ApexChart;
  dataLabels: ApexDataLabels;
  plotOptions: ApexPlotOptions;
  tooltip: ApexTooltip;
  stroke: ApexStroke;
  legend: ApexLegend;
  responsive: ApexResponsive;
}

@Component({
  selector: 'app-defense',
  templateUrl: './defense.component.html',
  styleUrls: ['./defense.component.scss'],
  providers:[DefenceService]
})

export class DefenseComponent implements OnInit, AfterViewInit {
  @Output() calculateRouteEvent: EventEmitter<string> = new EventEmitter<string>();
  @ViewChild(MapsComponent) mapsComponent: MapsComponent;
  @ViewChild('chart') chart: ChartComponent = Object.create(null);
  public monthlyChart!: Partial<monthlyChart> | any;

  defences: defense[];
  searchtext: any;
  filterDate: string = '';
  filterStudent: string = '';
  totalItems: number[] = [];
  paginatedDefenses: defense[][] = [];
  currentPage: number[] = [];
  totalPagess: number[] = [];
    currentIndex: number = 0;
  itemsPerPage: number = 5;
  blocList: defense[] = [];
  selectedDefenceBloc: string = '';
  map: L.Map;
  chartData: number[];
  public chartOptions: Partial<ChartOptions>;
  historiqueDefenses: HistoriqueDefense[] = [];
  private centroidH: L.LatLngExpression = [36.8981970128221, 10.189970708975915];
  private centroidE: L.LatLngExpression = [36.89966874789553, 10.18982196413415];
  private centroidM: L.LatLngExpression = [36.90225021696681, 10.189360434924096];
  private centroidI: L.LatLngExpression = [36.90108025227159, 10.19027638089706];
  private centroidJ: L.LatLngExpression = [36.90108025227159, 10.19027638089706];
  private centroidK: L.LatLngExpression = [36.90108025227159, 10.19027638089706];
  private centroidA: L.LatLngExpression = [36.89913305034116, 10.189278376812915];
  private centroidB: L.LatLngExpression = [36.89913305034116, 10.189278376812915];
  private centroidC: L.LatLngExpression = [36.89913305034116, 10.189278376812915];
  private centroidD: L.LatLngExpression = [36.89913305034116, 10.189278376812915];
  private userLocation: L.LatLngExpression;
  selectedUserBloc: defense | null | undefined = null;

  constructor(private http: HttpClient, private defenceService: DefenceService, private dialog: MatDialog, private cdr: ChangeDetectorRef) {}

  ngOnInit(): void {
    console.log('OnInit....');
    this.fetchDefence();
    this.loadBloc();
  }

  ngAfterViewInit(): void {
    console.log('ngAfterViewInit called');
    this.initMap();
    navigator.geolocation.getCurrentPosition((position) => {
      this.userLocation = [position.coords.latitude, position.coords.longitude];
      this.calculateRoute();
    });
  }

  private initMap(): void {
    this.map = L.map('map', {
      center: this.centroidH,
      zoom: 15
    });

    const tiles = L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      maxZoom: 18,
      minZoom: 10,
      attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
    });

    tiles.addTo(this.map);

    L.Routing.control({
      waypoints: [
        L.latLng(this.centroidH),
        L.latLng(this.centroidE),
        L.latLng(this.centroidM),
        L.latLng(this.centroidI),
        L.latLng(this.centroidJ),
        L.latLng(this.centroidK),
        L.latLng(this.centroidA),
        L.latLng(this.centroidB),
        L.latLng(this.centroidC),
        L.latLng(this.centroidD),
        L.latLng(this.userLocation)
      ]
    }).addTo(this.map);
    console.log("location user", L.latLng(this.userLocation));

    L.marker(this.centroidH).addTo(this.map)
      .bindPopup('Bloc H - Esprit')
      .openPopup();

    L.marker(this.centroidE).addTo(this.map)
      .bindPopup('Bloc E - Esprit')
      .openPopup();

    L.marker(this.centroidM).addTo(this.map)
      .bindPopup('Bloc M - Esprit')
      .openPopup();

    L.marker(this.centroidI).addTo(this.map)
      .bindPopup('Bloc I,J,K - Esprit')
      .openPopup();

    L.marker(this.centroidJ).addTo(this.map)
      .bindPopup('Bloc I,J,K - Esprit')
      .openPopup();

    L.marker(this.centroidK).addTo(this.map)
      .bindPopup('Bloc I,J,K - Esprit')
      .openPopup();

    L.marker(this.centroidA).addTo(this.map)
      .bindPopup('Bloc A,B,C,D- Esprit')
      .openPopup();

    L.marker(this.centroidB).addTo(this.map)
      .bindPopup('Bloc A,B,C,D- Esprit')
      .openPopup();

    L.marker(this.centroidC).addTo(this.map)
      .bindPopup('Bloc A,B,C,D- Esprit')
      .openPopup();

    L.marker(this.centroidD).addTo(this.map)
      .bindPopup('Bloc A,B,C,D- Esprit')
      .openPopup();

    navigator.geolocation.getCurrentPosition((position) => {
      const { latitude, longitude } = position.coords;
      const userLocation: L.LatLngExpression = [latitude, longitude];

      L.marker(userLocation).addTo(this.map)
        .bindPopup('Votre position actuelle')
        .openPopup();

      this.map.setView(userLocation, 15);
    });
    this.calculateRouteEvent.subscribe((numeroDeBloc: string) => {
      const destination: L.LatLngExpression | null = this.getDestinationLatLng(numeroDeBloc);
      if (destination) {
        let destinationLatLng: L.LatLng;
        if (Array.isArray(destination)) {
          destinationLatLng = L.latLng(destination[0], destination[1]);
        } else {
          destinationLatLng = destination as L.LatLng;
        }
        L.Routing.control({
          waypoints: [
            L.latLng(this.userLocation),
            destinationLatLng
          ]
        }).addTo(this.map);
      } else {
        console.error("Destination coordinates not found for the bloc:", numeroDeBloc);
      }
    });
  }

  loadBloc(): void {
    this.defenceService.getAllDefence().subscribe(
      (defenses: defense[]) => {
        this.blocList = defenses;
      },
      (error) => {
        console.error('Erreur lors de la récupération des bloc:', error);
      }
    );
  }
  openCalenderDialog(): void {
    const dialogRef = this.dialog.open(CalendarrComponent, {
      width: '1300px',
      height: '1300px',
      data: {}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      this.fetchDefence();
    });
  }
  openCreateDialog(): void {
    const dialogRef = this.dialog.open(CreateComponent, {
      width: '400px',
      data: {}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      this.fetchDefence();
    });
  }
  openCreateDialogCalendar(): void {
    const dialogRef = this.dialog.open(CalendarrComponent, {
      width: '400px',
      data: {}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      this.fetchDefence();
    });
  }
  openCreateDialogMapslocation(numeroDeBloc: string): void {
    if (!this.blocList || this.blocList.length === 0) {
      console.error('Bloc list not initialized or empty');
      return;
    }

    const selectedBloc: defense | undefined = this.blocList.find(defence => defence.numeroDeBloc === numeroDeBloc);
    this.selectedUserBloc = this.blocList.find(def => def.numeroDeBloc === numeroDeBloc);

    if (!selectedBloc) {
      console.error('Selected Bloc not found');
      return;
    }

    console.log('selectedBloc:', selectedBloc);
    let location: L.LatLngExpression;
    switch (numeroDeBloc) {
      case 'H':
        location = this.centroidH;
        break;
      case 'E':
        location = this.centroidE;
        break;
      case 'M':
        location = this.centroidM;
        break;
      case 'I':
        location = this.centroidI;
        break;
      case 'J':
        location = this.centroidJ;
        break;
      case 'K':
        location = this.centroidK;
        break;
      case 'A':
        location = this.centroidA;
        break;
      case 'B':
        location = this.centroidB;
        break;
      case 'C':
        location = this.centroidC;
        break;
      case 'D':
        location = this.centroidD;
        break;

      default:
        console.error('Unknown bloc:', numeroDeBloc);
        return;
    }

    navigator.geolocation.getCurrentPosition((position) => {
      this.userLocation = [position.coords.latitude, position.coords.longitude];
      this.calculateRoute();
    });

  }

  calculateRoute(): void {
    if (!this.selectedUserBloc || !this.userLocation) {
      console.error("Bloc or user location is not defined.");
      return;
    }

    const start = this.userLocation;
    const end = this.getDestinationLatLng(this.selectedUserBloc.numeroDeBloc);

    if (!end) {
      console.error("Destination coordinates not found for the selected bloc.");
      return;
    }

    const startLatLng = this.convertLatLng(start);
    const endLatLng = this.convertLatLng(end);

    L.Routing.control({
      waypoints: [
        L.latLng(startLatLng),
        L.latLng(endLatLng)
      ],
      routeWhileDragging: true
    }).addTo(this.map);

    const distance = startLatLng.distanceTo(endLatLng);
    console.log('Distance:', distance);
  }

  getDestinationLatLng(numeroDeBloc: string): L.LatLngExpression | null {
    let destination: L.LatLngExpression | null = null;

    switch (numeroDeBloc) {
      case 'H':
        destination = this.centroidH;
        break;
      case 'E':
        destination = this.centroidE;
        break;
      case 'M':
        destination = this.centroidM;
        break;
      case 'I':
        destination = this.centroidI;
        break;
      case 'J':
        destination = this.centroidJ;
        break;
      case 'K':
        destination = this.centroidK;
        break;
      case 'A':
        destination = this.centroidA;
        break;
      case 'B':
        destination = this.centroidB;
        break;
      case 'C':
        destination = this.centroidC;
        break;
      case 'D':
        destination = this.centroidD;
        break;
      default:
        console.error("Destination coordinates not found for the selected bloc.");
        break;
    }

    return destination;
  }
  private convertLatLng(location: L.LatLngExpression): L.LatLng {
    if (Array.isArray(location)) {
      return L.latLng(location[0], location[1]);
    } else {
      console.error("Invalid location coordinates:", location);
      return L.latLng(0, 0); // or any other default value
    }
  }

  deleteDefence(DefenceId: number): void {
    if (window.confirm('Are you sure you want to delete this Defence?')) {
      this.defenceService.deleteDefence(DefenceId).pipe(
        tap(() => {
          this.defences = this.defences.filter(defence => defence.idDef !== DefenceId);
        }),
        catchError(error => {
          console.error('Error deleting Defence:', error);
          throw error;
        })
      ).subscribe(() => {
        this.fetchDefence();
      });
    }
  }

  fetchDefence(): void {
    console.log('Début de la récupération des défenses...');
    this.defenceService.getAllDefence().subscribe({
      next: (defences: defense[]) => {
        console.log('Défenses récupérées avec succès:', defences);
        this.defences = defences;
        for (let index = 0; index < defences.length; index++) {
          this.totalItems[index] = defences.length;
          this.currentPage[index] = 1;
          this.totalPagess[index] = Math.ceil(defences.length / this.itemsPerPage);
          this.paginateDefenses(defences, index);
        }
      },
      error: (error: any) => {
        console.error('Erreur lors de la récupération des défenses:', error);
      }
    });
  }

  openUpdateDialog(DefenceId: number): void {
    const dialogRef = this.dialog.open(UpdateComponent, {
      data: { DefenceId: DefenceId, ...this.defences.find(defence => defence.idDef === DefenceId) }
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      this.fetchDefence();
    });
  }

  applyFilters(): void {
    let filteredDefenses = this.defences;

    if (this.filterDate) {
      const filterDate = new Date(this.filterDate);
      filteredDefenses = filteredDefenses.filter(defense => {
        const defenseDate = new Date(defense.dateDefense);
        return defenseDate.getTime() === filterDate.getTime();
      });
    }

    if (this.filterStudent) {
      filteredDefenses = filteredDefenses.filter(defense => defense.UserStudent?.id === +this.filterStudent);
    }

    for (let index = 0; index < this.defences.length; index++) {
      this.paginateDefenses(filteredDefenses, index);
    }
  }

  paginateDefenses(filteredDefenses: defense[], index: number): void {
    const startIndex = (this.currentPage[index] - 1) * this.itemsPerPage;
    const endIndex = startIndex + this.itemsPerPage;
    this.paginatedDefenses[index] = filteredDefenses.slice(startIndex, endIndex);
    this.totalItems[index] = filteredDefenses.length;
    this.totalPagess[index] = Math.ceil(filteredDefenses.length / this.itemsPerPage);
  }

  previousPage(index: number): void {
    if (this.currentPage[index] > 1) {
      this.currentPage[index]--;
      this.paginateDefenses(this.defences, index);
    }
  }

  nextPage(index: number): void {
    const totalPages = this.totalPagess[index];
    if (this.currentPage[index] < totalPages) {
      this.currentPage[index]++;
      this.paginateDefenses(this.defences, index);
    }
  }

  get totalPages(): number {
    return Math.ceil(this.totalItems[this.currentIndex] / this.itemsPerPage);
  }

  getWeekNumber(date: Date): number {
    const d = new Date(Date.UTC(date.getFullYear(), date.getMonth(), date.getDate()));
    const dayNum = d.getUTCDay() || 7;
    d.setUTCDate(d.getUTCDate() + 4 - dayNum);
    const yearStart = new Date(Date.UTC(d.getUTCFullYear(), 0, 1));
    return Math.ceil((((d.getTime() - yearStart.getTime()) / 86400000) + 1) / 7);
  }
}
