import {Component,  OnInit} from '@angular/core';
import {Course} from "../../models/Course";
import {CourseService} from "../../services/course/course.service";
import {ActivatedRoute} from "@angular/router";
import {TokenStorageService} from "../../services/auth/token-storage.service";
import {UserService} from "../../services/user/user.service";

@Component({
  selector: 'app-course-info',
  templateUrl: './course-info.component.html',
  styleUrls: ['./course-info.component.css']
})
export class CourseInfoComponent implements OnInit {
  course: Course;
  professor: string;
  showFile = false;
  constructor(private courseService: CourseService,
              private route: ActivatedRoute,
              private tokenStorage: TokenStorageService,
              private userService:UserService) {
  }

  ngOnInit(){
    this.getCourse();
  }

  private getCourse(){
    const id = +this.route.snapshot.paramMap.get('id');
    this.courseService.getCourse(id).subscribe(
      course => {
        this.course = course;
        this.userService.getUser(course.professorId)
          .subscribe(name=>this.professor=name.lastName+' '+name.firstName)
        this.showFile = false;
      }
    );

  }
  isAuthor():boolean{
    return this.tokenStorage.getToken() != null &&
      this.course.professorId == Number(this.tokenStorage.getId());
  }
  showFiles(enable: boolean) {
    this.showFile = enable;

  }

}
