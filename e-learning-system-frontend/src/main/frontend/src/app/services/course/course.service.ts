import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {CourseInfo} from "../../models/CourseInfo";
import {CreateCourse} from "../../models/CreateCourse";
import {Course} from "../../models/Course";

@Injectable({
  providedIn: 'root'
})
export class CourseService {
  get course(): CourseInfo {
    return this._course;
  }

  set course(value: CourseInfo) {
    this._course = value;
  }
  private allCoursesUrl = 'api/courses/all';
  private createCourseUrl = 'courses/create';
  private getCourseUrl = 'api/course/';
  private getMyCoursesUrl = 'api/my';
  private joinToCoursesUrl = 'courses/join';
  private updateCourseUrl = 'courses/courseEdit';
  private endCourseUrl='api/endCourseBut';
  private _course: CourseInfo;

  constructor(private http: HttpClient) {
  }

  getAllCourses(): Observable<Course[]> {
    return this.http.get<Course[]>(this.allCoursesUrl)
  }
  endCourse(courseId:number){
    return this.http.post(this.endCourseUrl,courseId)
  }
  createCourse(course: CreateCourse): Observable<number> {
    return this.http.post<number>(this.createCourseUrl, course)
  }

  getCourse(id: number): Observable<CourseInfo> {
    return this.http.get<CourseInfo>(this.getCourseUrl + id)
  }

  getMyCourses(): Observable<Course[]> {
    return this.http.get<Course[]>(this.getMyCoursesUrl)
  }

  join(id: number) {
    return this.http.post(this.joinToCoursesUrl, id);
  }

  updateCourse(course: Course) {
    return this.http.post(this.updateCourseUrl, course);
  }
}
