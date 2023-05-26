import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpErrorResponse
} from '@angular/common/http';
import { Observable, catchError, map } from 'rxjs';
import { LocalstorageService } from '../services/localstorage.service';
import { ErrorHandlerService } from '../services/error-handler.service';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {

  constructor(
    private _localStorageService: LocalstorageService,
    private _errorHandler: ErrorHandlerService
  ) { }

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    const token = this._localStorageService.Token;
    if (token) {
      request = request.clone({
        setHeaders: {
          bearerTokenDto: token,
          Authorization: `Bearer ${token}`,
          "Access-Control-Allow-Origin": "*"
        }
      });
    } else {
      request = request.clone({
        setHeaders: {
          "Access-Control-Allow-Origin": "*"
        }
      });
    }
    return next.handle(request).pipe(
      map((response: any) => {
        return response;
      }),
      catchError((error: HttpErrorResponse) => {
        this._errorHandler.handleError(error);
        throw (error);
      })
    )
  }
}
