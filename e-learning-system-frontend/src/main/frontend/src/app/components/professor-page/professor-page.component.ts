import {Component, OnInit} from '@angular/core';
import {CourseService} from "../../services/course/course.service";
import {Course} from "../../models/Course";
import {UserService} from "../../services/user/user.service";
import {UserInfo} from "../../models/UserInfo";
import {TokenStorageService} from "../../services/auth/token-storage.service";

@Component({
  selector: 'app-professor-page',
  templateUrl: './professor-page.component.html',
  styleUrls: ['./professor-page.component.css']
})
export class ProfessorPageComponent implements OnInit {
  courses: Course[];
  user: UserInfo;

  constructor(private courseService: CourseService,
              private userService: UserService,
              private tokenStorage: TokenStorageService) {
  }

  ngOnInit() {
    this.getMyCourses();
  }

  getMyCourses() {
    this.courseService.getMyCourses().subscribe(courses => {
      this.courses = courses;
      this.userService.getUser(Number(this.tokenStorage.getId())).subscribe(data => this.user = data);
    });
  }
}
