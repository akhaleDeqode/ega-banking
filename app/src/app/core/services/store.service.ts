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

  /**
   * Get user data from store
   */
  get UserData$(): Observable<UserStore> {
    return this._store$;
  }

  /**
   * Set user data to store
   */
  set StoreData(data: UserStore) {
    this._userData$.next(data);
  }
  /**
   * Reset store data to initial state
   */
  resetStore(): void {
    this._userData$.next(this._initialState);
  }
}
