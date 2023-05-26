import { Injectable } from '@angular/core';
import { MessageService } from 'primeng/api';

@Injectable({
  providedIn: 'root'
})
export class ToasterService {

  constructor(
    private _messageService: MessageService
  ) { }

  success(title: string, description: string): void {
    this._messageService.add({ severity: 'success', summary: title, detail: description });
  }

  error(title: string, description: string): void {
    this._messageService.add({ severity: 'error', summary: title, detail: description });
  }
}
