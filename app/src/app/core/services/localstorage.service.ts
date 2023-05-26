import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LocalstorageService {

  token: string = 'ega_token';

  constructor() { }

  get Token(): string {
    return localStorage.getItem(this.token) || '';
  }

  set Token(value: string) {
    localStorage.setItem(this.token, value);
  }

  removeToken(): void {
    localStorage.removeItem('ega_token');
  }
}
