import { Component, OnInit } from '@angular/core';
import {KeycloakSecurityService} from "../../services/keycloak-security.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-virement',
  templateUrl: './virement.component.html',
  styleUrls: ['./virement.component.css']
})
export class VirementComponent implements OnInit {

  public virement:any={idSrc:0,idDes:0,montant:0};
  public url:string="http://localhost:8888/COMPTE-SERVICE/comptes/full/";
  constructor(private kcService:KeycloakSecurityService, private route: Router, private routeActive: ActivatedRoute) { }

  ngOnInit(): void {}

  save() {
    this.kcService.add(this.url+"virement", this.virement).subscribe(data=>{});
    setTimeout(() => {
      this.route.navigate(['comptes']);
    }, 3000);  //3s
  }

}
