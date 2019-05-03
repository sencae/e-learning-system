import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {AuthInfo} from "../../models/AuthInfo";
import {AuthService} from "../../services/auth/auth.service";
import {TokenStorageService} from "../../services/auth/token-storage.service";
import {Router} from "@angular/router";
import {AlertService} from "../../services/alert.service";
import {first} from "rxjs/operators";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  isLoggedIn: boolean;
  loading = false;
  submitted = false;
  private loginInfo: AuthInfo;


  constructor(
    private authService: AuthService,
    private tokenStorage:TokenStorageService,
    private router:Router,
    private alertService: AlertService
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
    this.submitted = true;
    if (this.loginForm.invalid) {
      return;
    }
    this.loginInfo = new AuthInfo(
      this.f.email.value,
      this.f.password.value
    );
    this.loading = true;
    this.authService.attemptAuth(this.loginInfo)
      .pipe(first())
      .subscribe(
      data=> {
        this.tokenStorage.saveToken(data.accessToken);
        this.tokenStorage.saveUsername(data.email);
        this.tokenStorage.saveAuthorities(data.authorities);
        this.tokenStorage.saveId(data.id);
        this.isLoggedIn = true;
        this.router.navigate(['/user/' + data.id]).then(() => {
          window.location.reload();
        });
      },
      error => {
        let msg = "Wrong email or password";
        this.alertService.error(msg);
        this.loading = false;
      }
    );



  }
}
