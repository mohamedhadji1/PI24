import { Component, NgModule } from '@angular/core';
import { CompanyfrontComponent } from './companyfront/companyfront.component';
import { DefensefrontComponent } from './defensefront/defensefront.component';
import { TaskfrontComponent } from './taskfront/taskfront.component';
import { ComplaintfrontComponent } from './complaintfront/complaintfront.component';
import { InternshipfrontComponent } from './internshipfront/internshipfront.component';
import { EventfrontComponent } from './eventfront/eventfront.component';
import { InterviewfrontComponent } from './interviewfront/interviewfront.component';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { UiComponentsRoutes } from '../pages/ui-components/ui-components.routing';
import { MaterialModule } from '../material.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { TablerIconsModule } from 'angular-tabler-icons';
import { MatNativeDateModule } from '@angular/material/core';
import * as TablerIcons from 'angular-tabler-icons/icons';
import { SigninComponent } from './signin/signin.component';
import { SignupComponent } from './signup/signup.component';
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
    CompanyfrontComponent,
    DefensefrontComponent,
    TaskfrontComponent,
    ComplaintfrontComponent,
    InternshipfrontComponent,
    EventfrontComponent,
    InterviewfrontComponent,
    SigninComponent,
    SignupComponent,
  ],
})
export class FronttemplateComponent {

}
