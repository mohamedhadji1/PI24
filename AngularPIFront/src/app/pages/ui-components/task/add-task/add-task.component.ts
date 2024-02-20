import { TaskService } from 'src/app/services/task.service';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Task } from 'src/app/core/Task';
import { User } from 'src/app/core/User';

@Component({
  selector: 'app-add-task',
  templateUrl: './add-task.component.html',
  styleUrls: ['./add-task.component.scss']
})
export class AddTaskComponent {
  taskId: number;
  taskDescription: string = '';
  progress: string = '';
  duration: string = '';
  supervisorId: number = 2;
  studentId: number = 3;

  constructor(private router: Router, private taskService: TaskService) {}

  addTask(): void {
    const newTask: Task = {
      id: this.taskId,
      taskDescription: this.taskDescription,
      progress: this.progress,
      duration: this.duration,
      supervisor: { id: this.supervisorId } as User,
      student: { id: this.studentId } as User
    };
    this.taskService.createTask(newTask).subscribe(
      (response) => {
        console.log('Task added successfully:', response);
        this.router.navigate(['/tasks']);
      },
      (error) => {
        console.error('Error adding task:', error);
      }
    );
  }
}
