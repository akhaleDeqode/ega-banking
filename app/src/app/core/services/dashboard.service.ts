import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, timeout, catchError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { UtilityService } from './utility.service';

@Injectable({
  providedIn: 'root'
})
export class DashboardService {


  private _base = environment.baseUrl;

  constructor(
    private _http: HttpClient,
    private _utility: UtilityService
  ) { }

  getAllTransactions(queryParams?: any): Observable<any> {
    const query = this._utility.returnQueryParams(queryParams);
    return this._http.get(`${this._base}transaction/get${query}`).pipe(timeout(75000), catchError((error: HttpErrorResponse) => {
      throw error;
    }));
  }

  getAccountDetail(): Observable<any> {
    return this._http.get(`${this._base}account/get`).pipe(timeout(75000), catchError((error: HttpErrorResponse) => {
      throw error;
    }));
  }
}
