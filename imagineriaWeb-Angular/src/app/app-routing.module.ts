import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { ProfileComponent } from './profile/profile.component';
import { RegisterComponent } from './components/register/register.component';
import { ObrasComponent } from './components/obras/obras.component';
import { FormCreateObraComponent } from './components/obras/form-create-obra/form-create-obra.component';
import { ImagineroComponent } from './components/imaginero/imaginero.component';

const routes: Routes = [
  
  { path: "", component: AppComponent, pathMatch: "full" },
  { path: "login", component: LoginComponent, pathMatch: "full" },
  { path: "register", component: RegisterComponent, pathMatch: "full" },
  { path: "obras", component: ObrasComponent, pathMatch: "full" },
  { path: 'agregar-obra', component: FormCreateObraComponent },
  { path: 'imagineros', component: ImagineroComponent}
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
