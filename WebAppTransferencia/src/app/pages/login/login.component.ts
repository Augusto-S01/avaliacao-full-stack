import { AutenticacaoService } from './../../services/autenticacao.service';



import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatIconModule} from '@angular/material/icon';
import {MatTooltipModule} from '@angular/material/tooltip';
import {MatButtonModule} from '@angular/material/button';
import { FormGroup, FormControl, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { catchError } from 'rxjs';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    MatFormFieldModule,
    MatInputModule,
    MatIconModule ,
    MatButtonModule,
    MatTooltipModule,
    MatIconModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'

})
export class LoginComponent implements OnInit{

  constructor(
    private router : Router,
    private autenticacaoService : AutenticacaoService
    ) { }


  hide = true;



  loginForm = new FormGroup({
    usuario: new FormControl(''),
    senha: new FormControl(''),
  });



  ngOnInit(): void {
  }



  onSubmit(){
    const usuario = this.loginForm.value.usuario;
    const senha = this.loginForm.value.senha;

    if(usuario && senha){
      this.autenticacaoService.login(usuario, senha).pipe(
        catchError(error => {
          console.log('Erro ao fazer login: ', error);
          return of(null);
        })
       )
       .subscribe(
          (response: any) => {
            if(response){
              this.autenticacaoService.salvarSessao(response);
              this.router.navigate(['/home']);
            }}
       );


    }

  }
}
function of(arg0: null): any {
  throw new Error('Function not implemented.');
}

