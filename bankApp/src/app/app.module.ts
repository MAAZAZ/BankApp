import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CustomersComponent } from './customers/customers.component';
import { SavecustomerComponent } from './savecustomer/savecustomer.component';
import { SavecompteComponent } from './savecompte/savecompte.component';
import { ComptesComponent } from './comptes/comptes.component';

@NgModule({
  declarations: [
    AppComponent,
    CustomersComponent,
    SavecustomerComponent,
    SavecompteComponent,
    ComptesComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
