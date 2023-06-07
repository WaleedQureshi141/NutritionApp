import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, RouterStateSnapshot, Router} from '@angular/router';
import { Observable } from 'rxjs';
import { AuthenticationService } from './authentication.service';
@Injectable({
  providedIn: 'root'
})
export class DevAuthGuard {
  constructor(private authenticationService: AuthenticationService, private router: Router){};

  canActivate(route: ActivatedRouteSnapshot, status: RouterStateSnapshot): boolean {
    if(!this.authenticationService.isAuthenticatedDev()){ 
      this.router.navigate(['/login']);
      return false;
    } else {
      return true;
    }
  }
  
}