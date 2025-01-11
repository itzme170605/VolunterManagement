// src/app/components/login/login.component.ts

import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../user.service';
import { Helper, User } from '../user.model';
import { BasketService } from '../basket.service';
import { ScheduleService } from '../schedule.service';
import { NeedService } from '../need.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  userName: string = '';
  password: string = '';
  errorMessage: string = '';

  constructor(private userService: UserService, private router: Router, private basketService: BasketService, private scheduleService:ScheduleService, private needService:NeedService) { }

  // login(): void {
  //   this.userService.login(this.userName, this.password).subscribe({
  //     next: (response) => {
  //       // Handle successful login
  //       localStorage.setItem('user', JSON.stringify(response)); // Save user data in local storage
  //       this.router.navigate(['/dashboard']); // Redirect to home page
  //     },
  //     error: (err) => {
  //       // Handle error (e.g., show message to user)
  //       this.errorMessage = 'Invalid username or password';
  //     }
  //   });
  // }


  login(): void {
    const loginUser: User = {
      type: "helper",
      userName: this.userName,
      password: this.password,
      manager: false
    };

    this.userService.login(loginUser).subscribe(
      (user) => {
        // Save user in sessionStorage or similar
        // sessionStorage.setItem('user', JSON.stringify(user));
        this.userService.username = user.userName;
        console.log("in login",user);
        this.userService.helper = user as Helper;
        console.log("casted to helper",this.userService.helper);
        this.needService.getNeeds().subscribe(needs => {
          // Filter out the items in the basket that do not exist in the backend needs
          this.userService.helper.basket = this.userService.helper.basket?.filter(item => {
            const needExists = needs.some(backendNeed => backendNeed.id === item.id);
            if (needExists) {
              console.log(`Need with ID ${item.id} exists in the backend.`);
              return true;  // Keep this item in the basket
            } else {
              console.log(`Need with ID ${item.id} does not exist in the backend. Removing from basket.`);
              this.basketService.removeFromBasket(item);
              return false; // Remove this item from the basket
            }
          })|| [];
        this.basketService.setBasket(this.userService.helper.basket);
        this.scheduleService.setSchedule(this.userService.helper.schedule);
        });
        
        // this.userService.loggedIn = true;
        if(user.type == "admin"){
          this.router.navigate(['/edit-cupboard']);
        } else {
          this.router.navigate(['/dashboard']);
        }
      },
      error => {
        this.errorMessage = 'Invalid username or password';
      }
    );
    console.log("helper",this.userService.helper);
  }

  goToRegister(): void {
    this.router.navigate(['/register']);
  }
}




