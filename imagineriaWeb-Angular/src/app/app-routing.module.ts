import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { ProfileComponent } from './components/profile/profile.component';
import { RegisterComponent } from './components/register/register.component';
import { ObrasComponent } from './components/obras/obras.component';
import { FormCreateObraComponent } from './components/obras/form-create-obra/form-create-obra.component';
import { ImagineroComponent } from './components/imaginero/imaginero.component';
import { FormEditImagineroComponent } from './components/imaginero/form-edit-imaginero/form-edit-imaginero.component';
import { FormEditObrasComponent } from './components/obras/form-edit-obras/form-edit-obras.component';
import { FormCreateImagineroComponent } from './components/imaginero/form-create-imaginero/form-create-imaginero.component';
import { EditPasswordComponent } from './components/profile/edit-password/edit-password.component';
import { CategoriaComponent } from './components/categoria/categoria.component';
import { FormEditCategoriaComponent } from './components/categoria/form-edit-categoria/form-edit-categoria.component';
import { ListFavComponent } from './components/profile/list-fav/list-fav.component';
import { combineLatest } from 'rxjs';
import { ObrasCategoriaComponent } from './components/categoria/obras-categoria/obras-categoria.component';

const routes: Routes = [
  { path: "", redirectTo: "/login", pathMatch: "full" },
  { path:"home", component: HomeComponent},
  { path: "login", component: LoginComponent },
  { path: "register", component: RegisterComponent },
  { path: "home", component: AppComponent },
  { path: "obras", component: ObrasComponent },
  { path: 'agregar-obra', component: FormCreateObraComponent,},
  { path: 'imaginero/create', component: FormCreateImagineroComponent,},
  { path: 'imagineros', component: ImagineroComponent},
  { path: 'imaginero/editar/:id', component: FormEditImagineroComponent},
  { path: 'obras/editar/:id', component: FormEditObrasComponent },
  { path: 'perfil', component: ProfileComponent},
  { path: 'edit-password', component: EditPasswordComponent},
  { path: 'categorias', component: CategoriaComponent},
  { path: 'categoria/editar/:id', component: FormEditCategoriaComponent},
  { path: 'favoritos', component: ListFavComponent},
  { path: 'categoria/:id/obras', component: ObrasCategoriaComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
