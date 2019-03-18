import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {HttpClientModule} from "@angular/common/http";
import {ReactiveFormsModule} from '@angular/forms';

import {AppComponent} from './app.component';
import {SignUpComponent} from './components/sign-up/sign-up.component';
import {AppRoutingModule} from './app-routing.module';
import {LoginComponent} from './components/login/login.component';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { MainpageComponent } from './components/mainpage/mainpage.component';
import { AngularFontAwesomeModule } from 'angular-font-awesome';
import { CreateCourseComponent } from './components/create-course/create-course.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { CoursesComponent } from './components/courses/courses.component';
import {httpInterceptorProviders} from "./services/auth/authInterceptor";
import {AuthorityGuard} from "./guards/authority.guard";
import { CourseInfoComponent } from './components/course-info/course-info.component';
import { UserInfoComponent } from './components/user-info/user-info.component';
import { UserEditComponent } from './components/user-edit/user-edit.component';
import { CourseEditComponent } from './components/course-edit/course-edit.component';

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
    CourseEditComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    ReactiveFormsModule,
    AppRoutingModule,
    AngularFontAwesomeModule
  ],
  providers: [httpInterceptorProviders,
  AuthorityGuard],
  bootstrap: [AppComponent]
})
export class AppModule {
}
