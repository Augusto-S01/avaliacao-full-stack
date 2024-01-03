
import { HttpClient } from '@angular/common/http';
import { AgendarTransferenciaComponent } from './../pages/agendar-transferencia/agendar-transferencia.component';
import { Injectable } from '@angular/core';
import { TransferenciaAPI } from '../utils/const';
import { CookiesService } from './cookies.service';

@Injectable({
  providedIn: 'root'
})
export class TransferenciaService {

  constructor(
    private httpClient : HttpClient,
    private cookiesService : CookiesService
  ) { }

  agendarTransferencia(accountNumber :any, quantidade :number, data: Date){
    const body = {
      "destinatarioAccountNumber": accountNumber,
      "valor": quantidade,
      "dataTransferencia": data
    }
    const headers = {
      'Authorization': `Bearer ${this.cookiesService.getCookie('token')}`
    }

    return this.httpClient.post(TransferenciaAPI.agendarTransferencia, body, {headers});
  }

  buscarHistorico(){
    const headers = {
      'Authorization': `Bearer ${this.cookiesService.getCookie('token')}`
    }
    return this.httpClient.get(TransferenciaAPI.buscarHistorico, {headers});
  }

}
