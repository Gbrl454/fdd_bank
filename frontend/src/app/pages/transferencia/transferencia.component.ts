import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-transferencia',
  templateUrl: './transferencia.component.html',
  styleUrls: ['./transferencia.component.scss'],
})
export class TransferenciaComponent {
  formTransf!: FormGroup;

  constructor(private formBuilder: FormBuilder) {}

  ngOnInit(): void {
    this.formTransf = this.formBuilder.group({
      valor: [null, Validators.compose([Validators.required])],
      idDestino: [null, Validators.compose([Validators.required])],
    });
  }

  habilitarBotao(): string {
    return this.formTransf.valid ? 'botao' : 'botao_desabilitado';
  }

  async onSubmit() {}
}
