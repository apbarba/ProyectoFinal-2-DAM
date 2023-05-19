import { Component } from '@angular/core';
import { AuthService } from './services/auth.service';
import { User } from './models/user.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
[x: string]: any;
  title = 'imagineriaWeb-Angular';

  user: User | null = null;
  isUserLoggedIn = false;

  constructor(private authService: AuthService, private router: Router) {
    this.authService.currentUserSubject.subscribe(user => {
      this.user = user;
      this.isUserLoggedIn = !!user;
    });
  }

}
