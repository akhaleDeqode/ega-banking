import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { DynamicDialogRef } from 'primeng/dynamicdialog';
import { Subject, takeUntil } from 'rxjs';
import { UserStore } from 'src/app/core/models/user.model';
import { Withdraw } from 'src/app/core/models/withdraw.model';
import { StoreService } from 'src/app/core/services/store.service';
import { ToasterService } from 'src/app/core/services/toaster.service';
import { WithdrawService } from 'src/app/core/services/withdraw.service';

@Component({
  selector: 'app-create-withdrawl',
  templateUrl: './create-withdrawl.component.html',
  styleUrls: ['./create-withdrawl.component.scss']
})
export class CreateWithdrawlComponent {

  withdrawalForm!: FormGroup;
  isFormSubmitted: boolean = false;
  accountId!: string;
  private _unsubscribe$ = new Subject<boolean>();

  constructor(
    private _formBuilder: FormBuilder,
    private _withDrawService: WithdrawService,
    private _storeService: StoreService,
    private _toasterService: ToasterService,
    private _dialogRef: DynamicDialogRef
  ) { }

  ngOnInit(): void {
    this.withdrawalForm = this._formBuilder.group({
      accountId: [null, Validators.required],
      amount: [null, [Validators.required, Validators.min(1), Validators.max(1000000)]],
      transactionType: ['withdraw']
    });
    this.getStoreData();
  }

  getStoreData(): void {
    this._storeService.UserData$.pipe(takeUntil(this._unsubscribe$)).subscribe((res: UserStore) => {
      this.accountId = res?.accountId;
      this.FormControl['accountId'].setValue(this.accountId);
      this.FormControl['accountId'].disable();
    });
  }

  get FormControl() {
    return this.withdrawalForm.controls;
  }

  submit(): void {
    this.isFormSubmitted = true;
    console.log(this.withdrawalForm.value);
    if (this.withdrawalForm.valid) {
      let payload: Withdraw = this.withdrawalForm.value;
      payload['accountId'] = this.accountId;
      this._withDrawService.withdrawAmount(payload).pipe(takeUntil(this._unsubscribe$)).subscribe({
        next: (res: any) => {
          console.log(res);
          this._toasterService.success('Success', 'Amount Withdrawed');
          this._dialogRef.close({ data: res });
        }
      });
    }
  }

  ngOnDestroy(): void {
    this._unsubscribe$.next(true);
    this._unsubscribe$.complete();
  }
}
