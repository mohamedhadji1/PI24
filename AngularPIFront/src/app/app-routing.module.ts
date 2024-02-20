import { NgModule, Component } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BlankComponent } from './layouts/blank/blank.component';
import { FullComponent } from './layouts/full/full.component';
import { AppSideLoginComponent } from './pages/authentication/login/login.component';
import { NotfoundComponent } from './notfound/notfound.component';
import { CompanyfrontComponent } from './fronttemplate/companyfront/companyfront.component';
import { DefensefrontComponent } from './fronttemplate/defensefront/defensefront.component';
import { InternshipfrontComponent } from './fronttemplate/internshipfront/internshipfront.component';
import { TaskfrontComponent } from './fronttemplate/taskfront/taskfront.component';
import { EventfrontComponent } from './fronttemplate/eventfront/eventfront.component';
import { InterviewfrontComponent } from './fronttemplate/interviewfront/interviewfront.component';
import { ComplaintfrontComponent } from './fronttemplate/complaintfront/complaintfront.component';
import { FronttemplateComponent } from './fronttemplate/fronttemplate.component';
import {ClientModule} from "./pages/client/client.module";
import { AddTaskComponent } from './pages/ui-components/task/add-task/add-task.component';

const routes: Routes = [
  {path:"",pathMatch:"full",redirectTo:"client"},
  {
    path: 'client',
    loadChildren: () =>
      import('./pages/client/client.module').then((m) => m.ClientModule)
  },
  {
    path: '',
    component: FullComponent,
    children: [
      {
        path: '',
        redirectTo: '/dashboard',
        pathMatch: 'full',
      },
      {
        path: 'dashboard',
        loadChildren: () =>
          import('./pages/pages.module').then((m) => m.PagesModule),
      },
      {
        path: 'ui-components',
        loadChildren: () =>
          import('./pages/ui-components/ui-components.module').then(
            (m) => m.UicomponentsModule
          ),
      },
    ],
  },
  {
    path: '',
    component: BlankComponent,
    children: [
      {
        path: 'authentication',
        loadChildren: () =>
          import('./pages/authentication/authentication.module').then(
            (m) => m.AuthenticationModule
          ),
      },
    ],
  },
  {path:'index', component:FronttemplateComponent},
  {path:'company', component:CompanyfrontComponent},
  {path:'internship', component:InternshipfrontComponent},
  {path:'defense', component:DefensefrontComponent},
  {path:'task', component:TaskfrontComponent},
  {path:'event', component:EventfrontComponent},
  {path:'interview', component:InterviewfrontComponent},
  {path:'complaint', component:ComplaintfrontComponent},
  {path:'sign up', component:ComplaintfrontComponent},
  {path:'sign in', component:ComplaintfrontComponent},
  {path:'login', component:AppSideLoginComponent},
  {path: 'add-task',component: AddTaskComponent},
  {path:'**', component:NotfoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
