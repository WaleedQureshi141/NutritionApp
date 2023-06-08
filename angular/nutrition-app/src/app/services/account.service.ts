import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { User } from '../entities/User';
import { Food } from '../entities/Food';

@Injectable({
  providedIn: 'root'
})
export class AccountService {
  private devApi = 'http://localhost:8081/api/dev';
  private nutritionApi = 'http://localhost:8081/api/nutritions';
  private apiUrl = 'http://localhost:8081/api/users';
  private httpOptions  = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    }),
  };

  constructor(private http:HttpClient) { }

  addUser(user: User): Observable<User> {

    return this.http.post<User>(this.apiUrl, user, this.httpOptions);
  }
  addDev(user: User): Observable<User>{
    this.httpOptions.headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': sessionStorage.getItem('authKey') as string
    })
    return this.http.post<User>(this.devApi,user, this.httpOptions);
  }
  getAllUsers(): Observable<User[]> {
    this.httpOptions.headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': sessionStorage.getItem('authKey') as string
    })
    return this.http.get<User[]>(this.apiUrl, this.httpOptions);
  }
  checkUserExists(user: User): Observable<User> {
    const url = `${this.apiUrl}/login`;
    return this.http.post<User>(url,user, this.httpOptions);
  }
  deleteUser(user: User): Observable<User[]> {
    this.httpOptions.headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': sessionStorage.getItem('authKey') as string
    })
    const url = `${this.apiUrl}/${user.userName}`;
    console.log(this.httpOptions);
    return this.http.delete<User[]>(url, this.httpOptions);
  }
  retrieveUserFoods(username: string): Observable<Food[]>{
    const url = `${this.nutritionApi}/${username}`;
    this.httpOptions.headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': sessionStorage.getItem('authKey') as string
    })
    return this.http.get<Food[]>(url, this.httpOptions);
  }
  addFood(food: Food): Observable<Food>{
    this.httpOptions.headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': sessionStorage.getItem('authKey') as string
    })
    return this.http.post<Food>(this.nutritionApi,food, this.httpOptions);
  }
  deleteFood(food: Food){
    const url = `${this.nutritionApi}/${food.id}`;
    this.httpOptions.headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': sessionStorage.getItem('authKey') as string
    })
    return this.http.delete<Food[]>(url,this.httpOptions);
  }
}
