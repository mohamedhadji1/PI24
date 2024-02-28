import { TaskService } from 'src/app/services/task.service';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { MatDialogRef } from '@angular/material/dialog';
import { Task } from 'src/app/core/Task';
import { User } from 'src/app/core/User';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
@Component({
  selector: 'app-add-task',
  templateUrl: './add-task.component.html',
  styleUrls: ['./add-task.component.scss'],
})
export class AddTaskComponent {
  taskId: number;
  taskDescription: string = '';
  progress: string = '';
  duration: string = '';
  supervisorId: number = 2;
  studentId: number = 1;

  taskForm: FormGroup;
  constructor(
    private router: Router,
    private taskService: TaskService,
    public dialogRef: MatDialogRef<AddTaskComponent>,
    private formBuilder: FormBuilder
  ) {
    this.taskForm = this.formBuilder.group({
      taskDescription: ['', [Validators.required, Validators.minLength(3)]],
      progress: ['', [Validators.required, Validators.minLength(3)]],
      duration: ['', [Validators.required, Validators.minLength(3)]],
      supervisorId: [2],
      studentId: [1]
    });
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  addTask(): void {
    if (this.taskForm.valid) {
      const newTask: Task = {
        id: this.taskId, // Assuming you handle ID generation elsewhere
        taskDescription: this.taskForm.value.taskDescription,
        progress: this.taskForm.value.progress,
        duration: this.taskForm.value.duration,
        supervisor: { id: this.taskForm.value.supervisorId } as User,
        student: { id: this.taskForm.value.studentId } as User,
      };

      this.taskService.createTask(newTask).subscribe(
        (response) => {
          console.log('Task added successfully:', response);
          this.router.navigate(['/ui-components/task']);
        },
        (error) => {
          console.error('Error adding task:', error);
        }
      );
    }
  }
}
