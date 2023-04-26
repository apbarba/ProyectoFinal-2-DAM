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
import { FormEditImagineroComponent } from './components/imaginero/form-edit-imaginero/form-edit-imaginero.component';
import { FormEditObrasComponent } from './components/obras/form-edit-obras/form-edit-obras.component';
import { FormCreateImagineroComponent } from './components/form-create-imaginero/form-create-imaginero.component';

const routes: Routes = [
  
  { path: "", component: AppComponent, pathMatch: "full" },
  { path: "login", component: LoginComponent, pathMatch: "full" },
  { path: "register", component: RegisterComponent, pathMatch: "full" },
  { path: "obras", component: ObrasComponent, pathMatch: "full" },
  { path: 'agregar-obra', component: FormCreateObraComponent },
  { path: 'imaginero/create', component: FormCreateImagineroComponent},
  { path: 'imagineros', component: ImagineroComponent},
  {path: 'imaginero/editar/:id', component: FormEditImagineroComponent},
  {path: 'obras/editar/:id', component: FormEditObrasComponent}
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
