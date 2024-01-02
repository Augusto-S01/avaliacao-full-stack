import { CookiesService } from './cookies.service';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { TransferenciaAPI } from '../utils/const';
import { catchError } from 'rxjs/operators';
import { of, throwError } from 'rxjs';
import { CookieSession } from '../interface/cookie-session';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AutenticacaoService {


  constructor(
    private httpClient: HttpClient,
    private CookiesService: CookiesService,
    private router : Router
  ) { }


  login(usuario: any, senha: any) {
    return this.httpClient.post(TransferenciaAPI.login, {username : usuario, password : senha})
  }

  salvarSessao(response: CookieSession){
    this.CookiesService.setCookie('token', response.token) ;
    this.CookiesService.setCookie('username', response.username);

  }

  logout(){
    this.CookiesService.deleteCookie('token');
    this.CookiesService.deleteCookie('username');
    this.router.navigate(['/login']);
  }

  getUsuarioLogado(){
    return this.CookiesService.getCookie('username');
  }
}
