import { Component, Output, EventEmitter, OnInit } from '@angular/core';
import { User } from 'src/app/entities/User';
import { Role } from 'src/app/entities/Role';
import {Subscription} from 'rxjs';
import { AccountService } from 'src/app/services/account.service';
import { AuthenticationService } from 'src/app/auth/authentication.service';
@Component({
  selector: 'app-create-account',
  templateUrl: './create-account.component.html',
  styleUrls: ['./create-account.component.css']
})
export class CreateAccountComponent implements OnInit {
  userName!: string;
  password!: string;
  returnUser !: User;

  constructor(private accountService: AccountService, private authenticationService: AuthenticationService){
    
  }
  ngOnInit(): void {
    this.authenticationService.deauthenticate();
    this.authenticationService.deauthenticateDev();
    localStorage.setItem('currentUser', '');
  }

  onSubmit(){
    if(!this.userName){
      alert('Please add a username!');
      return;
    } if (!this.password){
      alert('Please add a password!');
      return;
    }
    const newUser : User = {
      userName: this.userName,
      password: this.password,
      enabled: true,
      roles: [{'id':2,'name': "ROLE_USER"}] 
    }
    
    this.accountService.addUser(newUser).subscribe((user: User) => (this.returnUser = user));
    alert('Account succesfully created. Please use the login tab for logging into the application. If it does not work please try registering a different username.');
    this.userName = '';
    this.password = '';
  }
}
