import { Component, OnInit } from '@angular/core';
import {TokenStorageService} from "../../services/auth/token-storage.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  id:number;
  auth: boolean;
  constructor(private tokenStorage:TokenStorageService) { }

  ngOnInit() {
    this.auth = !(this.tokenStorage.getToken() == 'AuthToken' || this.tokenStorage.getToken() == null);
    this.id = Number(this.tokenStorage.getId());
  }
  logout() {
    this.tokenStorage.signOut();
    window.location.reload();
  }
}
