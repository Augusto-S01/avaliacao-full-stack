
import { HttpClientModule } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatTooltipModule } from '@angular/material/tooltip';
import { Router } from '@angular/router';
import { AutenticacaoService } from '../../services/autenticacao.service';
import { CommonModule } from '@angular/common';
import { catchError, of, pipe, take } from 'rxjs';
import { ErrorDTO } from '../../interface/error-dto';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [
    CommonModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule ,
    MatButtonModule,
    MatTooltipModule,
    MatIconModule,
    ReactiveFormsModule,
    HttpClientModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss'
})
export class RegisterComponent  implements OnInit{
  registerForm!: FormGroup
  hide = true;
  error = "";


  constructor(
    private formBuilder: FormBuilder,
    private routerService: Router,
    private autenticacaoService: AutenticacaoService) { }



  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      usuario: ['', [Validators.required]],
      senha: ['', [Validators.required]],
      repitaSenha: ['', [Validators.required]]
     });

  }


  checkPasswords(group: FormGroup | any) {

    let pass = group.get('senha').value;
    let confirmPass = group.get('repitaSenha').value;

    return pass === confirmPass ;
   }

  onSubmit(){

    if(this.registerForm.valid && this.checkPasswords(this.registerForm)){
        const usuario = this.registerForm.get('usuario')?.value;
        const senha = this.registerForm.get('senha')?.value;
        this.autenticacaoService.register(usuario,senha).pipe(
          take(1),
          catchError( error => {
            this.atualizaMessagemDeErro(error.error);
            return of(null);
          })
        )
        .subscribe(
          (response: any) => {
            if(response){
              this.routerService.navigate(['/login']);
            }
          }
        );
      }




  }

  atualizaMessagemDeErro(error : ErrorDTO){
    if(error.message = "User already exists"){
      this.error = "Usuário já existe";
    }
  }
  irParaLogin(){
    this.routerService.navigate(['/login']);
  }
}
