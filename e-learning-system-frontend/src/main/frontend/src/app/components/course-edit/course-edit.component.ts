import { Component, OnInit } from '@angular/core';
import {Course} from "../../models/Course";
import {CourseService} from "../../services/course/course.service";
import {ActivatedRoute} from "@angular/router";
import {Resource} from "../../models/Resource";
import {FileExchangeService} from "../../services/fileExchange.service";

@Component({
  selector: 'app-course-edit',
  templateUrl: './course-edit.component.html',
  styleUrls: ['./course-edit.component.css']
})
export class CourseEditComponent implements OnInit {

  course: Course;
  constructor(private courseService:CourseService,
              private route: ActivatedRoute,
              private fileEx:FileExchangeService) { }

  ngOnInit() {
    this.getCourseInfo()
  }
  getCourseInfo(){
    const id = +this.route.snapshot.paramMap.get('id');
    this.courseService.getCourse(id).subscribe(
      course=> this.course=course
    )
  }
  deleteResource(resource:Resource){
    this.fileEx.deleteResource(resource.url);
  }

}
