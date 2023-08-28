import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/entity/user.model';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit{
  login: User = {
    login: '',
    senha: '',
  };

  constructor(){}

  ngOnInit(): void {

  }

  onSubmit(){}
}
