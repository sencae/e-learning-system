import {Component, OnInit} from '@angular/core';
import {CourseManagementService} from "../../services/course/course-management.service";
import {ActivatedRoute} from "@angular/router";
import {StudentManage} from "../../models/StudentManage";

@Component({
  selector: 'app-course-management',
  templateUrl: './course-management.component.html',
  styleUrls: ['./course-management.component.css']
})
export class CourseManagementComponent implements OnInit {

  private students: StudentManage[];

  constructor(private route: ActivatedRoute,
              private courseManagementService: CourseManagementService) {
  }

  ngOnInit() {
    this.getUsersOnCourse();
  }

  getUsersOnCourse() {
    const id = +this.route.snapshot.paramMap.get('id');
    this.courseManagementService.getUsersOnCourse(id).subscribe(data => this.students = data);
  }
}
