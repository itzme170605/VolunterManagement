import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from './user.service';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  
  title = 'Aspiration Cottage';
  isLoggedIn = false;
  isAdmin = false;
  constructor(private router: Router, private userService:UserService) {}
  ngOnInit(): void {
    // Check if the user is logged in
    this.userService.loggedIn$.subscribe((loggedIn) => {
      this.isLoggedIn = loggedIn; // Update the isLoggedIn flag whenever it changes
      if(this.userService.username == "admin"){
        this.isAdmin = true;
      }else{
        this.isAdmin = false;
      }
    });
  }
  logout() {
        // Add your logout logic here (e.g., clearing session or token)
        console.log('User logged out');
        this.userService.logout();
        // this.isLoggedIn = false;
        this.router.navigate(['/login']); // Redirect to login page after logout
    }
}
