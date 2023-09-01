import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { User } from 'src/app/models/entity/user.model';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss'],
})
export class RegisterComponent implements OnInit {
  formRegister!: FormGroup;

  constructor(private formBuilder: FormBuilder) {}

  ngOnInit(): void {
    this.formRegister = this.formBuilder.group({
      nome: [null, Validators.compose([Validators.required])],
      email: [
        null,
        Validators.compose([Validators.required, Validators.email]),
      ],
      senha: [null, Validators.compose([Validators.required])],
      confSenha: [null, Validators.compose([Validators.required])],
    });
  }

  habilitarBotao(): string {
    return this.formRegister.valid ? 'botao' : 'botao_desabilitado';
  }

  senhasIguais() {
    const senha = this.formRegister.get('senha')?.value;
    const confSenha = this.formRegister.get('confSenha')?.value;
    return senha === confSenha;
  }

  async onSubmit() {}
}
