import { Component, OnInit } from '@angular/core';
import {KeycloakSecurityService} from '../services/keycloak-security.service';

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.css']
})
export class CustomersComponent implements OnInit {
  public customers: any;
  public errorMessage: any;
  private url:string='http://localhost:8888/CLIENT-SERVICE/customers/';
  constructor(private kcService:KeycloakSecurityService) { }
  ngOnInit() {
      this.onGetCustomers();
  }
  onGetCustomers() {
    this.kcService.getData(this.url).subscribe(data=>{
      let customers:any=data;
      this.customers=customers._embedded.customers;
      },
        err=>{ this.errorMessage=err.error.message;});
  }
  deleteCustomer(id: number){
    if(confirm('êtes-vous sûr de vouloir supprimer  ce client?')) {
      this.customers.forEach((element: any, index: number) => {
        if (element.id == id)
          this.customers.splice(index, 1);
      });
      this.kcService.delete(this.url,id).subscribe(data => {});
    }
  }
}

