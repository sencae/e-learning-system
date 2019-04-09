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

  constructor(private http: HttpClient) {
  }

  saveTest(createTest: CreateTest): Observable<Test> {
    return this.http.post<Test>(this.createTestUrl, createTest)
  }
}
