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
import { OfferfrontComponent } from './components/offerfront/offerfront.component';
import { RequestfrontComponent } from './components/offerfront/requestfront/requestfront.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NotificationRequestComponent } from 'src/app/layouts/notification/notificationRequest/notificationRequest.component';

@NgModule({
  declarations: [
    NavbarComponent,
    MainComponent,
    VideoComponent,
    CompanyfrontComponent,
    ComplaintfrontComponent,
    EventfrontComponent,
    FooterComponent,
    OfferfrontComponent,
    RequestfrontComponent
  ],
  imports: [CommonModule, ClientRoutingModule,FormsModule, ReactiveFormsModule],
})
export class ClientModule {}
