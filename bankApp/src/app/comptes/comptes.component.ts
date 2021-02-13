import { Component, OnInit } from '@angular/core';
import {KeycloakSecurityService} from "../services/keycloak-security.service";
import {faPaste, faUser, faUserAlt, faUserAltSlash, faUserCog, faUserFriends} from "@fortawesome/free-solid-svg-icons";

@Component({
  selector: 'app-comptes',
  templateUrl: './comptes.component.html',
  styleUrls: ['./comptes.component.css']
})
export class ComptesComponent implements OnInit {

  user=faUser
  userActive=faUserAlt
  userdisable=faUserAltSlash
  users=faUserFriends
  userconf=faUserCog
  operations=faPaste

  public comptes: any;
  public errorMessage: any;
  private url:string='http://localhost:8888/COMPTE-SERVICE/comptes/full/';

  constructor(private kcService:KeycloakSecurityService) { }

  ngOnInit() {
    this.onGetComptes();
  }
  onGetComptes() {
    this.kcService.getData(this.url).subscribe(data=>{
        this.comptes=data
      },
      err=>{ this.errorMessage=err.error.message;});
  }

  etatCompte(id:number){
    this.comptes.forEach((element: any, index: number) => {
      if (element.id == id) {
        if(element.etat=="ACTIVE")
          this.kcService.getData(this.url+element.id+"/suspendre").subscribe(data=>{
            element.etat="SUSPENDED"
          })
        else {
          this.kcService.getData(this.url+element.id+"/activer").subscribe(data=>{
            element.etat="ACTIVE"
          });
        }
      }
    });
  }
}
