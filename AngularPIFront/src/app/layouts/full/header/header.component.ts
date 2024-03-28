import { TaskService } from 'src/app/services/task.service';
import {
  Component,
  Output,
  EventEmitter,
  Input,
  ViewEncapsulation,
  OnInit,
} from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { NotificationService } from 'src/app/services/Notification.service';


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  encapsulation: ViewEncapsulation.None,
})

export class HeaderComponent implements OnInit {
  notificationCount: number = 0;
  @Input() showToggle = true;
  @Input() toggleChecked = false;
  @Output() toggleMobileNav = new EventEmitter<void>();
  @Output() toggleMobileFilterNav = new EventEmitter<void>();
  @Output() toggleCollapsed = new EventEmitter<void>();

  showFiller = false;

  constructor(public dialog: MatDialog,private taskService: TaskService) {}
  ngOnInit(): void {
    this.fetchNotificationCount();
  }
  fetchNotificationCount(): void {
    this.taskService.getUnreadNotificationCount().subscribe(
      count => {
        this.notificationCount = count;
      },
      error => {
        console.error('Error fetching notification count:', error);
      }
    );
  }
}
