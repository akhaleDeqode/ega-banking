import { Component } from '@angular/core';
import { Subject, takeUntil } from 'rxjs';
import { DashboardService } from 'src/app/core/services/dashboard.service';

@Component({
  selector: 'app-current-balance',
  templateUrl: './current-balance.component.html',
  styleUrls: ['./current-balance.component.scss']
})
export class CurrentBalanceComponent {

  accountBalance!: any;
  private _unsubscribe$ = new Subject<boolean>();

  constructor(
    private _dashboardService: DashboardService
  ) { }

  ngOnInit(): void {
    this.getAccountBalance();
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
