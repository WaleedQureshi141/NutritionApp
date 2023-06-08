import { Component } from '@angular/core';
import { Food } from 'src/app/entities/Food';
@Component({
  selector: 'app-add-food',
  templateUrl: './add-food.component.html',
  styleUrls: ['./add-food.component.css']
})
export class AddFoodComponent {
  food_name!: string;
  calories!: number;
  protein_g!: number;
  fat_g!: number;
  carbs_g!: number;
  add_date!: string;

  onSubmit():void {
    if(!this.food_name){
      alert('Please add a foodname!');
      return;
    } 
    if (!this.calories){
      alert('Please add how many calories this item contains.');
      return;
    }
    if (!this.protein_g){
      alert('Please add how many grams of protein this item contains.');
      return;
    }
    if (!this.fat_g){
      alert('Please add how many grams of fat this item contains.');
      return;
    }
    if (!this.carbs_g){
      alert('Please add how many games of carbs this item contains.');
      return;
    }
    const newFood : Food = {
      food_name: this.food_name,
      calories: this.calories,
      protein_g: this.protein_g,
      fat_g: this.fat_g,
      carbs_g: this.carbs_g,
      add_date: this.add_date
    }
  }
}
