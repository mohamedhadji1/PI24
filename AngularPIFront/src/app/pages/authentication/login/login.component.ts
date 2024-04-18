import { HttpResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';
import Swal from 'sweetalert2';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
})
export class AppSideLoginComponent {
  loginForm!: FormGroup;
  hidePassword = true;

  constructor(
    private router: Router,
    private formBuilder: FormBuilder,
    private userService :UserService ,

  ) {}
  ngOnInit():void {
    this.loginForm= this.formBuilder.group({
      username : [null, [Validators.required]],
      password :[null, [Validators.required]],
    })
  }
  tooglePasswordVisibility(){
    this.hidePassword = !this.hidePassword;
  }

  login(loginForm: FormGroup) {
    if (loginForm.value.email !== "" && loginForm.value.password !== "") {
      this.userService.login(loginForm).subscribe(
        (response: HttpResponse<any>) => {
            this.userService.setRoles(response);
            // Navigate to dashboard or any other route
            //this.router.navigate(['/dashboard']);
            this.router.navigate(['verif']);
        },
        (error) => {
            // Handle error
            console.log(error);
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: 'Wrong email or password!'
            });
        }
    );

    } else {
      Swal.fire({
        icon: 'error',
        title: 'Oops...',
        text: 'You should type your email and password'
      });
    }
  }
  onSubmit(loginForm: FormGroup){
    this.login(loginForm)
  }
}
