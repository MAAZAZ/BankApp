import { Injectable } from '@angular/core';
import {KeycloakSecurityService} from './keycloak-security.service';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class KeycloakHttpInterceptorService implements HttpInterceptor{

  constructor(private kcService: KeycloakSecurityService) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if(!this.kcService.kc.authenticated) return next.handle(req);
    let request=req.clone({
      setHeaders: {
        Authorization:  `Bearer ${this.kcService.kc.token}`
      }
    });
    return next.handle(request);
  }
}
