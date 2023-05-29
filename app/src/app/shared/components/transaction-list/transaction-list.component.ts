import { Component, Input } from '@angular/core';
import { UtilityService } from 'src/app/core/services/utility.service';

@Component({
  selector: 'app-transaction-list',
  templateUrl: './transaction-list.component.html',
  styleUrls: ['./transaction-list.component.scss']
})
export class TransactionListComponent {

  @Input('dataList') dataList: any[] = [];
  @Input('activePage') activePage: number = 0;
  @Input('totalData') totalData: number = 0;

  constructor(private _utility: UtilityService) { }

  onPageChange(event: any): void {
    // console.log(event);
    const payload = { pageNumber: event.page }
    this._utility.addQueryParamsToUrl(payload);
  }
}
