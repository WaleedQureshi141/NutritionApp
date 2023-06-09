import { Component } from '@angular/core';
import { Food } from 'src/app/entities/Food';
import { AccountService } from 'src/app/services/account.service';
@Component({
  selector: 'app-add-food',
  templateUrl: './add-food.component.html',
  styleUrls: ['./add-food.component.css']
})
export class AddFoodComponent {
  foodName!: string;
  calories!: number;
  protein!: number;
  fat!: number;
  carbs!: number;
  addDate!: string;

  constructor(private accountService: AccountService){}

  onSubmit():void {
    if(!this.foodName){
      alert('Please add a foodname!');
      return;
    } 
    if (!this.calories){
      alert('Please add how many calories this item contains.');
      return;
    }
    if (!this.protein){
      alert('Please add how many grams of protein this item contains.');
      return;
    }
    if (!this.fat){
      alert('Please add how many grams of fat this item contains.');
      return;
    }
    if (!this.carbs){
      alert('Please add how many games of carbs this item contains.');
      return;
    }
    if (!this.addDate){
      alert('Please add a date for when you had this item. ');
      return;
    }
    const newFood : Food = {
      id: 0,
      userName: localStorage.getItem('currentUser') as string,
      foodName: this.foodName,
      calories: this.calories,
      protein: this.protein,
      fat: this.fat,
      carbs: this.carbs,
      addDate: this.addDate
    }
    this.accountService.addFood(newFood).subscribe();
    alert('Food succesfully created.');
    this.foodName = '';
    this.calories = 0;
    this.protein = 0;
    this.fat = 0;
    this.carbs = 0;
    this.addDate = '2023-06-08';
  }
}
