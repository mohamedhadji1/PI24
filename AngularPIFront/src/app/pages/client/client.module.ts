import { ComplaintComponent } from './../ui-components/complaint/complaint.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavbarComponent } from './navbar/navbar.component';
import { ClientRoutingModule } from './client-routing.module';
import { MainComponent } from './main/main.component';
import { VideoComponent } from './video/video.component';
// import { FooterComponent } from './footer/footer.component';
import { CompanyfrontComponent } from './components/companyfront/companyfront.component';
import { ComplaintfrontComponent } from './components/complaintfront/complaintfront.component';
import { EventfrontComponent } from './components/eventfront/eventfront.component';
import { FooterComponent } from './footer/footer.component';

@NgModule({
  declarations: [
    NavbarComponent,
    MainComponent,
    VideoComponent,
    CompanyfrontComponent,
    ComplaintfrontComponent,
    EventfrontComponent,
    FooterComponent
  ],
  imports: [CommonModule, ClientRoutingModule],
})
export class ClientModule {}
