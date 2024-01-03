import { HistoricoTransferenciaDTO } from './../../interface/historico-transferencia-dto';
import { TransferenciaService } from './../../services/transferencia.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MatTableModule } from '@angular/material/table';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-historico',
  standalone: true,
  imports: [
    MatTableModule,
    CommonModule ],
  templateUrl: './historico.component.html',
  styleUrl: './historico.component.scss'
})
export class HistoricoComponent implements OnInit{

  historico: HistoricoTransferenciaDTO[] = [];
  displayedColumns: string[] = ['dataAgendamento', 'destinatario', 'remetente', 'valor', 'dataTransferencia', 'taxa', 'status'];
  dataSource = this.historico;

  constructor(
    private routerService: Router,
    private transferenciaService: TransferenciaService
  ) { }
  ngOnInit(): void {
    this.transferenciaService.buscarHistorico().subscribe((response: HistoricoTransferenciaDTO[] | any) => {
      if(response){
        this.historico = response;
      }
    });
  }

  home() {
    this.routerService.navigate(['home']);
  }

}
