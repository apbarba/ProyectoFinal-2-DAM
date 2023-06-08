import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { User } from 'src/app/models/user.model';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent{
  
user: User | null = null;

 constructor(private authService: AuthService, private router: Router,) { }

 ngOnInit() {
  this.authService.currentUser.subscribe(user => {
    if (user && user.id) {
      this.user = user;
    } else {
      console.log('Usuario no encontrado o no tiene id.');
    }
  });
}


  onLogout() {
    this.authService.logout();
    this.router.navigateByUrl("/login")
  }

  
  isLoggedIn() {
    return localStorage.getItem('authToken') !== null;
  }

  onFileSelected(event: any) {
    const file: File = event.target.files[0];
    if (file && this.user && this.user.id) {
        this.authService.changeAvatar(this.user.id, file).subscribe({
            next: (response) => {
                console.log('Avatar cambiado con éxito.');
                // Tratar la respuesta como un objeto JSON
                const jsonResponse = JSON.parse(response);
                // Extraer la propiedad "avatarFilename"
                const avatarFilename = jsonResponse.avatarFilename;
                // Actualizar la URL de la imagen
                if(this.user){
                    this.user.avatar = `http://localhost:8080/download/${avatarFilename}`;
                }
            },
            error: (err) => console.log('Error al cambiar el avatar.', err)
        });
    }
}



  
  

}
