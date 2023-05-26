import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, timeout, catchError } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class DashboardService {


  private _base = environment.baseUrl;

  constructor(
    private _http: HttpClient
  ) { }

  getAllTransactions(): Observable<any> {
    return this._http.get(`${this._base}transaction/get`).pipe(timeout(75000), catchError((error: HttpErrorResponse) => {
      throw error;
    }));
  }

  getAccountDetail(): Observable<any> {
    return this._http.get(`${this._base}account/get`).pipe(timeout(75000), catchError((error: HttpErrorResponse) => {
      throw error;
    }));
  }
}
