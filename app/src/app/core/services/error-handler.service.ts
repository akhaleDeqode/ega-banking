import { HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MessageService } from 'primeng/api';
import { ToasterService } from './toaster.service';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class ErrorHandlerService {


  constructor(
    private _toasterService: ToasterService,
    private _authService: AuthService
  ) { }

  handleError(error: HttpErrorResponse): void {
    // console.log(error);
    if (error.status == 403) {
      this._authService.logout();
    }
    this._toasterService.error('Error', error?.error?.message || 'Something went wrong');
  }
}
