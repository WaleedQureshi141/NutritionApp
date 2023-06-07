import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { User } from '../entities/User';


@Injectable({
  providedIn: 'root'
})
export class AccountService {
  private devApi = 'http://localhost:8081/api/dev';
  private apiUrl = 'http://localhost:8081/api/users'
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
    console.log(this.httpOptions);
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
  // modifyHttpOptions(user: User): void {
  //   console.log(this.httpOptions);
  //   console.log(user.userName);
  //   console.log(user.password);
  //   this.httpOptions.headers = new HttpHeaders({
  //     'Content-Type': 'application/json',
  //     'Authorization': 'Basic ' + sessionStorage.getItem('authKey') as string
  //   })
  // }
}
