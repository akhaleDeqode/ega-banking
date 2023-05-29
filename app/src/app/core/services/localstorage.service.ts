import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LocalstorageService {

  token: string = 'ega_token';

  constructor() { }

  /**
   * GET Token from local storage
   */
  get Token(): string {
    return localStorage.getItem(this.token) || '';
  }

  /**
   * SET Token to local storage
   */
  set Token(value: string) {
    localStorage.setItem(this.token, value);
  }

  /**
   * Remove Token from local storage
   */
  removeToken(): void {
    localStorage.removeItem('ega_token');
  }
}
