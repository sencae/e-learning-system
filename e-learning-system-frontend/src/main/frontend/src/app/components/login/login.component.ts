import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {UserService} from "../../services/user/user.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;

  constructor(private userService: UserService) {
  }

  ngOnInit() {
    this.loginForm = new FormGroup({
      password: new FormControl('',
        [Validators.required]),
      email: new FormControl('',
        [Validators.required, Validators.pattern('[a-zA-z0-9_\.]+@[a-zA-Z]+\.[a-zA-Z]+')])
    });
  }

  get f() {
    return this.loginForm.controls;
  }

  onSubmit() {
    if (this.loginForm.invalid) {
      return;
    }
    this.userService.login(this.f.email.value, this.f.password.value)
      .subscribe(
        data => {
          console.log("success");
        },
        error => {
          console.log("error");
        });


  }
}
