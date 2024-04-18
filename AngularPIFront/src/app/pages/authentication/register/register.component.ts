import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { MatSelectModule } from '@angular/material/select';
import { UserService } from 'src/app/services/user.service';
import { RegUser } from '../RegUser';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
})
export class AppSideRegisterComponent implements OnInit{

  file!:File;
  registerForm !:FormGroup;
  user: RegUser = new RegUser();

  constructor(private router: Router,private formBuilder: FormBuilder,private userService :UserService) { }

  ngOnInit(): void {
  this.registerForm=this.formBuilder.group({
    username:['',Validators.required],
    email:['',
      [Validators.required,Validators.email]],
    password:['',Validators.required],
    //role:['',Validators.required],
});

  }


  register(){
    this.user.username=this.registerForm.value.username
    this.user.email=this.registerForm.value.email
    this.user.password=this.registerForm.value.password
    //this.user.roles.push(this.registerForm.value.role)


    this.userService.register(this.user).subscribe(()=>{
      console.log(this.user)
      console.log("registration done !!");
      this.router.navigate(['/authentication/login']);
    },
    (error) => {
      console.log(error);
    }

    )
  }



  submit() {
    // console.log(this.form.value);
  }
}
