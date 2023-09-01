import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/login/login.component';
import { ContaComponent } from './pages/conta/conta.component';
import { TransferenciaComponent } from './pages/transferencia/transferencia.component';
import { TemplateNoAuthComponent } from './templates/template-no-auth/template-no-auth.component';
import { TemplateAuthComponent } from './templates/template-auth/template-auth.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { RegisterComponent } from './pages/register/register.component';
import { InputMaskModule } from 'primeng/inputmask';
import { InputNumberModule } from 'primeng/inputnumber';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    ContaComponent,
    TransferenciaComponent,
    TemplateNoAuthComponent,
    TemplateAuthComponent,
    RegisterComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    InputMaskModule,
    InputNumberModule
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
