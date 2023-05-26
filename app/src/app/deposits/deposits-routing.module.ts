import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DepositListComponent } from './pages/deposit-list/deposit-list.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'list',
    pathMatch: 'full'
  },
  {
    path: 'list',
    component: DepositListComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DepositsRoutingModule { }
