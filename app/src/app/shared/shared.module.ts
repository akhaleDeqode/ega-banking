import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from './components/header/header.component';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { ProjectLayoutComponent } from './layouts/project-layout/project-layout.component';
import { CardLoaderComponent } from './components/card-loader/card-loader.component';
import { PrimengModule } from '../primeng/primeng.module';
import { RouterModule } from '@angular/router';
import { EmptyScreenComponent } from './components/empty-screen/empty-screen.component';



@NgModule({
  declarations: [
    HeaderComponent,
    SidebarComponent,
    ProjectLayoutComponent,
    CardLoaderComponent,
    EmptyScreenComponent
  ],
  imports: [
    CommonModule,
    PrimengModule,
    RouterModule
  ],
  exports: [HeaderComponent, PrimengModule, CardLoaderComponent, EmptyScreenComponent]
})
export class SharedModule { }
