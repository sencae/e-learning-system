import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {UserService} from "../../services/user/user.service";
import {User} from "../../models/User";
import {TokenStorageService} from "../../services/auth/token-storage.service";

@Component({
  selector: 'app-user-info',
  templateUrl: './user-info.component.html',
  styleUrls: ['./user-info.component.css']
})
export class UserInfoComponent implements OnInit {
  user:User;
  professor:boolean;
  constructor(private route:ActivatedRoute,
              private userService:UserService,
              private tokenStorage:TokenStorageService) { }

  ngOnInit() {
    this.getUser();
    if (this.tokenStorage.getToken()) {
      this.professor = !this.userService.hasAuthority('professor');
  }
  }
getUser(){
    const id = +this.route.snapshot.paramMap.get('id');
    this.userService.getUser(id).subscribe(
      user => {
        this.user = user;
      }
    );
  }

}
