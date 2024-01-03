import { UsuarioService } from './../../services/usuario.service';
import { SaldoService } from './../../services/saldo.service';
import { AutenticacaoService } from './../../services/autenticacao.service';
import { Component, OnInit } from '@angular/core';
import { MatIcon, MatIconModule } from '@angular/material/icon';
import { Router } from '@angular/router';
import { MoedaRealPipe } from '../../pipes/moeda-real.pipe';
import { CurrencyPipe } from '@angular/common';
import { UsuaroLogadoDTO } from '../../interface/usuaro-logado-dto';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    MatIconModule,
    CurrencyPipe],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent implements OnInit {
  hide = false;
  usuario = 'Usuário';
  saldo = 0;
  numeroDaconta = 0;



  constructor(
    private Router: Router,
    private autenticacaoService : AutenticacaoService,
    private usuarioService : UsuarioService,
    ) { }
  ngOnInit(): void {
   this.usuarioService.getUsuarioLogado().subscribe((response: UsuaroLogadoDTO) => {
      this.numeroDaconta = response.accountNumber;
      this.saldo = response.balance;
      this.usuario = response.username;
    });


  }

  irParaAgendarTransferencia(){
    this.Router.navigate(['/agendar-transferencia']);
  }

  irParaHistorico(){
    this.Router.navigate(['/historico']);
  }

  logout(){
    this.autenticacaoService.logout();
  }
}
