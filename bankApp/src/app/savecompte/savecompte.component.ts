import { Component, OnInit } from '@angular/core';
import {Customer} from "../models/Customer";
import {KeycloakSecurityService} from "../services/keycloak-security.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Compte} from "../models/Compte";

@Component({
  selector: 'app-savecompte',
  templateUrl: './savecompte.component.html',
  styleUrls: ['./savecompte.component.css']
})
export class SavecompteComponent implements OnInit {

  public compte:Compte=new Compte();
  public clients:any;
  public url:string="http://localhost:8888/COMPTE-SERVICE/comptes/full/";
  public urlclients:string="http://localhost:8888/CLIENT-SERVICE/customers/";

  constructor(private kcService:KeycloakSecurityService, private route: Router) { }
  ngOnInit(): void {
    this.kcService.getData(this.urlclients).subscribe(data=>{
      let clients:any=data;
      this.clients=clients._embedded.customers;
      this.compte.customerID=1;
    })
  }
  save() {
    this.compte.solde=0
    this.compte.etat="ACTIVE";
    this.compte.dateCreation=new Date().toISOString();
    this.kcService.add(this.url, this.compte).subscribe(data=>{})
    setTimeout(() => {
      this.route.navigate(['comptes']);
    }, 3000);  //3s
  }

}
