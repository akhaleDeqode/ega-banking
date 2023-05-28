import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subject, takeUntil } from 'rxjs';
import { DashboardService } from 'src/app/core/services/dashboard.service';

@Component({
  selector: 'app-recent-transactions',
  templateUrl: './recent-transactions.component.html',
  styleUrls: ['./recent-transactions.component.scss']
})
export class RecentTransactionsComponent {

  transactionList: any[] = [];
  totalData: number = 0;
  activePage: number = 0;
  private _unsubscribe$ = new Subject<boolean>();

  constructor(
    private _dashboardService: DashboardService,
    private _activatedRoute: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this._activatedRoute.queryParams.subscribe((res: any) => {
      this.getAllTransactions(res);
      if (res?.pageNumber) {
        this.activePage = (res.pageNumber * 10);
      }
    });
  }

  getAllTransactions(queryParams: any): void {
    this._dashboardService.getAllTransactions(queryParams).pipe(takeUntil(this._unsubscribe$)).subscribe({
      next: (res: any) => {
        console.log(res);
        this.transactionList = res?.transactionList;
        this.totalData = res?.listSize;
      }
    });
  }

  ngOnDestroy(): void {
    this._unsubscribe$.next(true);
    this._unsubscribe$.complete();
  }
}
