import { Component, OnInit } from '@angular/core';
import { Food } from 'src/app/entities/Food';
import { AccountService } from 'src/app/services/account.service';
@Component({
  selector: 'app-user-page',
  templateUrl: './user-page.component.html',
  styleUrls: ['./user-page.component.css']
})
export class UserPageComponent implements OnInit{
  foods!: Food[];

  constructor(private accountService: AccountService){
    
  }

  ngOnInit(): void {
    this.accountService.retrieveUserFoods(localStorage.getItem('currentUser') as string).subscribe((foods) => this.foods = foods);
  }
  currentUser(): string | null{
    return localStorage.getItem('currentUser');
  }
  deleteFood(food:Food): void{
    alert("works");
  }
}
