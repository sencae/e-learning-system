import {Component, OnInit} from '@angular/core';
import {UserInfo} from "../../models/UserInfo";
import {UserService} from "../../services/user/user.service";
import {TokenStorageService} from "../../services/auth/token-storage.service";
import {HttpEventType, HttpResponse} from "@angular/common/http";
import {FileExchangeService} from "../../services/fileExchange.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AlertService} from "../../services/alert.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-user-edit',
  templateUrl: './user-edit.component.html',
  styleUrls: ['./user-edit.component.css']
})
export class UserEditComponent implements OnInit {
  userInfo: UserInfo;
  selectedFiles: FileList;
  userEditForm: FormGroup;
  submitted = false;
  show = false;
  url = '';

  constructor(private formBuilder: FormBuilder,
              private userService: UserService,
              private tokenStorage: TokenStorageService,
              private uploadService: FileExchangeService,
              private alertService: AlertService,
              private router: Router) {
  }

  ngOnInit() {

    this.userEditForm = this.formBuilder.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      username: ['', Validators.required],
      email: ['',
        [Validators.required, Validators.pattern('[a-zA-z0-9_\.]+@[a-zA-Z]+\.[a-zA-Z]+')]],
      password: ['',
        [Validators.pattern('^(?=.*[0-9])(?=.*[a-zа-я])(?=.*[A-ZА-Я]).{8,}$')]],
      url:[],
      university: [],
      briefInformation: [],
      file: [null]
    });
    this.userService.getUser(Number(this.tokenStorage.getId())).subscribe(
      user => {
        let buf: any;
        this.userInfo = user;
        this.userInfo.password = null;
        buf = this.userInfo;
        this.userEditForm.setValue(buf);
      })
  };

  get f() {
    return this.userEditForm.controls;
  }

  selectFile(event) {
    this.selectedFiles = event.target.files[0];
    const reader = new FileReader();

    if (event.target.files && event.target.files.length) {
      const [file] = event.target.files;

      reader.readAsDataURL(file);

      reader.onload = (event: any) => {
        this.userEditForm.patchValue({file: reader.result});
        this.url = event.target.result;
        this.show = true;
      }
    }
  }
  deleteFile() {
    this.uploadService.deleteProfileImage(this.userInfo.url).subscribe(
      this.userInfo.url = null,
      error => {
        console.log("error")
      }
    )
  }

  onSubmit() {
    this.submitted = true;
    if (this.userEditForm.invalid) {
      return;
    }
    this.userService.updateUser(this.userEditForm.value).subscribe(data => {
        this.alertService.success('You successfully update profile', true);
        this.router.navigate(['/user/', this.tokenStorage.getId()])
      },
      error => {
        this.alertService.error('error', false);
      }
    );
    console.log("wow")
  }

}
