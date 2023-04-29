import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent{
  
  user = null;


 constructor(private authService: AuthService, private router: Router,) { }

  onLogout() {
    this.authService.logout();
    this.router.navigateByUrl("/login")
  }

  
  isLoggedIn() {
    return localStorage.getItem('authToken') !== null;
  }

}
