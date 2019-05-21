import {Component, OnInit} from '@angular/core';
import {TokenStorageService} from "../../services/auth/token-storage.service";
import {NavigationStart, Router} from "@angular/router";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  id: number;
  auth: boolean;

  constructor(private tokenStorage: TokenStorageService,
              private router: Router) {
    router.events.subscribe(event => {
      if (event instanceof NavigationStart)
        this.auth = !(this.tokenStorage.getToken() == 'AuthToken' || this.tokenStorage.getToken() == null);
    })
  }

  ngOnInit() {
    this.id = Number(this.tokenStorage.getId());
  }

  logout() {
    this.tokenStorage.signOut();
    this.auth = false;
  }
}
