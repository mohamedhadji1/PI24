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
import { AddTaskComponent } from './task/add-task/add-task.component';
import { UpdateTaskComponent } from './task/update-task/update-task.component';
import { AddCompanyComponent } from './company/add-company/add-company.component';
import { AddOfferComponent } from './company/add-offer/add-offer.component';
import { OfferComponent } from './company/offer/offer.component';
import { UpdateCompanyComponent } from './company/update-company/update-company.component';
import { UpdateOfferComponent } from './company/update-offer/update-offer.component';
import { RequestComponent } from './request/request.component';

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(UiComponentsRoutes),
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    TablerIconsModule.pick(TablerIcons),
    MatNativeDateModule,
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
    AddTaskComponent,
    UpdateTaskComponent,
    AddCompanyComponent,
    AddOfferComponent,
    OfferComponent,
    UpdateCompanyComponent,
    UpdateOfferComponent,
    RequestComponent,
  ],
})
export class UicomponentsModule {}
