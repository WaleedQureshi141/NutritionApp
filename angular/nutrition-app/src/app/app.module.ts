import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule, Routes } from '@angular/router';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavBarComponent } from './components/nav-bar/nav-bar.component';
import { CreateAccountComponent } from './components/create-account/create-account.component';
import { LoginComponent } from './components/login/login.component';
import { AboutComponent } from './components/about/about.component';

import { UserAuthGuard } from './auth/user-authenticaton-guard';
import { DevPageComponent } from './components/dev-page/dev-page.component';
import { DevAuthGuard } from './auth/dev-authentication-guard';
import { UserPageComponent } from './components/user-page/user-page.component';
import { UserPageNavComponent } from './components/user-page-nav/user-page-nav.component';
import { AddFoodComponent } from './components/add-food/add-food.component';
import { UserFooterComponent } from './components/user-footer/user-footer.component';
const appRoutes: Routes = [
  {
    path: '', component: CreateAccountComponent
  },
  {
    path: 'login', component: LoginComponent
  },
  {
    path: 'about', component: AboutComponent,
  },
  {
    path: 'dev', component: DevPageComponent,
    canActivate: [DevAuthGuard]
  },
  {
    path: 'user', component: UserPageComponent,
    canActivate: [UserAuthGuard]
  },
  {
    path: 'user/addfood', component: AddFoodComponent,
    canActivate: [UserAuthGuard]
  }
]

@NgModule({
  declarations: [
    AppComponent,
    NavBarComponent,
    CreateAccountComponent,
    LoginComponent,
    AboutComponent,
    DevPageComponent,
    UserPageComponent,
    UserPageNavComponent,
    AddFoodComponent,
    UserFooterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot(appRoutes, {enableTracing: true})
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
