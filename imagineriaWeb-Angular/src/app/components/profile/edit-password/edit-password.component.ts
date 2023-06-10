import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user.model';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-edit-password',
  templateUrl: './edit-password.component.html',
  styleUrls: ['./edit-password.component.css']
})
export class EditPasswordComponent {
  
  oldPassword!: string;
  newPassword!: string;
  verifyNewPassword!: string;
  user!: User;

  constructor(private userService: AuthService) {
    this.userService.currentUser.subscribe(user => this.user = user);
  }

  ngOnInit(): void {
  }

  editPassword() {
    if (this.newPassword !== this.verifyNewPassword) {
      console.log('Las nuevas contraseñas no coinciden');
      return;
    }
    this.userService.changePassword(this.oldPassword, this.newPassword, this.verifyNewPassword, this.user)
      .subscribe(
        response => {
          console.log('La contraseña se cambió con éxito');
        },
        error => {
          console.log(error);
        }
      );
  }
}
