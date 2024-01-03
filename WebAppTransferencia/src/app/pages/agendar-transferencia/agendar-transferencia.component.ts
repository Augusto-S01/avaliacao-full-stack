import { SaldoService } from './../../services/saldo.service';
import { UsuarioService } from './../../services/usuario.service';
import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatTooltipModule } from '@angular/material/tooltip';
import { EncontrarUsuarioDTO } from '../../interface/encontrar-usuario-dto';
import moment from 'moment';


@Component({
  selector: 'app-agendar-transferencia',
  standalone: true,
  imports: [
    MatFormFieldModule,
    MatInputModule,
    MatIconModule ,
    MatButtonModule,
    MatTooltipModule,
    MatIconModule,
    ReactiveFormsModule,
    CommonModule],
  templateUrl: './agendar-transferencia.component.html',
  styleUrl: './agendar-transferencia.component.scss'
})
export class AgendarTransferenciaComponent {

  constructor(
    private formBuilder: FormBuilder,
    private usuarioService : UsuarioService,
    private saldoService : SaldoService
  ) { }


  agendarForm!: FormGroup
  escolherConta = true;
  escolherQuantidade = false;
  escolherData = false;
  dataTransferencia = false;
  saldoAtual = 0;
  confirmacao = false;

  ngOnInit() {
    this.agendarForm = this.formBuilder.group({

      conta: ['', [
        Validators.required,
        Validators.max(999999),
        Validators.min(100000),

      ]],
      quantidade: ["",[]],
      data: ["",[]],
     });
    }

  prosseguirParaQuantidade(){
    if(this.agendarForm.valid){
      const conta = this.agendarForm.get('conta')?.value;
      this.usuarioService.verificaUsuario(conta).subscribe(
        (response: EncontrarUsuarioDTO) => {
          if(response.error){
            this.agendarForm.setErrors({contaInvalida: true})
          }
          else{
            this.escolherConta = false;
            this.escolherQuantidade = true;
            this.saldoService.getSaldo().subscribe(
              (response: any) => {
                this.saldoAtual = response;
                this.agendarForm.get('quantidade')?.setValidators([
                  Validators.required,
                  Validators.max(this.saldoAtual),
                  Validators.min(1),
                ])
                this.agendarForm.get('quantidade')?.updateValueAndValidity();
                this.agendarForm.get('quantidade')?.setValue(response);
              }
            )

          }
        }
      )
    }
  }

  prosseguirParaData(){
    const errosQuantidade = this.agendarForm.get('quantidade')?.errors;
    console.log(`errosQuantidade: ${errosQuantidade}`)
    if(!errosQuantidade){
      this.escolherQuantidade = false;
      this.escolherData = true;
      console.log("tudo certo!");
      this.agendarForm.get('data')?.setValidators([
        Validators.required,
        this.dateFutureValidator
      ])
    }
  }
  prosseguirParaConfirmacao(){
    const errosData = this.agendarForm.get('data')?.errors;
    if(!errosData){
      this.escolherData = false;
      this.dataTransferencia = false;
    }

  }


  private dateFutureValidator(control: FormControl): { [s: string]: boolean } {
    console.log("teste")
    if (control.value) {
     const date = moment(control.value);
     const today = moment();
     if (!date.isAfter(today)) {
       return { 'invalidDate': true };
     }
    }
    return {};
   }

}



