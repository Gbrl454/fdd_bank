import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TemplateNoAuthComponent } from './templates/template-no-auth/template-no-auth.component';
import { LoginComponent } from './pages/login/login.component';
import { HomeComponent } from './pages/home/home.component';
import { TemplateAuthComponent } from './templates/template-auth/template-auth.component';
import { ContaComponent } from './pages/conta/conta.component';
import { ExtratoComponent } from './pages/extrato/extrato.component';
import { TransferenciaComponent } from './pages/transferencia/transferencia.component';
import { authGuard } from './service/auth/guard/auth.guard';

const routes: Routes = [
  {
    path: '',
    component: TemplateAuthComponent,
    children: [
      { path: '', redirectTo: 'home', pathMatch: 'full' },
      { path: 'home', component: HomeComponent },
      { path: 'conta', component: ContaComponent },
      { path: 'extrato', component: ExtratoComponent },
      { path: 'transferencia', component: TransferenciaComponent },
    ],
    canActivate: [authGuard],
  },
  {
    path: '',
    component: TemplateNoAuthComponent,
    children: [
      { path: '', redirectTo: 'home', pathMatch: 'full' },
      { path: 'home', component: HomeComponent },
      { path: 'login', component: LoginComponent },
    ],
  },
  { path: '**', redirectTo: '' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
