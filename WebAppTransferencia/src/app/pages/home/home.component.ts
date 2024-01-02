import { SaldoService } from './../../services/saldo.service';
import { AutenticacaoService } from './../../services/autenticacao.service';
import { Component, OnInit } from '@angular/core';
import { MatIcon, MatIconModule } from '@angular/material/icon';
import { Router } from '@angular/router';
import { MoedaRealPipe } from '../../pipes/moeda-real.pipe';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [MatIconModule,MoedaRealPipe],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent implements OnInit {
  hide = false;
  usuario = 'Usuário';
  saldo = '0,00';

  constructor(
    private Router: Router,
    private autenticacaoService : AutenticacaoService,
    private saldoService : SaldoService
    ) { }
  ngOnInit(): void {
    const cookieUsuario = this.autenticacaoService.getUsuarioLogado();
    if(cookieUsuario){
      this.usuario = cookieUsuario;
    }
   this.saldoService.getSaldo().subscribe((saldo: any) => {

      this.saldo = saldo;
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
