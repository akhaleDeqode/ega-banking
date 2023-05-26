import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ButtonModule } from 'primeng/button';
import { DropdownModule } from 'primeng/dropdown';
import { AutoCompleteModule } from 'primeng/autocomplete';
import { AvatarModule } from 'primeng/avatar';
import { ProgressSpinnerModule } from 'primeng/progressspinner';
import { MenuModule } from 'primeng/menu';
import { PanelMenuModule } from 'primeng/panelmenu';
import { AccordionModule } from 'primeng/accordion';
import { DragDropModule } from 'primeng/dragdrop';
import { SidebarModule } from 'primeng/sidebar';
import { DynamicDialogModule } from 'primeng/dynamicdialog';
import { TableModule } from 'primeng/table';
import { ReactiveFormsModule } from '@angular/forms';
import { InputNumberModule } from 'primeng/inputnumber';
import { InputTextModule } from 'primeng/inputtext';
import { InputTextareaModule } from 'primeng/inputtextarea';
import { DialogModule } from 'primeng/dialog';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { ToastModule } from 'primeng/toast';
import { PasswordModule } from 'primeng/password';
import { TabMenuModule } from 'primeng/tabmenu';
import { TooltipModule } from 'primeng/tooltip';

const primeNgModules = [
  ButtonModule,
  DropdownModule,
  AutoCompleteModule,
  AvatarModule,
  ProgressSpinnerModule,
  MenuModule,
  PanelMenuModule,
  AccordionModule,
  DragDropModule,
  SidebarModule,
  DynamicDialogModule,
  DynamicDialogModule,
  TableModule,
  ReactiveFormsModule,
  InputTextModule,
  InputNumberModule,
  InputTextareaModule,
  DialogModule,
  ConfirmDialogModule,
  ToastModule,
  PasswordModule,
  TabMenuModule,
  TooltipModule
]

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    ...primeNgModules
  ],
  exports: primeNgModules
})
export class PrimengModule { }
