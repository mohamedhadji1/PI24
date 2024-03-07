import { AfterViewInit, Component, ElementRef, EventEmitter, OnInit, Output, QueryList, ViewChild, ViewChildren } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { MatDialog } from '@angular/material/dialog';
import { CreateComponent } from 'src/app/pages/ui-components/defense/createdefense/create/create.component';
import { ObjectId } from 'mongoose';
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

//import { ChartOptions } from 'chart.js';
import {
  ChartComponent,
  ApexAxisChartSeries,
  ApexChart,
  ApexXAxis,
  ApexTitleSubtitle,
  ApexDataLabels
} from "ng-apexcharts";
import { HistoriqueDefense } from 'src/app/core/HistoriqueDefense';
//import { FullCalendarComponent } from '@fullcalendar/angular';
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



// ecommerce card

@Component({
  selector: 'app-defense',
  templateUrl: './defense.component.html',
  styleUrls: ['./defense.component.scss'],
  providers:[DefenceService]
})



export class DefenseComponent implements OnInit,AfterViewInit {
  @Output() calculateRouteEvent: EventEmitter<string> = new EventEmitter<string>();
  @ViewChild(MapsComponent) mapsComponent: MapsComponent;
 // @ViewChildren('chart') chartElements: QueryList<ElementRef<HTMLCanvasElement>>;

 @ViewChild('chart') chart: ChartComponent = Object.create(null);
 public monthlyChart!: Partial<monthlyChart> | any;
 
  defences: defense[];
  searchtext: any;
  filterDate: string = '';
  filterStudent: string = '';
 // itemsPerPage: number = 10;
  totalItems: number[] = [];
  paginatedDefenses: defense[][] = [];
  currentPage: number[][] = [];
  totalPagess: number[][] = [];
  currentIndex: number = 0;
  itemsPerPage: number = 5;
  blocList: defense[] = [];
  selectedDefenceBloc: string ='' ;
  map: L.Map ; 
  chartData: number[]; 
  public chartOptions: Partial<ChartOptions>;
  historiqueDefenses: HistoriqueDefense[] = []; // Déclarer la propriété historiqueDefenses

 // private map: L.Map;
  private centroidH: L.LatLngExpression = [36.8981970128221, 10.189970708975915];
  private centroidE: L.LatLngExpression = [36.89966874789553, 10.18982196413415];
  private centroidM: L.LatLngExpression = [36.90225021696681, 10.189360434924096];
  private centroidI: L.LatLngExpression = [ 36.90108025227159, 10.19027638089706];
  private centroidJ: L.LatLngExpression = [ 36.90108025227159, 10.19027638089706];
  private centroidK: L.LatLngExpression = [ 36.90108025227159, 10.19027638089706];
  private centroidA: L.LatLngExpression = [ 36.89913305034116, 10.189278376812915];
  private centroidB: L.LatLngExpression = [ 36.89913305034116, 10.189278376812915];
  private centroidC: L.LatLngExpression = [ 36.89913305034116, 10.189278376812915];
  private centroidD: L.LatLngExpression = [ 36.89913305034116, 10.189278376812915];



   
  private userLocation: L.LatLngExpression;
  
  //const bloc: string = this.selectedUserBloc ? this.selectedUserBloc.numeroDeBloc : '';
  selectedUserBloc: defense | null | undefined = null;
  //const map = this.mapsComponent.getMap(); // Obtenir l'instance de la carte

constructor(private http: HttpClient, private defenceService: DefenceService, private dialog: MatDialog) {
  
  

}
ngOnInit(): void {
  console.log('OnInit....');
  this.fetchDefence();
  this.loadBloc();
  //this.initCharts() ; 
  // Écouter l'événement pour calculer l'itinéraire
  //if (this.selectedUserBloc) {
    //this.initMap(); // Initialisez d'abord la carte
    //this.calculateRoute();
  //}
  
}

ngAfterViewInit(): void {
  console.log('ngAfterViewInit called');
  this.initMap(); // Initialisez la carte dans ngAfterViewInit si elle dépend des éléments de la vue

  navigator.geolocation.getCurrentPosition((position) => {
    this.userLocation = [position.coords.latitude, position.coords.longitude];
    this.calculateRoute(); // Appelez calculateRoute() après avoir obtenu la position de l'utilisateur
  });

 /* setTimeout(() => {
    this.initCharts();
  }, 0);*/
}

private initMap(): void {
  this.map = L.map('map', {
    center: this.centroidH, // Changez cette ligne pour utiliser le bloc que vous souhaitez afficher initialement
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
console.log("location user",L.latLng(this.userLocation)) ; 
  // Ajouter le marqueur pour le bloc H
  L.marker(this.centroidH).addTo(this.map)
    .bindPopup('Bloc H - Esprit')
    .openPopup();

  // Ajouter le marqueur pour le bloc E
  L.marker(this.centroidE).addTo(this.map)
    .bindPopup('Bloc E - Esprit')
    .openPopup();

  // Ajouter le marqueur pour le bloc M
  L.marker(this.centroidM).addTo(this.map)
    .bindPopup('Bloc M - Esprit')
    .openPopup();
     // Ajouter le marqueur pour le bloc I
  L.marker(this.centroidI).addTo(this.map)
  .bindPopup('Bloc I,J,K - Esprit')
  .openPopup();
   // Ajouter le marqueur pour le bloc J
   L.marker(this.centroidJ).addTo(this.map)
   .bindPopup('Bloc I,J,K - Esprit')
   .openPopup();
    // Ajouter le marqueur pour le bloc K
  L.marker(this.centroidK).addTo(this.map)
  .bindPopup('Bloc I,J,K - Esprit')
  .openPopup();
      // Ajouter le marqueur pour le bloc A
      L.marker(this.centroidA).addTo(this.map)
      .bindPopup('Bloc A,B,C,D- Esprit')
      .openPopup();
          // Ajouter le marqueur pour le bloc B
  L.marker(this.centroidB).addTo(this.map)
  .bindPopup('Bloc A,B,C,D- Esprit')
  .openPopup();
      // Ajouter le marqueur pour le bloc C
      L.marker(this.centroidC).addTo(this.map)
      .bindPopup('Bloc A,B,C,D- Esprit')
      .openPopup();
          // Ajouter le marqueur pour le bloc D
  L.marker(this.centroidD).addTo(this.map)
  .bindPopup('Bloc A,B,C,D- Esprit')
  .openPopup();

  

  // Ajouter le marqueur de votre position actuelle
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
        this.blocList = defenses; // Utiliser userList au lieu de usedDefenseIds
      },
      (error) => {
        console.error('Erreur lors de la récupération des bloc:', error);
      }
    );
  }
  openCreateDialog(): void {
    const dialogRef = this.dialog.open(CreateComponent, {
      width: '400px',
      data: {}
    });
  
  
    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      this.fetchDefence(); // Rafraîchir les données après la création
    });
  }
  /*openCreateDialogMaps(): void {
    const dialogRef = this.dialog.open(MapsComponent, {
      width: '700px',
      height:'700px' , 
      data: {}
    });
  }*/
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
  
   /* const dialogRef = this.dialog.open(MapsComponent, {
      width: '700px',
      height: '700px',
      data: { location }
    });
  
    dialogRef.afterClosed().subscribe(result => {
      console.log('The maps was closed');
      this.fetchDefence(); // Refresh data after closing the map
      this.calculateRoute(); // Calculate the route when the map is closed
    });*/
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
  
    const start = this.userLocation; // Utilisez directement la position actuelle
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
  /*transferDefenses() {
    //this.defenceService.transferDefensesToHistory().subscribe(
      Response => {
      //  console.log('Transfert réussi :', response);
        // Gérer la réponse de l'API ici, par exemple afficher un message de succès à l'utilisateur
      },
      error => {
        console.error('Erreur lors du transfert :', error);
        // Gérer les erreurs ici, par exemple afficher un message d'erreur à l'utilisateur
      }
    );
  }*/
  
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
  /*
  openCreateDialogMapslocation(numeroDeBloc: string): void {
    // Assurez-vous que this.blocList est correctement rempli avec les défenses récupérées de la base de données avant d'appeler find()
    if (!this.blocList || this.blocList.length === 0) {
        console.error('Bloc list not initialized or empty');
        return;
    }
    if (this.map) {
      L.Routing.control({
        waypoints: [
          L.latLng(51.5, -0.1),
          L.latLng(51.52, -0.12)
        ],
        routeWhileDragging: true
      }).addTo(this.map);
    } else {
      console.error('La carte n\'est pas définie.');
    }
    const selectedBloc: defense | undefined = this.blocList.find(defence => defence.numeroDeBloc === numeroDeBloc); // Utilisez numeroDeBloc ici
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
        default:
            location = [0, 0]; // Coordonnées par défaut si le bloc n'est pas reconnu
            break;
    }

    // Appeler la méthode loadBloc
    this.loadBloc();

    const dialogRef = this.dialog.open(MapsComponent, {
        width: '700px',
        height: '700px',
        data: { location }
    });

    dialogRef.afterClosed().subscribe(result => {
        console.log('The maps was closed');
        this.fetchDefence(); // Rafraîchir les données après la création
        this.calculateRoute(); // Calculer le chemin lorsque la carte est fermée
    });

    // Utiliser votre propre serveur OSRM ou un service payant
    // Utiliser votre propre serveur OSRM ou un service payant
    L.Routing.control({
      waypoints: [
          L.latLng(51.5, -0.1),
          L.latLng(51.52, -0.12)
      ],
      routeWhileDragging: true
    }).addTo(this.map);
    this.calculateRouteEvent.emit(numeroDeBloc);


}
calculateRouteToBloc(numeroDeBloc: string): void {
  this.calculateRouteEvent.emit(numeroDeBloc);
}



calculateRoute(): void {
  if (!this.selectedUserBloc || !this.userLocation) {
    console.error("Bloc or user location is not defined.");
    return;
  }

  const start = this.convertLatLng(this.userLocation);
  const end = this.getDestinationLatLng(this.selectedUserBloc.numeroDeBloc);

  if (!end) {
    console.error("Destination coordinates not found for the selected bloc.");
    return;
  }

  const startLatLng = this.convertLatLng(start);
  const endLatLng = this.convertLatLng(end);

  const distance = startLatLng.distanceTo(endLatLng);
  console.log('Distance:', distance);

  setTimeout(() => {
    const route = L.Routing.control({
      waypoints: [endLatLng, startLatLng],
      routeWhileDragging: true,
      lineOptions: {
        styles: [
          { color: 'blue', weight: 5 },
          { color: 'red', weight: 3, opacity: 1, lineJoin: 'round' },
        ],
        extendToWaypoints: true,
        missingRouteTolerance: 50
      },
    }).addTo(this.map);
  }, 1000);
}

  
  
  private convertLatLng(location: L.LatLngExpression): L.LatLng {
    if (Array.isArray(location)) {
      console.error(" location coordinates:", location);

      return L.latLng(location[0], location[1]);

    } else {
      console.error("Invalid location coordinates:", location);
      return L.latLng(0, 0); // or any other default value
    }
  }
  
 
  
  private getDestinationLatLng(numeroDeBloc: string): L.LatLngExpression | null {
    switch (numeroDeBloc) {
      case "H":
        return this.centroidH;
      case "E":
        return this.centroidE;
      case "M":
        return this.centroidM;
      default:
        console.error("Destination coordinates not found for the bloc:", numeroDeBloc);
        return null;
    }
  }
  */
  /*fetchHistoriqueDefenses(ids: number[]): void {
    const observables: Observable<HistoriqueDefense>[] = [];
    for (const id of ids) {
      observables.push(this.defenceService.gethistoriqueDefenceByIdById(id));
    }
    forkJoin(observables).subscribe({
      next: (historiqueDefenses: HistoriqueDefense[]) => {
        // Faites quelque chose avec les défenses historiques récupérées
        this.historiqueDefenses = historiqueDefenses;
      },
      error: (error: any) => {
        console.error('Error fetching historique defenses:', error);
      }
    });
  }

  getHistoriqueDefenseById(id: number): void {
    this.defenceService.gethistoriqueDefenceByIdById(id).subscribe({
      next: (historiqueDefense: HistoriqueDefense) => {
        // Faites quelque chose avec la défense historique récupérée, comme l'ajouter à une liste ou la traiter d'une autre manière
      },

      error: (error: any) => {
        console.error('Error fetching historique defense:', error);
      }
    });
  }*/

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
        this.fetchDefence(); // Rafraîchir les données après la suppression
      });
    }
  }

 fetchDefence(): void {

        console.log('Début de la récupération des défenses...');
        this.defenceService.getAllDefence().subscribe({
          next: (defences: defense[]) => {
            console.log('Défenses récupérées avec succès:', defences);
            this.defences = defences;
            const observables: Observable<HistoriqueDefense>[] = [];   
        for (let index = 0; index < defences.length; index++) {
          this.totalItems[index] = defences.length;
          this.currentPage[index] = [1];
          this.totalPagess[index] = [Math.ceil(defences.length / this.itemsPerPage)];
          this.paginateDefenses(defences, index);
          
          // Créer un observable pour chaque appel à la fonction
          //observables.push(this.defenceService.gethistoriqueDefenceByIdById(defences[index].idDef));
        }
        
        
   /* forkJoin(observables).subscribe({
      next: (historiqueDefenses: HistoriqueDefense[]) => {
        console.log('Défenses historiques récupérées avec succès:', historiqueDefenses);
        this.historiqueDefenses = historiqueDefenses;
      },
      error: (error: any) => {
        console.error('Erreur lors de la récupération des défenses historiques:', error);
      }
    });*/
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
    const startIndex = (this.currentPage[index][0] - 1) * this.itemsPerPage;
    const endIndex = startIndex + this.itemsPerPage;
    this.paginatedDefenses[index] = filteredDefenses.slice(startIndex, endIndex);
  
    // Mise à jour des données de pagination
    this.totalItems[index] = filteredDefenses.length;
    this.totalPagess[index] = [Math.ceil(filteredDefenses.length / this.itemsPerPage)];
  }
  

  previousPage(index: number): void {
    if (this.currentPage[index][0] > 1) {
      this.currentPage[index][0]--;
      this.paginateDefenses(this.defences, index);
    }
  }

  nextPage(index: number): void {
    const totalPages = this.totalPagess[index][0];
    if (this.currentPage[index][0] < totalPages) {
      this.currentPage[index][0]++;
      this.paginateDefenses(this.defences, index);
    }
  }

  get totalPages(): number {
    return Math.ceil(this.totalItems[this.currentIndex] / this.itemsPerPage);
  }
 
  
  // Fonction pour obtenir le numéro de la semaine à partir d'une date
  getWeekNumber(date: Date): number {
    const d = new Date(Date.UTC(date.getFullYear(), date.getMonth(), date.getDate()));
    const dayNum = d.getUTCDay() || 7;
    d.setUTCDate(d.getUTCDate() + 4 - dayNum);
    const yearStart = new Date(Date.UTC(d.getUTCFullYear(), 0, 1));
    return Math.ceil((((d.getTime() - yearStart.getTime()) / 86400000) + 1) / 7);
  }
  

}


  
  


