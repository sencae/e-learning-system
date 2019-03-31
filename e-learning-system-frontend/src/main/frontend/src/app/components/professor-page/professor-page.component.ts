import {Component, OnInit} from '@angular/core';
import {CourseService} from "../../services/course/course.service";
import {Course} from "../../models/Course";

@Component({
  selector: 'app-professor-page',
  templateUrl: './professor-page.component.html',
  styleUrls: ['./professor-page.component.css']
})
export class ProfessorPageComponent implements OnInit {
  courses: Course[];

  constructor(private courseService: CourseService) {
  }

  ngOnInit() {
    this.getMyCourses();
  }

  getMyCourses() {
    this.courseService.getMyCourses().subscribe(courses=>this.courses=courses);
  }
}
