import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { TransferenciaAPI } from '../utils/const';
import { CookiesService } from './cookies.service';
import { map, subscribeOn, take } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SaldoService {

  constructor(
    private httpClient: HttpClient,
    private cookiesService: CookiesService,
  ) { }


  getSaldo(){
    const token = this.cookiesService.getCookie('token');
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.httpClient.get(TransferenciaAPI.getSaldo, {headers}).pipe(
      take(1),
      map((response: any) => {
        return response;
      }
      )
    )
  }

}
