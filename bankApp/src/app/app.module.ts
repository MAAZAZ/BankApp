import { BrowserModule } from '@angular/platform-browser';
import {APP_INITIALIZER, NgModule} from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CustomersComponent } from './customers/customers.component';
import { SavecustomerComponent } from './savecustomer/savecustomer.component';
import { SavecompteComponent } from './savecompte/savecompte.component';
import { ComptesComponent } from './comptes/comptes.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {KeycloakHttpInterceptorService} from "./services/keycloak-http-interceptor.service";
import {KeycloakSecurityService} from "./services/keycloak-security.service";
import {FormsModule} from "@angular/forms";
import {FontAwesomeModule} from "@fortawesome/angular-fontawesome";
import { OperationsComponent } from './operations/operations.component';
import { RetraitComponent } from './transactions/retrait/retrait.component';
import { VirementComponent } from './transactions/virement/virement.component';
import { VersementComponent } from './transactions/versement/versement.component';

export function kcFactory(kcSecService:KeycloakSecurityService){
  return ()=>kcSecService.init();
}

@NgModule({
  declarations: [
    AppComponent,
    CustomersComponent,
    SavecustomerComponent,
    SavecompteComponent,
    ComptesComponent,
    OperationsComponent,
    RetraitComponent,
    VirementComponent,
    VersementComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule,
    FontAwesomeModule
  ],
  providers: [
    { provide: APP_INITIALIZER, deps: [KeycloakSecurityService], useFactory: kcFactory, multi: true},
    { provide: HTTP_INTERCEPTORS, useClass: KeycloakHttpInterceptorService, multi: true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
