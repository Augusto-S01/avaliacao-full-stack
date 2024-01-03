import { Component } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatTooltipModule } from '@angular/material/tooltip';

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
    ReactiveFormsModule,],
  templateUrl: './agendar-transferencia.component.html',
  styleUrl: './agendar-transferencia.component.scss'
})
export class AgendarTransferenciaComponent {

  constructor(
    private formBuilder: FormBuilder,
  ) { }
  agendarForm!: FormGroup
  ngOnInit() {
    this.agendarForm = this.formBuilder.group({

      conta: ['', [
        Validators.required,
        Validators.minLength(6),
        Validators.maxLength(6)
      ]],
     });
    }

  prosseguir(){
    console.log('prosseguir');
  }

}
