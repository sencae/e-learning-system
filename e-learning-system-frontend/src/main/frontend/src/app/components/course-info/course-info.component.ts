import {Component, Input, OnInit} from '@angular/core';
import {Course} from "../../models/Course";
import {CourseService} from "../../services/course/course.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-course-info',
  templateUrl: './course-info.component.html',
  styleUrls: ['./course-info.component.css']
})
export class CourseInfoComponent implements OnInit {
  course: Course;
  constructor(private courseService: CourseService,
              private route: ActivatedRoute) {
  }

  ngOnInit(){
    this.getCourse();
  }

  private getCourse(){
    const id = +this.route.snapshot.paramMap.get('id');
    this.courseService.getCourse(id).subscribe(
      course => {
        this.course = course;
      }
    );
  }
}
