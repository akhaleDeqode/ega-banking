import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { DialogService, DynamicDialogRef } from 'primeng/dynamicdialog';
import { Subject, takeUntil } from 'rxjs';
import { Deposit } from 'src/app/core/models/deposit.model';
import { UserStore } from 'src/app/core/models/user.model';
import { DepositService } from 'src/app/core/services/deposit.service';
import { StoreService } from 'src/app/core/services/store.service';
import { ToasterService } from 'src/app/core/services/toaster.service';
import { UtilityService } from 'src/app/core/services/utility.service';

@Component({
  selector: 'app-create-deposit',
  templateUrl: './create-deposit.component.html',
  styleUrls: ['./create-deposit.component.scss']
})
export class CreateDepositComponent {

  depositForm!: FormGroup;
  isFormSubmitted: boolean = false;
  storeData!: any;
  accountId!: string;
  private _unsubscribe$ = new Subject<boolean>();

  constructor(
    private _formBuilder: FormBuilder,
    private _depositService: DepositService,
    private _storeService: StoreService,
    private _toasterService: ToasterService,
    private _dialogRef: DynamicDialogRef,
    public _utility: UtilityService
  ) { }

  ngOnInit(): void {
    this.depositForm = this._formBuilder.group({
      accountId: [null, [Validators.required]],
      amount: [null, [Validators.required, Validators.min(1), Validators.max(1000000)]],
      transactionType: ['deposit']
    });
    this.getStoreData();
  }

  getStoreData(): void {
    this._storeService.userData.pipe(takeUntil(this._unsubscribe$)).subscribe((res: UserStore) => {
      console.log(res);
      this.accountId = res?.accountId;
      this.FormControl['accountId'].setValue(this.accountId);
      this.FormControl['accountId'].disable();
    });
  }

  get FormControl() {
    return this.depositForm.controls;
  }

  submit(): void {
    this.isFormSubmitted = true;
    console.log(this.depositForm.value);
    if (this.depositForm.valid) {
      let payload: Deposit = this.depositForm.value;
      payload['accountId'] = this.accountId;
      this._depositService.depositAmount(payload).pipe(takeUntil(this._unsubscribe$)).subscribe({
        next: (res: any) => {
          console.log(res);
          this._toasterService.success('Success', 'Amount Deposited');
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
