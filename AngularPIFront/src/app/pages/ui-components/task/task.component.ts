import { Component } from '@angular/core';
import { Task } from 'src/app/core/Task';
import { TaskService } from 'src/app/services/task.service';
import { Router } from '@angular/router';
import { MatDialog,MatDialogRef } from '@angular/material/dialog';
import { AddTaskComponent } from './add-task/add-task.component';
@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.scss']
})
export class TaskComponent {
  tasks: Task[];

  constructor(private taskService: TaskService , private router: Router,public dialog: MatDialog ) { }


  openDialog(): void {
    const dialogRef = this.dialog.open(AddTaskComponent, {

    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }

  ngOnInit(): void {
    // Fetch tasks on component initialization
    this.fetchTasks();
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

}



