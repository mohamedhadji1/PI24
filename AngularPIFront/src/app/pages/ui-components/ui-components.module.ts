import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from '../../material.module';

// icons
import { TablerIconsModule } from 'angular-tabler-icons';
import * as TablerIcons from 'angular-tabler-icons/icons';

import { UiComponentsRoutes } from './ui-components.routing';

// ui components
import { MatNativeDateModule } from '@angular/material/core';
import { UserComponent } from './user/user.component';
import { CompanyComponent } from './company/company.component';
import { DefenseComponent } from './defense/defense.component';
import { TaskComponent } from './task/task.component';
import { ComplaintComponent } from './complaint/complaint.component';
import { InternshipComponent } from './internship/internship.component';
import { EventComponent } from './event/event.component';
import { InterviewComponent } from './interview/interview.component';
import { DocumetsComponent } from './documets/documets.component';
import { CreateComponent } from './defense/createdefense/create/create.component';
import { DeleteComponent } from './defense/DeleteDefense/delete/delete.component';
import { UpdateComponent } from './defense/updateDefence/update/update.component';
import { EvaluationComponent } from './evaluation/evaluation.component';
import { CreateComponentt } from './evaluation/create/create.component';
import { UpdateComponentt } from './evaluation/update/update.component';
import { FilterPipe } from './defense/filter.pipe';
import { MapsComponent } from './defense/maps/maps.component';
import { NgApexchartsModule } from 'ng-apexcharts';
//import { FullCalendarModule } from '@fullcalendar/angular';

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(UiComponentsRoutes),
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    TablerIconsModule.pick(TablerIcons),
    MatNativeDateModule,CommonModule ,NgApexchartsModule,
  ],
  declarations: [
    UserComponent,
    CompanyComponent,
    DefenseComponent,
    TaskComponent,
    ComplaintComponent,
    InternshipComponent,
    EventComponent,
    InterviewComponent,
    DocumetsComponent,
    CreateComponent,
    DeleteComponent,
    UpdateComponent,
    EvaluationComponent,
    CreateComponentt,
    UpdateComponentt,
    FilterPipe,
    MapsComponent,
    UpdateComponent ,
    
  ],
})
export class UicomponentsModule {}
