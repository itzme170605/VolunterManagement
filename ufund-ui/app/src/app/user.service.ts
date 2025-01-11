import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { BehaviorSubject, catchError, empty, map, Observable, of, tap } from 'rxjs';
import { Helper, Manager, User } from '../app/user.model';
import { Need } from './need';
import { BasketService } from './basket.service';
import { ScheduleComponent } from './schedule/schedule.component';
import { ScheduleService } from './schedule.service';
import { NeedService } from './need.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private apiUrl = 'http://localhost:8080/users'; 
  private currentUserSubject: BehaviorSubject<User>;
  public currentUser: Observable<User>;
  public username: string;
  public helper!: Helper;
  public loggedIn: boolean = false;
  private loggedInSubject = new BehaviorSubject<boolean>(false);
  loggedIn$ = this.loggedInSubject.asObservable();

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient) {
    this.currentUserSubject = new BehaviorSubject<User>(JSON.parse(localStorage.getItem('currentUser') || '{}'));
    this.currentUser = this.currentUserSubject.asObservable();

    this.username = "";
  }

  // Get current logged-in user
  public get currentUserValue(): User {
    return this.currentUserSubject.value;
  }
  private handleError<T>(operation = 'operation', result?: T) {
      return (error: any): Observable<T> => {
        // TODO: send the error to remote logging infrastructure
        console.error(error); // log to console instead
        // TODO: better job of transforming error for user consumption
        this.log(`${operation} failed: ${error.message}`);
        // Let the app keep running by returning an empty result.
        return of(result as T);
      };
    }

  // Get all users
  getUsers(): Observable<User[]> {
    return this.http.get<User[]>(this.apiUrl);
  }

  // Get a user by username
  getUser(userName: string): Observable<User> {
    return this.http.get<User>(`${this.apiUrl}/${userName}`);
  }

  getHelper(userName: string): Observable<User> {
    return this.http.get<User>(`${this.apiUrl}/${userName}`);
  }

  // Create a new user
  createUser(newUser: User): Observable<User> {
    return this.http.post<User>(`${this.apiUrl}`, newUser);
  }
  createHelper(newUser: Helper): Observable<Helper> {
    return this.http.post<Helper>(`${this.apiUrl}`, newUser);
  }

  // Login user
  login(loginUser: User): Observable<User> {
    this.username = loginUser.userName
    this.loggedInSubject.next(true);
    return this.http.post<User>(`${this.apiUrl}/login`, loginUser);
  }

  logout(): void {
    this.loggedInSubject.next(false); 
    this.username = ""
  }

  // Update user
  updateUser(user: User): Observable<User> {
    console.log(user);
    return this.http.put<User>(this.apiUrl, user,this.httpOptions).pipe(
      tap(_ => this.log(`updated user =${user}`)),
      catchError(this.handleError<any>('updateUser'))
    );
  }
  private log(message: string) {
  }

  // Delete user
  deleteUser(userName: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${userName}`);
  }

}

