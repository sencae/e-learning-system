import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {CourseService} from "../../services/course/course.service";
import {Router} from "@angular/router";
import {AlertService} from "../../services/alert.service";

@Component({
  selector: 'app-create-course',
  templateUrl: './create-course.component.html',
  styleUrls: ['./create-course.component.css']
})
export class CreateCourseComponent implements OnInit {

  courseCreateForm: FormGroup;
  submitted = false;
  constructor(private courseService: CourseService,
              private router:Router,
              private alertService: AlertService) { }

  ngOnInit() {
    this.courseCreateForm = new FormGroup({
      title: new FormControl('', [Validators.required]),
      description: new FormControl(''),
      startDate: new FormControl('',[Validators.required]),
      endDate: new FormControl('',[Validators.required])
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
          this.alertService.success('Course created', true);
          this.router.navigate(['/course/'+data])
        },
        error => {
          console.log("error");
        });
  }
}
