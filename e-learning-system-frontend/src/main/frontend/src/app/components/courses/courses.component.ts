import {Component, OnInit} from '@angular/core';
import {CourseService} from "../../services/course/course.service";
import {Course} from "../../models/Course";


@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.css']
})
export class CoursesComponent implements OnInit {
  courses: Course[];

  constructor(private courseService: CourseService) {
  }

  ngOnInit() {
    this.loadAllCourses();
  }

  private loadAllCourses() {
    this.courseService.getAllCourses().subscribe(
      courses=>{
        this.courses = courses;
      }
    );
  }
}
