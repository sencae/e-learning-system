import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {SignUpComponent} from "./components/sign-up/sign-up.component";
import {LoginComponent} from "./components/login/login.component";
import {MainpageComponent} from "./components/mainpage/mainpage.component";

const appRoutes: Routes = [
  {path: 'registration', component: SignUpComponent},
  {path: 'login', component: LoginComponent},
  {path:'',component:MainpageComponent}
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
