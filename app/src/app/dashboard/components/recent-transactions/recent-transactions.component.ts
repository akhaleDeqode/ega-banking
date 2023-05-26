import { Component } from '@angular/core';
import { Subject, takeUntil } from 'rxjs';
import { DashboardService } from 'src/app/core/services/dashboard.service';

@Component({
  selector: 'app-recent-transactions',
  templateUrl: './recent-transactions.component.html',
  styleUrls: ['./recent-transactions.component.scss']
})
export class RecentTransactionsComponent {

  transactionList: any[] = [];
  private _unsubscribe$ = new Subject<boolean>();

  constructor(
    private _dashboardService: DashboardService
  ) { }

  ngOnInit(): void {
    this.getAllTransactions();
  }

  getAllTransactions(): void {
    this._dashboardService.getAllTransactions().pipe(takeUntil(this._unsubscribe$)).subscribe({
      next: (res: any) => {
        console.log(res);
        this.transactionList = res?.transactionList;
      }
    });
  }

  ngOnDestroy(): void {
    this._unsubscribe$.next(true);
    this._unsubscribe$.complete();
  }
}
