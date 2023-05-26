import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, Subject } from 'rxjs';
import { UserStore } from '../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class StoreService {

  private _initialState: UserStore = {
    accountId: '',
    firstName: '',
    lastName: '',
    email: '',
    userId: ''
  }
  private _userData$ = new BehaviorSubject<UserStore>(this._initialState);
  private _store$: Observable<UserStore> = this._userData$.asObservable();
  constructor() { }

  get userData(): Observable<UserStore> {
    return this._store$;
  }

  set StoreData(data: UserStore) {
    this._userData$.next(data);
  }

  resetStore(): void {
    this._userData$.next(this._initialState);
  }
}
