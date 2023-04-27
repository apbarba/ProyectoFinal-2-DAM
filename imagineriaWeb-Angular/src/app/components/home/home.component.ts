import { Component, OnInit } from "@angular/core";
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
 
  constructor(public userService: AuthService, private authService: AuthService, private router: Router) {}
  
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
