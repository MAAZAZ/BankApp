import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {ComptesComponent} from "./comptes/comptes.component";
import {SavecustomerComponent} from "./savecustomer/savecustomer.component";
import {CustomersComponent} from "./customers/customers.component";
import {SavecompteComponent} from "./savecompte/savecompte.component";
import {OperationsComponent} from "./operations/operations.component";
import {VirementComponent} from "./transactions/virement/virement.component";
import {RetraitComponent} from "./transactions/retrait/retrait.component";
import {VersementComponent} from "./transactions/versement/versement.component";

const routes: Routes = [
  { path: '' , component : ComptesComponent },
  { path: 'comptes' , component : ComptesComponent },
  { path: 'comptes/nouveau', component : SavecompteComponent },
  { path: 'comptes/:id/operations' , component : OperationsComponent },
  { path: 'comptes/:id/Versement' , component : VersementComponent },
  { path: 'comptes/:id/retrait' , component : RetraitComponent },
  { path: 'comptes/virement' , component : VirementComponent },
  { path: 'customers', component : CustomersComponent },
  { path: 'customers/get', component : SavecustomerComponent },
  { path: 'customers/get/:id', component : SavecustomerComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
