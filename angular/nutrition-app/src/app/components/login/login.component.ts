import { Component, Output, EventEmitter, OnInit } from '@angular/core';
import { User } from 'src/app/entities/User';
import { Router } from '@angular/router';
import {Subscription} from 'rxjs';
import { AccountService } from 'src/app/services/account.service';
import { AuthenticationService } from 'src/app/auth/authentication.service';
import { Role } from 'src/app/entities/Role';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  userName!: string;
  password!: string;
  currentUser!: User;


  constructor(private authenticationService: AuthenticationService, private accountService: AccountService, private router: Router){
    
  }
  ngOnInit(): void {
    this.authenticationService.deauthenticate();
    this.authenticationService.deauthenticateDev();
    localStorage.setItem('currentUser', '');
  }

  async onSubmit(){  
    this.currentUser = {
      userName: '',
      password: '',
      enabled: true,
      roles: [] 
    };

    if(!this.userName){
      alert('Please add a username!');
      return;
    } if (!this.password){
      alert('Please add a password!');
      return;
    }
    console.log(this.userName);
    console.log(this.password);
    const loginUser : User = {
      userName: this.userName,
      password: this.password,
      enabled: true,
      roles: [] 
    }
    //this.accountService.modifyHttpOptions(loginUser);
    sessionStorage.setItem('authKey', 'Basic ' + btoa(`${loginUser.userName}:${loginUser.password}`));
    localStorage.setItem('currentUser', loginUser.userName);
    this.accountService.checkUserExists(loginUser).subscribe((user) => {
      this.currentUser = user;
      var isDev: boolean = false;
      console.log(this.currentUser);
      if(this.currentUser.enabled == true){
        this.userName = '';
        this.password = '';
        this.currentUser.roles.forEach((role: Role) => {
          if(role.name === 'ROLE_DEVELOPER'){
            isDev = true;
            console.log("hello");
            this.userName = '';
            this.password = '';
          } else {
            console.log("no");
          }
        })
        if(isDev){
          this.authenticationService.authenticateDev();
          this.router.navigate(['/dev']);
        }else {
          this.authenticationService.authenticate();
          this.router.navigate(['/user']);
        }
      } else {
        alert("Incorrect account info or account is not enabled, please try again.");
      }
    });

    this.userName = '';
    this.password = '';
  }
}
