import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, timeout } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private _base = environment.baseUrl;

  constructor(
    private _http: HttpClient
  ) { }

  getUserDetail(): Observable<any> {
    return this._http.get(`${this._base}user/get`).pipe(timeout(75000), catchError((error: HttpErrorResponse) => {
      throw error;
    }));
  }
}
