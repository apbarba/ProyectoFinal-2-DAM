import { Component, OnInit } from '@angular/core';
import { Route, Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  loginForm = this.fb.group({
    username: ['', Validators.required],
    password: ['', Validators.required]
  })
  
  constructor(private fb: FormBuilder, private userService: AuthService, private router: Router) {}
  
  login() {
    if (!this.loginForm.controls.username.value || !this.loginForm.controls.password.value) {
      return;
    }

    this.userService.login(
      this.loginForm.controls.username.value,
      this.loginForm.controls.password.value
    ).subscribe(data => {
      this.userService.setToken(data.token);
      this.router.navigateByUrl("/")
    });
  }
}
