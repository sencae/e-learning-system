import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {CourseService} from "../../services/course/course.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-create-course',
  templateUrl: './create-course.component.html',
  styleUrls: ['./create-course.component.css']
})
export class CreateCourseComponent implements OnInit {

  courseCreateForm: FormGroup;
  submitted = false;
  constructor(private courseService: CourseService,
              private router:Router) { }

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
    this.submitted = true;
    if (this.courseCreateForm.invalid) {
      return;
    }
    this.courseService.createCourse(this.courseCreateForm.value)
      .subscribe(
        data => {
          console.log("success");
          this.router.navigate(['/courses/all'])
        },
        error => {
          console.log("error");
        });
  }
}
