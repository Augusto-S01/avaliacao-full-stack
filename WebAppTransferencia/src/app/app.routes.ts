
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { NgModule } from '@angular/core';
import { LogoutComponent } from './pages/logout/logout.component';
import { HomeComponent } from './pages/home/home.component';
import { tokenGuardGuard } from './guard/token-guard.guard';
import { RegisterComponent } from './pages/register/register.component';
import { AgendarTransferenciaComponent } from './pages/agendar-transferencia/agendar-transferencia.component';
import { HistoricoComponent } from './pages/historico/historico.component';


export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'prefix' }, // Set login as the default route
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent},
  { path: 'logout', component: LogoutComponent },
  { path: 'home', component: HomeComponent , canActivate: [tokenGuardGuard] },
  { path: "agendar-transferencia" , component: AgendarTransferenciaComponent, canActivate: [tokenGuardGuard]},
  { path: 'historico', component: HistoricoComponent, canActivate: [tokenGuardGuard]}


];

@NgModule({
  declarations: [],
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
