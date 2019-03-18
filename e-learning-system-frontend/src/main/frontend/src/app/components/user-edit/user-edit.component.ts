import { Component, OnInit } from '@angular/core';
import {UserInfo} from "../../models/UserInfo";
import {UserService} from "../../services/user/user.service";
import {TokenStorageService} from "../../services/auth/token-storage.service";
import {HttpEventType, HttpResponse} from "@angular/common/http";
import {FileExchangeService} from "../../services/fileExchange.service";

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

  constructor(private userService:UserService,
              private tokenStorage:TokenStorageService,
              private uploadService: FileExchangeService) { }

  ngOnInit() {
    this.getUserInfo();
  }
  getUserInfo(){
    this.userService.getUser(Number(this.tokenStorage.getId())).subscribe(
      user=> this.userInfo=user
    )
  }
  selectFile(event) {
    this.selectedFiles = event.target.files;
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
        console.log('File is completely uploaded!');
      }
    });

    this.selectedFiles = undefined;
  }

}
