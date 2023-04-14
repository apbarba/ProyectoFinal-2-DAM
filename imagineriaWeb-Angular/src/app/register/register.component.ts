import { Component } from "@angular/core";
import { Router } from "@angular/router";
import { AuthService } from "../services/auth.service";

@Component({
  selector: "app-register",
  templateUrl: "./register.component.html",
  styleUrls: ["./register.component.css"],
})
export class RegisterComponent {
  email = '';
  password = '';
  passwordError!: boolean;
  name = '';
  verifyPassword = '';

  constructor(public userService: AuthService, private router: Router) {}
  
  register() {
    const user = { email: this.email, password: this.password };
    this.userService.register(user).subscribe(data => {
      this.userService.setToken(data.token);
      this.router
    },
    error => {
      console.log(error);
    }
    );
  }
}