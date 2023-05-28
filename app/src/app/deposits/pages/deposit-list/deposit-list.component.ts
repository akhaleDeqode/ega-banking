import { Component } from '@angular/core';
import { DynamicDialogRef, DialogService } from 'primeng/dynamicdialog';
import { CreateDepositComponent } from '../../components/create-deposit/create-deposit.component';
import { Subject, takeUntil } from 'rxjs';
import { DepositService } from 'src/app/core/services/deposit.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-deposit-list',
  templateUrl: './deposit-list.component.html',
  styleUrls: ['./deposit-list.component.scss']
})
export class DepositListComponent {

  dialogRef!: DynamicDialogRef;
  depositsList: any[] = [];
  totalData: number = 0;
  currentPage: number = 0;
  private _unsubscribe$ = new Subject<boolean>();

  constructor(
    public dialogService: DialogService,
    private _depositService: DepositService,
    private _activatedRoute: ActivatedRoute,
    private _router: Router
  ) { }

  ngOnInit(): void {
    this._activatedRoute.queryParams.subscribe((res: any) => {
      this.getAllDeposits(res);
      if (res?.pageNumber) {
        this.currentPage = res?.pageNumber;
      }
    });
  }

  getAllDeposits(queryParams: any): void {
    this._depositService.getAllDeposits(queryParams).pipe(takeUntil(this._unsubscribe$)).subscribe({
      next: (res: any) => {
        // console.log(res);
        this.depositsList = res?.transactionList;
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
    this.dialogRef = this.dialogService.open(CreateDepositComponent, {
      header: 'Deposit Amount'
    });
    this.onCloseDialog();
  }

  onCloseDialog(): void {
    this.dialogRef.onClose.subscribe((res: any) => {
      if (res) this.ngOnInit();
    });
  }

  ngOnDestroy() {
    this._unsubscribe$.next(true);
    this._unsubscribe$.complete();
  }
}
