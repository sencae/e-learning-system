import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {AuthService} from "../../services/auth/auth.service";
import {TokenStorageService} from "../../services/auth/token-storage.service";
import {AlertService} from "../../services/alert.service";

@Component({
  selector: 'app-confirm-account',
  templateUrl: './confirm-account.component.html',
  styleUrls: ['./confirm-account.component.css']
})
export class ConfirmAccountComponent implements OnInit {

  constructor(private route: ActivatedRoute,
              private authService: AuthService,
              private tokenStorage: TokenStorageService,
              private router: Router,
              private alertService: AlertService) {
  }

  ngOnInit() {
    this.confirm()
  }

  confirm() {
    const id = this.route.snapshot.paramMap.get('id');
    this.authService.confirm(id).subscribe(data => {
        this.tokenStorage.signOut();
        this.alertService.success("You successfully confirm account", true);
        this.router.navigate(['/login']);
      },
      error1 => {
        this.alertService.error("Failed to confirm", true);
        this.router.navigate(['/'])
      })
  }
}
