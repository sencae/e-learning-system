import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Course} from "../../models/Course";
import {CreateCourse} from "../../models/CreateCourse";

@Injectable({
  providedIn: 'root'
})
export class CourseService {
private allCoursesUrl = 'api/courses/all';
private createCourseUrl ='courses/create';
private getCourseUrl='api/course/';
  constructor(private http: HttpClient) { }

  getAllCourses(): Observable<Course[]>{
    return this.http.get<Course[]>(this.allCoursesUrl)
  }
  createCourse(course:CreateCourse){
    return this.http.post(this.createCourseUrl,course)
  }
  getCourse(id:number): Observable<Course>{
    return this.http.get<Course>(this.getCourseUrl+id)
  }
}
