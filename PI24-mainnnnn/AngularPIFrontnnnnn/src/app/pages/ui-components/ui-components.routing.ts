import { Routes } from '@angular/router';

// ui
import { CompanyComponent } from './company/company.component';
import { UserComponent } from './user/user.component';
import { DefenseComponent } from './defense/defense.component';
import { TaskComponent } from './task/task.component';
import { ComplaintComponent } from './complaint/complaint.component';
import { InternshipComponent } from './internship/internship.component';
import { EventComponent } from './event/event.component';
import { DocumetsComponent } from './documets/documets.component';
import { AddTaskComponent } from './task/add-task/add-task.component';
import { ChatComponent } from './Chat/Chat.component';
import { TaskstudentComponent } from './taskstudent/taskstudent.component';
import { AddturninComponent } from './taskstudent/addturnin/addturnin.component';
import { TurnInsComponent } from './taskstudent/addturnin/turnIns/turnIns.component';
import { AddmonitoringComponent } from './taskstudent/addturnin/turnIns/Addmonitoring/Addmonitoring.component';
import { MonitoringChartsComponent } from './taskstudent/addturnin/turnIns/MonitoringCharts/MonitoringCharts.component';
import { RequestComponent } from './request/request.component';
import { EvaluationComponent } from './evaluation/evaluation.component';
import { DefenseStudentComponent } from './defensestudent/defense-student.component';

export const UiComponentsRoutes: Routes = [
  {
    path: '',
    children: [
      {
        path: 'defense',
        component: DefenseComponent,
      },{
        path: 'evaluation',
        component: EvaluationComponent,
      },
      {
        path: 'company',
        component: CompanyComponent,
      },
      {
        path: 'requests',
        component: RequestComponent,
      },
      {
        path: 'task',
        component: TaskComponent,
      },
      {
        path: 'complaint',
        component: ComplaintComponent,
      },
      {
        path: 'internship',
        component: InternshipComponent,
      },
      {
        path: 'event',
        component: EventComponent,
      },
      {
        path: 'user',
        component: UserComponent,
      },
      {
        path: 'interview',
        component: InternshipComponent,
      },
      {
        path: 'documents',
        component: DocumetsComponent,
      },
      {
      path: 'add-task',
      component: AddTaskComponent,
      },
      {
        path: 'chat',
        component: ChatComponent,
      },
      {
        path: 'taskstudent',
        component: TaskstudentComponent,
      },
      {
        path: 'turnin',
        component: AddturninComponent,
      },
      {
        path: 'turnins',
        component: TurnInsComponent,
      },
      {
        path: 'monitoring',
        component: AddmonitoringComponent,
      },
      {
        path: 'monitoringnotes',
        component: MonitoringChartsComponent,
      },
      {
        path: 'defensestudent',
        component: DefenseStudentComponent,
      },
    ],
  },
];
