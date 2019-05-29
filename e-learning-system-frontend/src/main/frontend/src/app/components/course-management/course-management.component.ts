import {Component, OnInit} from '@angular/core';
import {CourseManagementService} from "../../services/course/course-management.service";
import {ActivatedRoute} from "@angular/router";
import {StudentManage} from "../../models/StudentManage";
import {AlertService} from "../../services/alert.service";

@Component({
  selector: 'app-course-management',
  templateUrl: './course-management.component.html',
  styleUrls: ['./course-management.component.css']
})
export class CourseManagementComponent implements OnInit {

  private students: StudentManage[];

  constructor(private route: ActivatedRoute,
              private courseManagementService: CourseManagementService,
              private alertService: AlertService) {
  }

  ngOnInit() {
    this.getUsersOnCourse();
  }

  getUsersOnCourse() {
    const id = +this.route.snapshot.paramMap.get('id');
    this.courseManagementService.getUsersOnCourse(id).subscribe(data => this.students = data,
      error1 => {
        if (error1.status === 403) {
          this.alertService.error('Access denied')
        }
      });
  }
  endCourse(studentId:number){
    this.courseManagementService.endCourse(studentId,+this.route.snapshot.paramMap.get('id')).subscribe(data=>
    {
      this.alertService.success('student '+this.students.find(x=>x.userId==studentId).firstName
      + ' '+ this.students.find(x=>x.userId==studentId).lastName + ' successfully end course');
      this.students.find(x=>x.userId===studentId).finished = true;
    })
  }
  deleteFromC(studentId:number){
    this.courseManagementService.deleteFromC(studentId,+this.route.snapshot.paramMap.get('id')).subscribe(
      data=>{
        this.alertService.success('student '+this.students.find(x=>x.userId==studentId).firstName
          + ' '+ this.students.find(x=>x.userId==studentId).lastName + ' successfully deducted');
       this.students = this.students.filter(x=>x.userId!==studentId);

      }
    )
  }
}
