import { CookiesService } from './cookies.service';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { TransferenciaAPI } from '../utils/const';
import { catchError } from 'rxjs/operators';
import { of, throwError } from 'rxjs';
import { CookieSession } from '../interface/cookie-session';

@Injectable({
  providedIn: 'root'
})
export class LoginService {


  constructor(
    private httpClient: HttpClient,
    private CookiesService: CookiesService
  ) { }


  login(usuario: any, senha: any) {
    return this.httpClient.post(TransferenciaAPI.login, {username : usuario, password : senha})
  }

  salvarSessao(response: CookieSession){
    console.log("chegou aqui")
    this.CookiesService.setCookie('token', response.token) ;
    this.CookiesService.setCookie('username', response.username);

  }
}
