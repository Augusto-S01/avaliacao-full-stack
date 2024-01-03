import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { take, map } from 'rxjs';
import { TransferenciaAPI } from '../utils/const';
import { CookiesService } from './cookies.service';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  constructor(
    private httpClient: HttpClient,
    private cookiesService: CookiesService,
  ) { }


  getUsuarioLogado() {
    const token = this.cookiesService.getCookie('token');
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.httpClient.get(TransferenciaAPI.getUsuarioLogado, {headers}).pipe(
      take(1),
      map((response: any) => {
        return response;
      }
      )
    )
  }
}
