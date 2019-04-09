import {Component, OnInit} from '@angular/core';
import {UserService} from "../../services/user/user.service";

@Component({
  selector: 'app-my-page',
  templateUrl: './my-page.component.html',
  styleUrls: ['./my-page.component.css']
})
export class MyPageComponent implements OnInit {
  isProfessor = false;

  constructor(private userService: UserService) {
  }

  ngOnInit() {
    this.isProfessor = !this.userService.hasAuthority('professor');
  }

}
