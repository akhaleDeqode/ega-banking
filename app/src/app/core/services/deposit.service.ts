import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, timeout } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Deposit } from '../models/deposit.model';
import { UtilityService } from './utility.service';

@Injectable({
  providedIn: 'root'
})
export class DepositService {

  private _base = environment.baseUrl;

  constructor(
    private _http: HttpClient,
    private _utility: UtilityService
  ) { }

  /**
   * Function to send deposit payload to server
   * @param {Deposit} data
   * @returns `Observable`
   */
  depositAmount(data: Deposit): Observable<any> {
    return this._http.post(`${this._base}transaction/`, data).pipe(timeout(75000), catchError((error: HttpErrorResponse) => {
      throw error;
    }));
  }

  /**
   * Function to get all deposits made by user
   * @param queryParams Query params for pagination
   * @returns `Observable`
   */
  getAllDeposits(queryParams?: any): Observable<any> {
    let query = this._utility.returnQueryParams(queryParams);
    return this._http.get(`${this._base}transaction/get/deposits${query}`).pipe(timeout(75000), catchError((error: HttpErrorResponse) => {
      throw error;
    }));
  }
}
