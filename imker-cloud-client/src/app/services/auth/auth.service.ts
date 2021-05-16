import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  constructor() {
  }

  setDataInLocalStorage(variableName: string, data: string) {
    localStorage.setItem(variableName, data);
  }

  getToken() {
    return localStorage.getItem('token');
  }

  getUsername() {
    return localStorage.getItem('username')
  }

  getId() {
    return localStorage.getItem('id')
  }

  getFirstName() {
    return localStorage.getItem('firstName')
  }

  getLastName() {
    return localStorage.getItem('lastName')
  }

  getEmail() {
    return localStorage.getItem('email')
  }

  clearStorage() {
    localStorage.clear();
  }
}
