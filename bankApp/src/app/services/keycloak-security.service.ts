import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})
export class KeycloakSecurityService {
  public kc: any;
  constructor(private http:HttpClient) { }

  public async init() {
    // @ts-ignore
    this.kc = new Keycloak({
      url: "http://localhost:8080/auth",
      realm: "realm-bq",
      clientId: "banque-app"
    });

    await this.kc.init({
      //onLoad:"login-required",
      onLoad: 'check-sso',
      promiseType: 'native'
    });
  }
  public getCustomers(){
    return this.http.get("http://localhost:8888/CUSTOMER-SERVICE/customers");
  }

  public getComptes(){
    return this.http.get("http://localhost:8888/COMPTES-SERVICE/comptes/full");
  }
  public getById(url:String , resource: any){
    return this.http.get(url + resource.toString() + '/');
  }
  add(url:String , resource: any){
    return this.http.post(url+"", resource);
  }
  update(url:String , resource: any){
    return this.http.put(url + resource.id + '/', resource);
  }
  delete(url:String , resource: any){
    return this.http.delete(url + resource.toString() + '/');
  }
  public isManager():boolean{
    return this.kc.hasResourceRole("ADMIN");
  }
}
