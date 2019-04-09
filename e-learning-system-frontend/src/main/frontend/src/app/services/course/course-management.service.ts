import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {StudentManage} from "../../models/StudentManage";

@Injectable({
  providedIn: 'root'
})
export class CourseManagementService {
  private getUsersOnCourseUrl = 'api/usersoncourse';

  constructor(private http: HttpClient) {
  }

  getUsersOnCourse(id: number): Observable<StudentManage[]> {
    return this.http.post<StudentManage[]>(this.getUsersOnCourseUrl, id)
  }

}
