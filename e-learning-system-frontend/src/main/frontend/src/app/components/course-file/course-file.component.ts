import {Component, OnInit} from '@angular/core';
import {FileExchangeService} from "../../services/fileExchange.service";
import {ActivatedRoute, Router} from "@angular/router";
import {AlertService} from "../../services/alert.service";
import {CourseInfo} from "../../models/CourseInfo";
import {CourseService} from "../../services/course/course.service";

@Component({
  selector: 'app-course-file',
  templateUrl: './course-file.component.html',
  styleUrls: ['./course-file.component.css']
})
export class CourseFileComponent implements OnInit {
  course: CourseInfo;
  fileUpload: File;

  readonly id = +this.route.snapshot.paramMap.get('id');

  constructor(private fileEx:FileExchangeService,
              private route:ActivatedRoute,
              private alertService:AlertService,
              private router:Router,
              private courseService:CourseService) { }

  ngOnInit() {
    this.course = this.courseService.course;
  }
  selectFile(event) {
    this.fileUpload = event.target.files[0];
  }
  submit(){
    if(this.fileUpload){
      this.fileEx.uploadFinalTask(this.fileUpload,this.id).subscribe(data=>{
        this.alertService.success("You successfully upload file! Wait for Professor decision.",true)
        this.router.navigate(['course/', this.id]);
      })
    }
  }
}
