import { Injectable } from '@angular/core';
import { User } from '../entities/User';
@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  constructor() { }

  isAuthenticated(){
    return sessionStorage.getItem('authenticated') === 'true';
  }
  authenticate(){
    sessionStorage.setItem('authenticated','true')
  }
  deauthenticate(){
    sessionStorage.setItem('authenticated','false')
  }
  isAuthenticatedDev(){
    return sessionStorage.getItem('authenticatedDev') === 'true';
  }
  authenticateDev(){
    sessionStorage.setItem('authenticatedDev','true')
  }
  deauthenticateDev(){
    sessionStorage.setItem('authenticatedDev','false')
  }
}
