import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {UserService} from "../../services/user/user.service";
import {UserInfo} from "../../models/UserInfo";
import {TokenStorageService} from "../../services/auth/token-storage.service";
import {Observable} from "rxjs";
import {FileExchangeService} from "../../services/fileExchange.service";

@Component({
  selector: 'app-user-info',
  templateUrl: './user-info.component.html',
  styleUrls: ['./user-info.component.css']
})
export class UserInfoComponent implements OnInit {
  user: UserInfo;
  professor: boolean;

  constructor(private route: ActivatedRoute,
              private userService: UserService,
              private tokenStorage: TokenStorageService) {
  }

  ngOnInit() {
    this.getUser();
    if (this.tokenStorage.getToken()) {
      this.professor = !this.userService.hasAuthority('professor');
    }
  }

  authorized():boolean{
    return this.tokenStorage.getToken() != null &&
      this.tokenStorage.getUsername() == this.user.email;
  }
  isConfirmed():boolean{
    return !this.userService.hasAuthority('unconfirmed user');
  }

  getUser() {
    const id = +this.route.snapshot.paramMap.get('id');
    this.userService.getUser(id).subscribe(
      user => {
        this.user = user;
      }
    );
  }

}
