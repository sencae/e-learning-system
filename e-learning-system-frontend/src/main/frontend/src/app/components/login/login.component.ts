import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {AuthInfo} from "../../models/AuthInfo";
import {AuthService} from "../../services/auth/auth.service";
import {TokenStorageService} from "../../services/auth/token-storage.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  isLoggedIn: boolean;
  isLoginFailed: boolean;
  errorMessage: '';
  private loginInfo: AuthInfo;


  constructor(
    private authService: AuthService,
    private tokenStorage:TokenStorageService,
    private router:Router
  ) {  }

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
    this.loginInfo = new AuthInfo(
      this.f.email.value,
      this.f.password.value
    );
    this.authService.attemptAuth(this.loginInfo).subscribe(
      data=> {
        this.tokenStorage.saveToken(data.accessToken);
        this.tokenStorage.saveUsername(data.email);
        this.tokenStorage.saveAuthorities(data.authorities);
        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.router.navigate(['/user/'+data.id]);
      },
      error => {
        console.log(error);
        this.errorMessage = error.error.message;
        this.isLoginFailed = true;
      }
    );



  }
}
