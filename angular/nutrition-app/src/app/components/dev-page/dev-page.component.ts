import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from 'src/app/auth/authentication.service';
import { User } from 'src/app/entities/User';
import { AccountService } from 'src/app/services/account.service';

@Component({
  selector: 'app-dev-page',
  templateUrl: './dev-page.component.html',
  styleUrls: ['./dev-page.component.css']
})
export class DevPageComponent {
    userName!: string;
    password!: string;
    returnUser !: User;
    users: User[] = [];
    showCreateUI: boolean = false;
    constructor(private accountService: AccountService, private authenticationService: AuthenticationService, private router: Router){

    }
    ngOnInit(): void {
      this.accountService.getAllUsers().subscribe((users) => this.users = users);
    }
    toggleCreateUI(): void{
      this.showCreateUI = !this.showCreateUI;
    }
    rolesToString(user: User): string {
      var returnString: string = "";
      for(let r of user.roles){
        returnString = returnString + r.name + " ";
      }
      return returnString;
    }
    deleteUser(user: User): void {
      for(let r of user.roles){
        if(r.name === 'ROLE_DEVELOPER'){
          alert('Deleting developers is not allowed. Try deleting through the API directly.');
          return;
        }
      }
      this.accountService.deleteUser(user).subscribe((users) => {
        this.users = users;
        alert('Deleted user: ' + user.userName + ' succesfully!');
      });
    }
    async createDev(){
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
        roles: [{'id':1,'name': "ROLE_DEVELOPER"}] 
      }
      
      this.accountService.addDev(newUser).subscribe((user: User) => this.returnUser = user);
      alert('Account succesfully created. Please use the login tab for logging into the application. If it does not work please try registering a different username.');
      this.userName = '';
      this.password = '';
      this.accountService.getAllUsers().subscribe((users) => this.users = users);
    }
    routeUserPage(){
      this.authenticationService.authenticate();
      this.router.navigate(['/user']);
    }
}
