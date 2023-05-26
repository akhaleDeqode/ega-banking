import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DepositsRoutingModule } from './deposits-routing.module';
import { DepositListComponent } from './pages/deposit-list/deposit-list.component';
import { CreateDepositComponent } from './components/create-deposit/create-deposit.component';
import { PrimengModule } from '../primeng/primeng.module';
import { DialogService } from 'primeng/dynamicdialog';
import { ConfirmationService } from 'primeng/api';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SharedModule } from '../shared/shared.module';


@NgModule({
  declarations: [
    DepositListComponent,
    CreateDepositComponent
  ],
  imports: [
    CommonModule,
    DepositsRoutingModule,
    PrimengModule,
    ReactiveFormsModule,
    FormsModule,
    SharedModule
  ],
  providers: [DialogService, ConfirmationService]
})
export class DepositsModule { }
