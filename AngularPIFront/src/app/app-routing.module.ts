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
  {path: 'add-task',component: AddTaskComponent},
  { path: 'add-user', component: AddUserComponent },
  { path: 'update-user/:id', component: UpdateUserComponent },
  {path:'**', component:NotfoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
