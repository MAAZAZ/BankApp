import { Component, OnInit } from '@angular/core';
import {KeycloakSecurityService} from '../services/keycloak-security.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-savecustomer',
  templateUrl: './savecustomer.component.html',
  styleUrls: ['./savecustomer.component.css']
})
export class SavecustomerComponent implements OnInit {
  public customer:any={id:0,name:"",email:""};
  public id:string="";
  public url:string="http://localhost:8888/CUSTOMER-SERVICE/customers/";
  constructor(private kcService:KeycloakSecurityService, private route: Router, private routeActive: ActivatedRoute) { }
  ngOnInit(): void {
    this.id=this.routeActive.snapshot.params['id'];
    if(this.id!=null)
      this.kcService.getById(this.url,this.id).subscribe(data=>{
        this.customer=data;
      });
  }
  save() {
    if(this.id!=null){
      this.customer.id=Number(this.customer.id);
      this.kcService.update(this.url, this.customer).subscribe(data=>{});
    }
    else {
      this.kcService.add(this.url, this.customer).subscribe(data=>{});
    }
    setTimeout(() => {
      this.route.navigate(['customers']);
    }, 2000);  //2s
  }
}
