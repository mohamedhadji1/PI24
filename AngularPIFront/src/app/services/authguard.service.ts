import { Injectable } from "@angular/core";
import { Router, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree } from "@angular/router";
import { Observable } from "rxjs";
import { UserService } from "./user.service";
import Swal from "sweetalert2";

@Injectable({
  providedIn: 'root'
})
export class AuthguardService {

  constructor(
    private userService: UserService,
    private router: Router
  ) {}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ):
    | Observable<boolean | UrlTree>
    | Promise<boolean | UrlTree>
    | boolean
    | UrlTree {

    // Get user roles from UserService
    const userRoles = this.userService.getRoles();
      console.error("Role : "+userRoles)
    // Get required roles from route data
    const requiredRoles = route.data["roles"] as string[];

    // Check if user has any roles
    if (!userRoles || userRoles.length === 0) {
      // Redirect to login page and preserve the attempted URL
      return this.router.createUrlTree(['/authentication/login'], { queryParams: { returnUrl: state.url } });
    }

    // Check if user has any of the required roles
    const hasRequiredRole = userRoles.some((role: string) => requiredRoles.includes(role));

    if (!hasRequiredRole) {
      this.showAlert('You do not have permission to access this page!');

      // Redirect to unauthorized page or login page and preserve the attempted URL
      return this.router.createUrlTree(['/login'], { queryParams: { returnUrl: state.url } });
    }

    // User has required roles, allow access
    return true;
  }
  private showAlert(message: string) {
    Swal.fire({
      title: 'Access Denied',
      text: message,
      icon: 'error',
      confirmButtonText: 'OK'
    });
  }
}
