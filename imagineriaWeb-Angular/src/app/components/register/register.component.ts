import { Component } from "@angular/core";
import { Router } from "@angular/router";
import { AuthService } from "../../services/auth.service";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { MatSnackBar } from "@angular/material/snack-bar";

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

  constructor(private fb: FormBuilder, private userService: AuthService, private router: Router, private snackBar: MatSnackBar) { }

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
          this.snackBar.open(error.error.mensaje, 'close', {
            duration: 5000, 
            verticalPosition: 'top'
          })
        }
      );
  }
}