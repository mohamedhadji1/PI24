import { saveAs } from 'file-saver';
import { Component } from '@angular/core';
import { Task } from 'src/app/core/Task';
import { TaskService } from 'src/app/services/task.service';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { AddTaskComponent } from './add-task/add-task.component';
import { UpdateTaskComponent } from './update-task/update-task.component';
import { Notification } from 'src/app/core/Notification';

@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.scss']
})
export class TaskComponent {
  tasks: Task[];
  notifications: Notification[];
  constructor(private taskService: TaskService, private router: Router, public dialog: MatDialog) { }

  openAddDialog(): void {
    const dialogRef = this.dialog.open(AddTaskComponent, {});

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      this.fetchTasks();
    });
  }

  openUpdateDialog(taskId: number): void {
    const dialogRef = this.dialog.open(UpdateTaskComponent, {
      data: { taskId: taskId, ...this.tasks.find(task => task.id === taskId) }
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      this.fetchTasks();
    });
  }

  ngOnInit(): void {
    this.fetchTasks();
    setInterval(() => {
      this.fetchNotifications();
    }, 5000);
  }
  fetchData(): void {
    this.fetchTasks();
    this.fetchNotifications();
  }
  fetchTasks(): void {
    this.taskService.getAllTasks().subscribe(
      tasks => {
        this.tasks = tasks;
      },
      error => {
        console.error('Error fetching tasks:', error);
      }
    );
  }

  deleteTask(taskId: number): void {
    if (confirm('Are you sure you want to delete this task?')) {
      this.taskService.deleteTask(taskId).subscribe(
        () => {
          this.tasks = this.tasks.filter(task => task.id !== taskId);
        },
        error => {
          console.error('Error deleting task:', error);
        }
      );
    }
  }
  downloadAttachment(taskId: number, attachmentFileName?: string): void {
    if (attachmentFileName) {
      this.taskService.downloadTaskFile(taskId, attachmentFileName).subscribe(
        data => {
          const blob = new Blob([data], { type: 'application/octet-stream' });
          saveAs(blob, attachmentFileName);
        },
        error => {
          console.error('Error downloading attachment:', error);
          // Handle specific errors, if needed
          if (error.status === 404) {
            console.error('Attachment not found');
          } else {
            console.error('Unknown error occurred');
          }
        }
      );
    } else {
      console.error('Attachment file name is undefined');
    }
  }
  fetchNotifications(): void {
    this.taskService.getAllNotifications().subscribe(
      notifications => {
        this.notifications = notifications;
      },
      error => {
        console.error('Error fetching notifications:', error);
      }
    );
  }
}
