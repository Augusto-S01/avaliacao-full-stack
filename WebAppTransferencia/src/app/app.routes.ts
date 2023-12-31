
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { NgModule } from '@angular/core';
import { LogoutComponent } from './pages/logout/logout.component';
import { HomeComponent } from './pages/home/home.component';
import { AgendarTransferenciaComponent } from './pages/agendar-transferencia/agendar-transferencia.component';


export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'prefix' }, // Set login as the default route
  { path: 'login', component: LoginComponent },
  { path: 'logout', component: LogoutComponent },
  { path: 'home', component: HomeComponent },
  { path: "agendar-transferencia", component : AgendarTransferenciaComponent },

];

@NgModule({
  declarations: [],
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
