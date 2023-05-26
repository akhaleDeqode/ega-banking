import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class UtilityService {

  constructor(private _router: Router) { }

  /**
   * Function to allow only numbers in input field
   * @param event Key event
   * @returns True if number is input
   */
  numberOnly(event: any) {
    const charCode = event.which ? event.which : event.keyCode;
    const allowedKey = [8, 9, 17, 37, 38, 39, 40, 46, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105];
    if (allowedKey.includes(charCode)) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Function to set queryParams in URL with merge as queryParams handling
   * @param data Data with key value pairs to be set as queryParams in URL
   */
  addQueryParamsToUrl(data: any): void {
    this._router.navigate([], {
      queryParams: data,
      queryParamsHandling: 'merge'
    });
  }

  /**
   * Function that takes object and returns the object in query params structure
   * @param queryParams Object of queryParams
   * @returns queryParams structured for URL
   */
  returnQueryParams(queryParams: any): string {
    let query = "";
    if (queryParams) {
      var esc = encodeURIComponent;
      query += Object.keys(queryParams)
        .map(k => esc(k) + '=' + esc(queryParams[k]))
        .join('&');
    }
    return query != "" ? "?" + query : "";
  }
}
