import { Component, OnInit } from '@angular/core';
import {Course} from "../../models/Course";
import {CourseService} from "../../services/course/course.service";
import {ActivatedRoute} from "@angular/router";
import {Resource} from "../../models/Resource";
import {FileExchangeService} from "../../services/fileExchange.service";
import {HttpEventType, HttpResponse} from "@angular/common/http";

@Component({
  selector: 'app-course-edit',
  templateUrl: './course-edit.component.html',
  styleUrls: ['./course-edit.component.css']
})
export class CourseEditComponent implements OnInit {

  course: Course;
  selectedFiles: FileList
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
  upload() {
  }
  deleteResource(resource:Resource){
    this.fileEx.deleteResource(resource.url);
  }
  selectFile(event) {
    this.selectedFiles = event.target.files;
  }
}
