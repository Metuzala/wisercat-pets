import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { tap } from 'rxjs';
import { User } from './user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  user: User | null = null;

  userUrl = 'http://localhost:8080/api/user';
  loginUrl = 'http://localhost:8080/login';
  logoutUrl = 'http://localhost:8080/logout';

  constructor(private http: HttpClient) { }

  getCurrentUser() {
    return this.http.get<User>(this.userUrl, { withCredentials: true }).pipe(
      tap((user: User) => {
        this.user = user;
      })
    );
  }

  logout() {
    return this.http.post<null>(this.logoutUrl, null, { withCredentials: true }).pipe(
      // forget user
      tap(() => this.user = null)
    );
  }
}
