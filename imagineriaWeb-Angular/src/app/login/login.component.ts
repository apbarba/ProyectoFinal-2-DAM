import { Component, OnInit } from '@angular/core';
import { Route, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  username = '';
  password = '';
  
  constructor(public userService: AuthService, private router: Router) { }
  
  login() {
    this.userService.login(this.username, this.password).subscribe(data => {
      this.userService.setToken(data.token);
      this.router.navigateByUrl("/")
    });
  }
}
