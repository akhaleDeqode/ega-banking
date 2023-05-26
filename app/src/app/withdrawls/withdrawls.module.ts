import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { WithdrawlsRoutingModule } from './withdrawls-routing.module';
import { WithdrawlListComponent } from './pages/withdrawl-list/withdrawl-list.component';
import { CreateWithdrawlComponent } from './components/create-withdrawl/create-withdrawl.component';
import { ConfirmationService } from 'primeng/api';
import { DialogService } from 'primeng/dynamicdialog';
import { PrimengModule } from '../primeng/primeng.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SharedModule } from '../shared/shared.module';


@NgModule({
  declarations: [
    WithdrawlListComponent,
    CreateWithdrawlComponent
  ],
  imports: [
    CommonModule,
    WithdrawlsRoutingModule,
    PrimengModule,
    FormsModule,
    ReactiveFormsModule,
    SharedModule
  ],
  providers: [DialogService, ConfirmationService]
})
export class WithdrawlsModule { }
