import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {CreateTest} from "../../models/CreateTest";
import {Test} from "../../models/Test";

@Injectable({
  providedIn: 'root'
})
export class TestService {
  private createTestUrl = '/api/createTest';
  private getTestUrl = '/api/getTest';
  private getTestEditUrl = '/api/getTestEdit';
  private saveUrl = '/api/saveTest';
  private completeTestUrl = '/api/completeTest';
  private deleteTestUrl = '/api/deleteTest';
  constructor(private http: HttpClient) {
  }

  saveTest(createTest: CreateTest): Observable<Test> {
    return this.http.post<Test>(this.createTestUrl, createTest)
  }

  getTest(id: number): Observable<Test> {
    return this.http.post<Test>(this.getTestUrl, id)
  }

  getTestEdit(id: number): Observable<Test> {
    return this.http.post<Test>(this.getTestEditUrl, id)
  }

  completeTest(test: Test, id: number): Observable<number> {
    test.id = id;
    return this.http.post<number>(this.completeTestUrl, test)
  }

  deleteTest(id: number) {
    return this.http.post(this.deleteTestUrl, id);
  }
  save(test: Test, id: number) {
    test.id = id;
    return this.http.post(this.saveUrl, test)
  }
}
