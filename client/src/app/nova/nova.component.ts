import {Component, Injectable, OnInit} from '@angular/core';
import {Sort} from '@angular/material/sort';
export interface Food {
   calories: number;
   carbs: number;
   fat: number;
   name: string;
   protein: number;
}
@Component({
  selector: 'app-nova',
  templateUrl: './nova.component.html',
  styleUrls: ['./nova.component.css']
})
export class NovaComponent implements OnInit {

  foods: Food[] = [
    {name: 'Yogurt', calories: 159, fat: 6, carbs: 24, protein: 4},
    {name: 'Sandwich', calories: 237, fat: 9, carbs: 37, protein: 4},
    {name: 'Eclairs', calories: 262, fat: 16, carbs: 24, protein: 6},
    {name: 'Cupcakes', calories: 305, fat: 4, carbs: 67, protein: 4},
    {name: 'Gingerbreads', calories: 356, fat: 16, carbs: 49, protein: 4},
 ];
 sortedFood: Food[];
 constructor() {
    this.sortedFood = this.foods.slice();
 }
 ngOnInit() {}
 sortFood(sort: Sort) {
    const data = this.foods.slice();
    if (!sort.active || sort.direction === '') {
       this.sortedFood = data;
       return;
    }
    this.sortedFood = data.sort((a, b) => {
       const isAsc = sort.direction === 'asc';
       switch (sort.active) {
          case 'name': return compare(a.name, b.name, isAsc);
          case 'calories': return compare(a.calories, b.calories, isAsc);
          case 'fat': return compare(a.fat, b.fat, isAsc);
          case 'carbs': return compare(a.carbs, b.carbs, isAsc);
          case 'protein': return compare(a.protein, b.protein, isAsc);
          default: return 0;
       } 
    });
 }
}
function compare(a: number | string, b: number | string, isAsc: boolean) {
 return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
}
  
