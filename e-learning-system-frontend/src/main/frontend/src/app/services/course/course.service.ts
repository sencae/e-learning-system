import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Course} from "../../models/Course";

@Injectable({
  providedIn: 'root'
})
export class CourseService {
private allCoursesUrl = '/courses/alls';
  constructor(private http: HttpClient) { }

  getAllCourses(): Observable<Course[]>{
    return this.http.get<Course[]>(this.allCoursesUrl)
  }
}
