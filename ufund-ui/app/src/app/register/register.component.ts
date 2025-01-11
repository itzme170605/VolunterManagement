// src/app/components/register/register.component.ts

import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../user.service';
import { Manager, User } from '../user.model';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

  userName: string = '';
  password: string = '';
  confirmPassword: string = '';
  errorMessage: string = '';
  successMessage: string = '';

  constructor(private userService: UserService, private router: Router) { }

  register(): void {
    if (this.password !== this.confirmPassword) {
      this.errorMessage = 'Passwords do not match!';
      return;
    }

    const user = { userName: this.userName, password: this.password, type: "helper"} as User;

    this.userService.createUser(user).subscribe({
      next: () => {
        this.successMessage = 'Account created successfully!';
        setTimeout(() => this.router.navigate(['/login']), 2000); // Redirect to login after success
      },
      error: (err) => {
        this.errorMessage = 'Failed to create account. Please try again later.';
      }
    });
  }

  goToLogin(): void {
    this.router.navigate(['/login']);
  }
}
