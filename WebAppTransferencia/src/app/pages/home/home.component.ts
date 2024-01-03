import { UsuarioService } from './../../services/usuario.service';
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
  numeroDaconta = '000000';

  constructor(
    private Router: Router,
    private autenticacaoService : AutenticacaoService,
    private usuarioService : UsuarioService,
    ) { }
  ngOnInit(): void {
    const cookieUsuario = this.autenticacaoService.getUsuarioLogado();
    if(cookieUsuario){
      this.usuario = cookieUsuario;
    }
   this.usuarioService.getUsuarioLogado().subscribe((response: any) => {
      this.numeroDaconta = response.accountNumber;
      this.saldo = response.balance;
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
