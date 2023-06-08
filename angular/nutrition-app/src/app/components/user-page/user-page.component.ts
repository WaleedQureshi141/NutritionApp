import { Component } from '@angular/core';
import { Food } from 'src/app/entities/Food';
@Component({
  selector: 'app-user-page',
  templateUrl: './user-page.component.html',
  styleUrls: ['./user-page.component.css']
})
export class UserPageComponent {
  foods!: Food[];

  currentUser(): string | null{
    return localStorage.getItem('currentUser');
  }
  deleteFood(food:Food): void{

  }
}
