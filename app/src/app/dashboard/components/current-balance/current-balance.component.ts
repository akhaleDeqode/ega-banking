import { Component } from '@angular/core';
import { Subject, takeUntil } from 'rxjs';
import { UserStore } from 'src/app/core/models/user.model';
import { DashboardService } from 'src/app/core/services/dashboard.service';
import { StoreService } from 'src/app/core/services/store.service';

@Component({
  selector: 'app-current-balance',
  templateUrl: './current-balance.component.html',
  styleUrls: ['./current-balance.component.scss']
})
export class CurrentBalanceComponent {

  accountBalance!: any;
  storeData!: UserStore;
  private _unsubscribe$ = new Subject<boolean>();

  constructor(
    private _dashboardService: DashboardService,
    private _storeService: StoreService
  ) { }

  ngOnInit(): void {
    this.getAccountBalance();
    this.getStoreData();
  }

  getStoreData(): void {
    this._storeService.UserData$.subscribe((res: UserStore) => {
      this.storeData = res;
    });
  }

  getAccountBalance(): void {
    this._dashboardService.getAccountDetail().pipe(takeUntil(this._unsubscribe$)).subscribe({
      next: (res: any) => {
        console.log(res);
        this.accountBalance = res?.balance;
      }
    });
  }

  ngOnDestroy(): void {
    this._unsubscribe$.next(true);
    this._unsubscribe$.complete();
  }
}
