import {Component, OnInit} from '@angular/core';
import {Course} from "../../models/Course";
import {CourseService} from "../../services/course/course.service";
import {UserInfo} from "../../models/UserInfo";
import {UserService} from "../../services/user/user.service";
import {TokenStorageService} from "../../services/auth/token-storage.service";

@Component({
  selector: 'app-student-page',
  templateUrl: './student-page.component.html',
  styleUrls: ['./student-page.component.css']
})
export class StudentPageComponent implements OnInit {

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
