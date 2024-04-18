import { NgModule, Component } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BlankComponent } from './layouts/blank/blank.component';
import { FullComponent } from './layouts/full/full.component';
import { NotfoundComponent } from './notfound/notfound.component';
import { AddTaskComponent } from './pages/ui-components/task/add-task/add-task.component';
import { UserComponent } from './pages/ui-components/user/user.component';
import { AddUserComponent } from './pages/ui-components/user/add-user/add-user.component';
import { UpdateTaskComponent } from './pages/ui-components/task/update-task/update-task.component';
import { UpdateUserComponent } from './pages/ui-components/user/update-user/update-user.component';
import { AuthguardService } from './services/authguard.service';
import { LogVerificationComponent } from './pages/authentication/log-verification/log-verification.component';

const routes: Routes = [
  {path:"",pathMatch:"full",redirectTo:"home"},
  {
    path: 'home',
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
          canActivate:[AuthguardService],  data:{
            roles:['ADMIN','TUTOR','STUDENT','SUPERVISOR']
          }
      },
      {
        path: 'ui-components',
        loadChildren: () =>
          import('./pages/ui-components/ui-components.module').then(
            (m) => m.UicomponentsModule
          ),
          canActivate:[AuthguardService],  data:{
            roles:['ADMIN','TUTOR','STUDENT','SUPERVISOR']
          }
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
  {path: 'add-task',component: AddTaskComponent},
  { path: 'add-user', component: AddUserComponent,canActivate:[AuthguardService], data:{roles:['ADMIN']}},
  { path: 'update-user/:id', component: UpdateUserComponent,canActivate:[AuthguardService], data:{roles:['ADMIN']} },
  //{path:'**', component:NotfoundComponent},
  {path:'verif',component:LogVerificationComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
