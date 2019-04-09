import {Component, OnInit} from '@angular/core';
import {Course} from "../../models/Course";
import {CourseService} from "../../services/course/course.service";

@Component({
  selector: 'app-student-page',
  templateUrl: './student-page.component.html',
  styleUrls: ['./student-page.component.css']
})
export class StudentPageComponent implements OnInit {

  courses: Course[];

  constructor(private courseService: CourseService) {
  }

  ngOnInit() {
    this.getMyCourses();
  }

  getMyCourses() {
    this.courseService.getMyCourses().subscribe(courses => this.courses = courses);
  }
}
