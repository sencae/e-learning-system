import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {SignUpComponent} from "./components/sign-up/sign-up.component";
import {LoginComponent} from "./components/login/login.component";
import {MainpageComponent} from "./components/mainpage/mainpage.component";
import {CreateCourseComponent} from "./components/create-course/create-course.component";
import {NotFoundComponent} from "./components/not-found/not-found.component";
import {CoursesComponent} from "./components/courses/courses.component";
import {AuthorityGuard} from "./guards/authority.guard";
import {CourseInfoComponent} from "./components/course-info/course-info.component";
import {UserInfoComponent} from "./components/user-info/user-info.component";
import {UserEditComponent} from "./components/user-edit/user-edit.component";
import {CourseEditComponent} from "./components/course-edit/course-edit.component";

const appRoutes: Routes = [
  {path: 'signup', component: SignUpComponent},
  {path: 'login', component: LoginComponent},
  {path:'',component:MainpageComponent},
  {path:'courses/create', component:CreateCourseComponent, canActivate:[AuthorityGuard]},
  {path:'courses/all', component:CoursesComponent},
  {path:'course/:id',component:CourseInfoComponent},
  {path:'course/:id/edit',component:CourseEditComponent},
  {path:'user/:id',component:UserInfoComponent, },
  {path:'user/:id/edit',component:UserEditComponent},
  {path: '404', component:NotFoundComponent},
  {path: '**', redirectTo: '404'}
];

@NgModule({
  declarations: [],
  exports: [RouterModule],
  imports: [
    CommonModule,
    RouterModule.forRoot(appRoutes)
  ]
})

export class AppRoutingModule {
}
