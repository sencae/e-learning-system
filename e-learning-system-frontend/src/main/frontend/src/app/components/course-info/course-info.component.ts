import {Component, OnInit} from '@angular/core';
import {Course} from "../../models/Course";
import {CourseService} from "../../services/course/course.service";
import {ActivatedRoute, Router} from "@angular/router";
import {TokenStorageService} from "../../services/auth/token-storage.service";
import {UserService} from "../../services/user/user.service";
import {AlertService} from "../../services/alert.service";
import {CourseInfo} from "../../models/CourseInfo";

@Component({
  selector: 'app-course-info',
  templateUrl: './course-info.component.html',
  styleUrls: ['./course-info.component.css']
})
export class CourseInfoComponent implements OnInit {
  course: CourseInfo;
  professor: string;
  showFile = false;

  constructor(private courseService: CourseService,
              private route: ActivatedRoute,
              private tokenStorage: TokenStorageService,
              private userService: UserService,
              private alertService: AlertService,
              private router: Router) {
  }

  ngOnInit() {
    this.getCourse();
  }

  private getCourse() {
    const id = +this.route.snapshot.paramMap.get('id');
    this.courseService.getCourse(id).subscribe(
      course => {
        this.course = course;
        this.userService.getUser(course.professorId)
          .subscribe(name => this.professor = name.lastName + ' ' + name.firstName);

      },
      error => this.router.navigate(['/404'])
    );
  }

  joinTo() {
    const id = +this.route.snapshot.paramMap.get('id');
    this.courseService.join(id).subscribe(success => window.location.reload(),
      error => this.alertService.error(error.value))
  }

  showFiles(enable: boolean) {
    this.showFile = enable;

  }

}
