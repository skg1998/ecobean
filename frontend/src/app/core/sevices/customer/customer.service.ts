import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, map, Observable } from 'rxjs';
import { User } from '../../interfaces/User';
import { environment } from '../../../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class CustomerService {
  private currentUserSubject: BehaviorSubject<User>;
  public currentUser: Observable<User>;

  constructor(private http: HttpClient) {
    this.currentUserSubject = new BehaviorSubject<User>(
      JSON.parse(localStorage.getItem('currentUser')!)
    );
    this.currentUser = this.currentUserSubject.asObservable();
  }

  public get currentUserValue(): User {
    return this.currentUserSubject.value;
  }

  login(username: string, password: string) {
    return this.http
      .post<any>(`${environment.BASE_URL}/customer-api/login`, {
        username,
        password,
      })
      .pipe(
        map((user: User) => {
          localStorage.setItem('currentUser', JSON.stringify(user));
          this.currentUserSubject.next(user);
          return user;
        })
      );
  }

  signup(user: User) {
    return this.http.post(
      `${environment.BASE_URL}/customer-api/register`,
      user
    );
  }

  updateProfile(user: User) {
    return this.http.put(
      `${environment.BASE_URL}/customer-api/customer/${this.currentUserValue.emailId}/updateProfile/`,
      user
    );
  }

  changePassword(currentPassword: string, newPassword: string) {
    return this.http.put(
      `${environment.BASE_URL}/customer-api/customer/${this.currentUserValue.emailId}/updatePassword/`,
      { currentPassword, newPassword }
    );
  }

  updateShippingAddress(address: string) {
    return this.http.put(
      `${environment.BASE_URL}/customer-api/customer/${this.currentUserValue.emailId}/address/`,
      address
    );
  }

  deleteShippingAddress() {
    return this.http.delete(
      `${environment.BASE_URL}/customer-api/customer/${this.currentUserValue.emailId}/`
    );
  }

  logout() {
    localStorage.removeItem('currentUser');
    this.currentUserSubject.next(null!);
  }

  checkIfTokenExists() {
    return false;
  }
}
