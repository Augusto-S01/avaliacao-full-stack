import { Component } from '@angular/core';
import { MatIcon, MatIconModule } from '@angular/material/icon';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [MatIconModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent {
  hide = false;
  usuario = 'Usuário';
  saldo = 'R$ 0,00';

  constructor(private Router: Router) { }

  irParaAgendarTransferencia(){
    this.Router.navigate(['/agendar-transferencia']);
  }

  irParaHistorico(){
    this.Router.navigate(['/historico']);
  }
}
