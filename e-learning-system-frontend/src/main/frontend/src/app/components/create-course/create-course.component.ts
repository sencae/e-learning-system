import { Component, OnInit } from '@angular/core';
import {AbstractControl, FormControl, FormGroup, ValidatorFn, Validators} from "@angular/forms";
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
      startDate: new FormControl(null,[Validators.required,this.dateValidator]),
      endDate: new FormControl(null,[Validators.required])
    });

  }
  get f() {
    return this.courseCreateForm.controls;
  }
  dateValidator(control: AbstractControl): { [key: string]: boolean } | null {
    if (control.value !== undefined && Math.round((Date.parse(control.value.toString()) - Date.parse(new Date().toString())) / 86400000)<0) {
      return { 'pastDate': true };
    }
    return null;
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
