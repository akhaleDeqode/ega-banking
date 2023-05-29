import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Withdraw } from '../models/withdraw.model';
import { Observable, catchError, timeout } from 'rxjs';
import { UtilityService } from './utility.service';

@Injectable({
  providedIn: 'root'
})
export class WithdrawService {

  private _base = environment.baseUrl;

  constructor(
    private _http: HttpClient,
    private _utility: UtilityService
  ) { }

  /**
   * Function to send withdraw amount to server
   * @param data Withdraw payload
   * @returns `Observable`
   */
  withdrawAmount(data: Withdraw): Observable<any> {
    return this._http.post(`${this._base}transaction/`, data).pipe(timeout(75000), catchError((error: HttpErrorResponse) => {
      throw error;
    }));
  }

  /**
   * Function to get all withdrawals made by user
   * @param queryParams Query params for pagination
   * @returns `Observable`
   */
  getAllWithdrawals(queryParams?: any): Observable<any> {
    const query = this._utility.returnQueryParams(queryParams);
    return this._http.get(`${this._base}transaction/get/withdraws${query}`).pipe(timeout(75000), catchError((error: HttpErrorResponse) => {
      throw error;
    }));
  }
}
