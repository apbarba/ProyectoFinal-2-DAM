import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AppComponent } from './app.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LoginComponent } from './components/login/login.component';
import { HomeComponent } from './components/home/home.component';
import { ProfileComponent } from './components/profile/profile.component';
import { RegisterComponent } from './components/register/register.component';
import { CookieService } from "ngx-cookie-service";
import { MaterialImportsModule } from './material-imports/material-imports.module';
import { ObrasComponent } from './components/obras/obras.component';
import { FormCreateObraComponent } from './components/obras/form-create-obra/form-create-obra.component';
import { ImagineroComponent } from './components/imaginero/imaginero.component';
import { FormCreateImagineroComponent } from './components/imaginero/form-create-imaginero/form-create-imaginero.component';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { FormEditImagineroComponent } from './components/imaginero/form-edit-imaginero/form-edit-imaginero.component';
import { TokenInterceptor } from './interceptors/token.interceptor';
import { FormEditObrasComponent } from './components/obras/form-edit-obras/form-edit-obras.component';
import { EditPasswordComponent } from './components/profile/edit-password/edit-password.component';
import { CategoriaComponent } from './components/categoria/categoria.component';
import { FormEditCategoriaComponent } from './components/categoria/form-edit-categoria/form-edit-categoria.component';




@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    ProfileComponent,
    RegisterComponent,
    ObrasComponent,
    FormCreateObraComponent,
    ImagineroComponent,
    FormCreateImagineroComponent,
    FormEditImagineroComponent,
    FormEditObrasComponent,
    EditPasswordComponent,
    CategoriaComponent,
    FormEditCategoriaComponent
  ],
  imports: [
    AppRoutingModule,
    HttpClientModule,
    BrowserModule,
    FormsModule,
    MatInputModule,
    MatButtonModule,
    MatCardModule,
    MatFormFieldModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    MaterialImportsModule
  ],
  providers: [CookieService, {
    provide: HTTP_INTERCEPTORS, 
    useClass: TokenInterceptor,
    multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
