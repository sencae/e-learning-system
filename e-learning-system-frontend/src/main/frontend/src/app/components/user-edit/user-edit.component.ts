import { Component, OnInit } from '@angular/core';
import {UserInfo} from "../../models/UserInfo";
import {UserService} from "../../services/user/user.service";
import {TokenStorageService} from "../../services/auth/token-storage.service";
import {HttpEventType, HttpResponse} from "@angular/common/http";
import {FileExchangeService} from "../../services/fileExchange.service";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {concat} from "rxjs";

@Component({
  selector: 'app-user-edit',
  templateUrl: './user-edit.component.html',
  styleUrls: ['./user-edit.component.css']
})
export class UserEditComponent implements OnInit {
  userInfo: UserInfo;
  progress: { percentage: number } = { percentage: 0 };
  currentFileUpload: File;
  selectedFiles: FileList;
  userEditForm: FormGroup;
  submitted = false;
  url='';
  constructor(private formBuilder: FormBuilder,
              private userService:UserService,
              private tokenStorage:TokenStorageService,
              private uploadService: FileExchangeService) { }

  ngOnInit() {

    this.userEditForm = this.formBuilder.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      username: ['', Validators.required],
      email: ['',
        [Validators.required, Validators.pattern('[a-zA-z0-9_\.]+@[a-zA-Z]+\.[a-zA-Z]+')]],
      password: ['',
        [ Validators.pattern('^(?=.*[0-9])(?=.*[a-zа-я])(?=.*[A-ZА-Я]).{8,}$')]],
      url: [],
      university:[],
      briefInformation:[]

    });
    this.userService.getUser(Number(this.tokenStorage.getId())).subscribe(
      user=> {
        let buf:any;
        this.userInfo=user;
        this.userInfo.password=null;
        buf = this.userInfo;
this.userEditForm.setValue(buf);})
};

  get f() {
    return this.userEditForm.controls;
  }
  selectFile(event) {
    this.selectedFiles = event.target.files[0];
    const reader = new FileReader();

    reader.readAsDataURL(event.target.files[0]); // read file as data url

    reader.onload = (event:any) => { // called once readAsDataURL is completed
      this.url = event.target.result}
  }
  deleteFile(){
    this.uploadService.deleteProfileImage(this.userInfo.url).subscribe(
      this.userInfo.url=null,
      error=>{
        console.log("error")
      }
    )
  }

  upload() {
    this.progress.percentage = 0;
    this.currentFileUpload = this.selectedFiles.item(0);
    this.uploadService.pushFileToStorage(this.currentFileUpload).subscribe(event => {
      if (event.type === HttpEventType.UploadProgress) {
        this.progress.percentage = Math.round(100 * event.loaded / event.total);
      } else if (event instanceof HttpResponse) {
        console.log(event);
      }
    });

    this.selectedFiles = undefined;
  }
  onSubmit(){
    this.submitted = true;
    if (this.userEditForm.invalid) {
      return;
    }
    if(this.selectedFiles&& this.selectedFiles.length>0){
      this.upload();
    }
    this.userService.updateUser(this.userEditForm.value);
    console.log("wow")
  }

}
