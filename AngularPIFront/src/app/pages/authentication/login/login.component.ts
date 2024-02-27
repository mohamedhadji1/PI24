import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
})
export class AppSideLoginComponent {
  loginForm!: FormGroup;
  hidePassword = true;

  constructor(
    private formBuilder: FormBuilder,
    private snackBar : MatSnackBar,
    private router : Router
  ) {}
  ngOnInit():void {
    this.loginForm= this.formBuilder.group({
      email : [null, [Validators.required]],
      password :[null, [Validators.required]],
    })
  }
  tooglePasswordVisibility(){
    this.hidePassword = !this.hidePassword;
  }
 /* onSubmit():void{
    const username =this.loginForm.get('email')!.value;
    const password =this.loginForm.get('password')!.value;
    this.authService.login(username ,password).subscribe(
      (res :any) => {
        this.snackBar.open('login Success', 'ok', {duration: 5000});
      },
      (error :any) =>{
        this.snackBar.open('Bad credentiels', 'ERROR', {duration: 5000});

      }
    )

  }*/
}
