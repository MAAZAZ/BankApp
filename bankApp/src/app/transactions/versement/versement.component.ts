import { Component, OnInit } from '@angular/core';
import {Customer} from "../../models/Customer";
import {KeycloakSecurityService} from "../../services/keycloak-security.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-versement',
  templateUrl: './versement.component.html',
  styleUrls: ['./versement.component.css']
})
export class VersementComponent implements OnInit {

  public transation:any={montant:0};
  public id:string="";
  public url:string="http://localhost:8888/COMPTE-SERVICE/comptes/full/";
  constructor(private kcService:KeycloakSecurityService, private route: Router, private routeActive: ActivatedRoute) { }
  ngOnInit(): void {
    this.id=this.routeActive.snapshot.params['id'];
  }

  save() {
    if(this.id!=null){
      this.kcService.add(this.url+this.id+"/versement", this.transation).subscribe(data=>{});
    }
    setTimeout(() => {
      this.route.navigate(['comptes']);
    }, 3000);  //3s
  }

}
