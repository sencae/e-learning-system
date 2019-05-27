import {Component, Input, OnInit} from '@angular/core';
import {CourseInfo} from "../../models/CourseInfo";
import {ActivatedRoute, Router} from "@angular/router";
import {CourseService} from "../../services/course/course.service";
import {AlertService} from "../../services/alert.service";

@Component({
  selector: 'app-course-resources',
  templateUrl: './course-resources.component.html',
  styleUrls: ['./course-resources.component.css']
})
export class CourseResourcesComponent implements OnInit {
  @Input('course') course: CourseInfo;

  constructor(private router:Router,
              private route:ActivatedRoute,
              private courseService:CourseService,
              private alertService:AlertService) {
  }

  howToEnd(){
    const id = +this.route.snapshot.paramMap.get('id');
    switch (this.course.endType) {
      case 1:
      {
        this.router.navigate(['course/'+ id+'/test']);
        break;
      }
      case 2:{
        this.courseService.course = this.course;
        this.router.navigate(['course/'+id+'/file']);
        break;
      }
      case 3:{
        this.endCourse();
        break;
      }

    }
  }
  endCourse(){
    const id = +this.route.snapshot.paramMap.get('id');
    this.courseService.endCourse(id).subscribe(data=>{
      this.alertService.success('You successfully complete course',true)

    },error1 => {this.alertService.error('Failed to end course',false)});
  }

  ngOnInit() {
  }

}
