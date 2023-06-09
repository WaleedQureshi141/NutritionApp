import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from 'src/app/auth/authentication.service';
@Component({
  selector: 'app-user-footer',
  templateUrl: './user-footer.component.html',
  styleUrls: ['./user-footer.component.css']
})
export class UserFooterComponent {
  constructor(private router: Router, private authenticationService: AuthenticationService){

  }
  currentUser(): string{
    return localStorage.getItem('currentUser') as string;
  }
  logout(): void{
    this.router.navigate(['/login'])
  }
  showReturnDev(): boolean{
    return this.authenticationService.isAuthenticatedDev() && this.router.url !== '/dev';
  }
  devGoBack(): void{
    this.router.navigate(['/dev']);
  }
}
