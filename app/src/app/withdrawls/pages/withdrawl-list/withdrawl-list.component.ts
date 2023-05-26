import { Component } from '@angular/core';
import { DynamicDialogRef, DialogService } from 'primeng/dynamicdialog';
import { CreateWithdrawlComponent } from '../../components/create-withdrawl/create-withdrawl.component';
import { Subject, takeUntil } from 'rxjs';
import { WithdrawService } from 'src/app/core/services/withdraw.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-withdrawl-list',
  templateUrl: './withdrawl-list.component.html',
  styleUrls: ['./withdrawl-list.component.scss']
})
export class WithdrawlListComponent {

  projects: any[] = [];
  ref!: DynamicDialogRef;
  withdrawsList: any[] = [];
  totalData: number = 0;
  currentPage: number = 0;
  private _unsubscribe$ = new Subject<boolean>();

  constructor(
    public dialogService: DialogService,
    private _withdrawService: WithdrawService,
    private _activatedRoute: ActivatedRoute,
    private _router: Router
  ) { }

  ngOnInit(): void {
    this._activatedRoute.queryParams.subscribe((res: any) => {
      this.getAllWithdraws(res);
      if (res?.pageNumber) {
        this.currentPage = res?.pageNumber;
      }
    });
  }

  getAllWithdraws(queryParams: any): void {
    this._withdrawService.getAllWithdrawals(queryParams).pipe(takeUntil(this._unsubscribe$)).subscribe({
      next: (res: any) => {
        // console.log(res);
        this.withdrawsList = res?.transactionList;
        this.totalData = res?.listSize;
      }
    });
  }

  onPageChange(event: any): void {
    const page = Math.floor(event?.first / event?.rows);
    this._router.navigate([], {
      queryParams: { pageNumber: page },
      queryParamsHandling: 'merge'
    });
  }

  showAddModal() {
    this.ref = this.dialogService.open(CreateWithdrawlComponent, {
      header: 'Withdraw Amount'
    });
    this.onCloseDialog();
  }

  onCloseDialog() {
    this.ref.onClose.subscribe((res: any) => {
      this.ngOnInit();
    });
  }

  ngOnDestroy() {
    this._unsubscribe$.next(true);
    this._unsubscribe$.complete();
  }
}
