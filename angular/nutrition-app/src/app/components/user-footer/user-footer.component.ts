import { Component } from '@angular/core';
import { Router } from '@angular/router';
@Component({
  selector: 'app-user-footer',
  templateUrl: './user-footer.component.html',
  styleUrls: ['./user-footer.component.css']
})
export class UserFooterComponent {
  constructor(private router: Router){

  }
  currentUser(): string{
    return localStorage.getItem('currentUser') as string;
  }
  logout(): void{
    this.router.navigate(['/login'])
  }
}
