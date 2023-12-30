import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatIconModule} from '@angular/material/icon';
import {MatTooltipModule} from '@angular/material/tooltip';
import {MatButtonModule} from '@angular/material/button';
import { FormGroup, FormControl, ReactiveFormsModule } from '@angular/forms';

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
    ReactiveFormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'

})
export class LoginComponent implements OnInit{

  constructor(private Router : Router ) { }


  hide = true;



  loginForm = new FormGroup({
    usuario: new FormControl(''),
    senha: new FormControl(''),
  });



  ngOnInit(): void {
  }



  onSubmit(){
    this.Router.navigate(['/home']);
    console.log(this.loginForm.value);
  }
}
