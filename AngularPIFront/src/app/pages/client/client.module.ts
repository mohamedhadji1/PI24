import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavbarComponent } from './navbar/navbar.component';
import { ClientRoutingModule } from './client-routing.module';
import { MainComponent } from './main/main.component';
import { VideoComponent } from './video/video.component';
// import { FooterComponent } from './footer/footer.component';

@NgModule({
  declarations: [NavbarComponent, MainComponent, VideoComponent],
  imports: [CommonModule, ClientRoutingModule],
})
export class ClientModule {}
