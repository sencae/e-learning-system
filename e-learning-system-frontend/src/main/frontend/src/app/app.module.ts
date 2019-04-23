import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {HttpClientModule} from "@angular/common/http";
import {ReactiveFormsModule} from '@angular/forms';
import {RxReactiveFormsModule} from "@rxweb/reactive-form-validators";

import {AppComponent} from './app.component';
import {SignUpComponent} from './components/sign-up/sign-up.component';
import {AppRoutingModule} from './app-routing.module';
import {LoginComponent} from './components/login/login.component';
import {HeaderComponent} from './components/header/header.component';
import {FooterComponent} from './components/footer/footer.component';
import {MainpageComponent} from './components/mainpage/mainpage.component';
import {AngularFontAwesomeModule} from 'angular-font-awesome';
import {CreateCourseComponent} from './components/create-course/create-course.component';
import {NotFoundComponent} from './components/not-found/not-found.component';
import {CoursesComponent} from './components/courses/courses.component';
import {httpInterceptorProviders} from "./services/auth/authInterceptor";
import {AuthorityGuard} from "./guards/authority.guard";
import {CourseInfoComponent} from './components/course-info/course-info.component';
import {UserInfoComponent} from './components/user-info/user-info.component';
import {UserEditComponent} from './components/user-edit/user-edit.component';
import {CourseEditComponent} from './components/course-edit/course-edit.component';
import {AlertService} from "./services/alert.service";
import {AlertComponent} from './components/alert/alert.component';
import {ProfessorPageComponent} from './components/professor-page/professor-page.component';
import {StudentPageComponent} from './components/student-page/student-page.component';
import {TestComponent} from './test/test.component';
import {DownloadResourceComponent} from './components/download-resource/download-resource.component';
import {MyPageComponent} from './components/my-page/my-page.component';
import {CourseManagementComponent} from './components/course-management/course-management.component';
import {CourseTestComponent} from './components/course-test/course-test.component';
import {EditTestComponent} from './components/edit-test/edit-test.component';
import {CourseResourcesComponent} from './components/course-resources/course-resources.component';

@NgModule({
  declarations: [
    AppComponent,
    SignUpComponent,
    LoginComponent,
    HeaderComponent,
    FooterComponent,
    MainpageComponent,
    CreateCourseComponent,
    NotFoundComponent,
    CoursesComponent,
    CourseInfoComponent,
    UserInfoComponent,
    UserEditComponent,
    CourseEditComponent,
    AlertComponent,
    ProfessorPageComponent,
    StudentPageComponent,
    TestComponent,
    DownloadResourceComponent,
    MyPageComponent,
    CourseManagementComponent,
    CourseTestComponent,
    EditTestComponent,
    CourseResourcesComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    ReactiveFormsModule,
    AngularFontAwesomeModule,
    AppRoutingModule,
    RxReactiveFormsModule
  ],
  providers: [httpInterceptorProviders,
  AuthorityGuard,
  AlertService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
