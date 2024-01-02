import { CanActivateFn, Router } from '@angular/router';
import { CookiesService } from '../services/cookies.service';

export const tokenGuardGuard: CanActivateFn = (route, state) => {

  const cookiesService = new CookiesService();
  const routerService = new Router();
  const token = cookiesService.getCookie('token');
  if (token) {
    return true;
  }
  routerService.navigate(['/login']);
  return false;

};
