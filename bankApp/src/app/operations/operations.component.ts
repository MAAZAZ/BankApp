import { Component, OnInit } from '@angular/core';
import {KeycloakSecurityService} from "../services/keycloak-security.service";
import {Customer} from "../models/Customer";
import {ActivatedRoute, Router} from "@angular/router";
import {Compte} from "../models/Compte";

@Component({
  selector: 'app-operations',
  templateUrl: './operations.component.html',
  styleUrls: ['./operations.component.css']
})
export class OperationsComponent implements OnInit {

  public compte: Compte=new Compte();
  public id:string="";
  private url:string='http://localhost:8888/COMPTE-SERVICE/comptes/full/';

  constructor(private kcService:KeycloakSecurityService, private route: Router, private routeActive: ActivatedRoute) { }

  ngOnInit(): void {
    this.id=this.routeActive.snapshot.params['id'];
    if(this.id!=null)
      this.kcService.getById(this.url,this.id).subscribe(data=>{
        this.compte=data;
      });
  }

}
