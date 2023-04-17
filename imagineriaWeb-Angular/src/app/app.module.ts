import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AppComponent } from './app.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LoginComponent } from './components/login/login.component';
import { HomeComponent } from './components/home/home.component';
import { ProfileComponent } from './profile/profile.component';
import { RegisterComponent } from './components/register/register.component';
import { CookieService } from "ngx-cookie-service";
import { MaterialImportsModule } from './material-imports/material-imports.module';
import { ObrasComponent } from './components/obras/obras.component';
import { FormCreateObraComponent } from './components/obras/form-create-obra/form-create-obra.component';
import { ImagineroComponent } from './components/imaginero/imaginero.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    ProfileComponent,
    RegisterComponent,
    ObrasComponent,
    FormCreateObraComponent,
    ImagineroComponent
  ],
  imports: [
    AppRoutingModule,
    HttpClientModule,
    BrowserModule,
    FormsModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    MaterialImportsModule
  ],
  providers: [CookieService],
  bootstrap: [AppComponent]
})
export class AppModule { }
