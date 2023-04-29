import { Component, Input, OnInit } from "@angular/core";
import { AuthService } from "../../services/auth.service";
import { Router } from "@angular/router";
import { User } from "src/app/models/user.model";

@Component({
  selector: "app-home",
  templateUrl: "./home.component.html",
  styleUrls: ["./home.component.css"],
})
export class HomeComponent implements OnInit {

  user: User | null = null;
  isUserLoggedIn = false;

  slides = [
    { image: 'https://picsum.photos/id/10/600/400', title: 'Slide 1', subtitle: 'Subtitle 1' },
    { image: 'https://picsum.photos/id/20/600/400', title: 'Slide 2', subtitle: 'Subtitle 2' },
    { image: 'https://picsum.photos/id/30/600/400', title: 'Slide 3', subtitle: 'Subtitle 3' }
  ]; 
 
  constructor(public userService: AuthService, private authService: AuthService, private router: Router) {
  }
  
  ngOnInit() {
    this.getUserLogged();
   
  }
  
  getUserLogged() {
    
    this.userService.getUser().subscribe((user) => {
   
      console.log(user);
   
    });
  }

  isLoggedIn() {
    return localStorage.getItem('authToken') !== null;
  }
}
