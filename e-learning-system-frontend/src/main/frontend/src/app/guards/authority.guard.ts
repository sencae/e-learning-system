import {Injectable} from '@angular/core';
import {CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router} from '@angular/router';

import {UserService} from "../services/user/user.service";

@Injectable()
export class AuthorityGuard implements CanActivate {

  constructor(private userService: UserService, private router:Router) {
  }

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ) {
    if(!this.userService.hasAuthority('professor')) {
      return true;
    }
    this.router.navigate(['/']);
    return false;
  }
}
