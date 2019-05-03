import {Component, OnInit} from '@angular/core';
import {CourseService} from "../../../services/course/course.service";
import {ActivatedRoute, Router} from "@angular/router";
import {CourseInfo} from "../../../models/CourseInfo";
import {UserService} from "../../../services/user/user.service";
import {UserInfo} from "../../../models/UserInfo";

@Component({
  selector: 'app-test',
  templateUrl: './test.component.html',
  styleUrls: ['./test.component.css']
})
export class TestComponent implements OnInit {
  course: CourseInfo;
  professor: string;
  user: UserInfo;

  constructor(private courseService: CourseService,
              private route: ActivatedRoute,
              private userService: UserService,
              private router: Router) {
  }

  ngOnInit() {
    this.getCourse();
  }

  private getCourse() {
    const id = +this.route.snapshot.paramMap.get('id');
    this.courseService.getCourse(15).subscribe(
      course => {
        this.course = course;
        this.userService.getUser(course.professorId)
          .subscribe(name => {
            this.professor = name.lastName + ' ' + name.firstName;
            this.user = name;
          });

      },
      error => this.router.navigate(['/404'])
    );
  }
}
