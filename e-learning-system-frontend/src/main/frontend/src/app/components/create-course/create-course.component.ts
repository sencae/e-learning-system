import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {CourseService} from "../../services/course/course.service";

@Component({
  selector: 'app-create-course',
  templateUrl: './create-course.component.html',
  styleUrls: ['./create-course.component.css']
})
export class CreateCourseComponent implements OnInit {

  courseCreateForm: FormGroup;
  constructor(private courseService: CourseService) { }

  ngOnInit() {
    this.courseCreateForm = new FormGroup({
      title: new FormControl('', [Validators.required]),
      description: new FormControl('')
    });
  }
  get f() {
    return this.courseCreateForm.controls;
  }
  onSubmit() {
    if (this.courseCreateForm.invalid) {
      return;
    }
    this.courseService.createCourse(this.courseCreateForm.value)
      .subscribe(
        data => {
          console.log("success");
        },
        error => {
          console.log("error");
        });
  }
}
