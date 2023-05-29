import { Injectable } from '@angular/core';
import { MessageService } from 'primeng/api';

@Injectable({
  providedIn: 'root'
})
export class ToasterService {

  constructor(
    private _messageService: MessageService
  ) { }

  /**
   * Open Success toaster with title and description
   * @param title Title of success
   * @param description Description of success
   */
  success(title: string, description: string): void {
    this._messageService.add({ severity: 'success', summary: title, detail: description });
  }

  /**
   * Open Error toaster with title and description
   * @param title Title of error
   * @param description Description of error
   */
  error(title: string, description: string): void {
    this._messageService.add({ severity: 'error', summary: title, detail: description });
  }
}
