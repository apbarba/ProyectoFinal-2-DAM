import { Component } from "@angular/core";
import { Router } from "@angular/router";
import { AuthService } from "../services/auth.service";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";

@Component({
  selector: "app-register",
  templateUrl: "./register.component.html",
  styleUrls: ["./register.component.css"],
})
export class RegisterComponent {

  loginForm = this.fb.group({
    name: ['', Validators.required],
    username: ['', Validators.required],
    email: ['', [Validators.required, Validators.email]],
    password: ['', Validators.required],
    verifyPassword: ['', [Validators.required]]
  })

  constructor(private fb: FormBuilder, private userService: AuthService, private router: Router) { }

  register() {
    if (!this.loginForm.controls.name.value || 
      !this.loginForm.controls.username.value || 
      !this.loginForm.controls.email.value ||
      !this.loginForm.controls.password.value || 
      !this.loginForm.controls.verifyPassword.value) {
        return;
       }

      this.userService.register(
        this.loginForm.controls.name.value,
        this.loginForm.controls.username.value,
        this.loginForm.controls.email.value,
        this.loginForm.controls.password.value,
        this.loginForm.controls.verifyPassword.value
      ).subscribe(data => {
        this.userService.setToken(data.token);
      this.router.navigateByUrl("/")
      },
        error => {
          console.log(error);
        }
      );
  }
}