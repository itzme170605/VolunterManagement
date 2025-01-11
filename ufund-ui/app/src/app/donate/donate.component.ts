import { Component } from '@angular/core';
import { UserService } from '../user.service';

@Component({
  selector: 'app-donate',
  templateUrl: './donate.component.html',
  styleUrls: ['./donate.component.css']
})
export class DonateComponent {
  pledgeAmount: number = 0;

  constructor(public userService: UserService) {}

  // Function to handle pledging
  pledgeDonation(): void {
    if (this.pledgeAmount > 0) {
      // Update the user's donated amount
      this.userService.helper.donated = (this.userService.helper.donated || 0) + this.pledgeAmount;

      // Call updateUser to sync with the backend
      this.userService.updateUser(this.userService.helper).subscribe(
        (updatedUser) => {
          console.log('Donation updated successfully', updatedUser);
          this.pledgeAmount = 0; // Reset pledge input
        },
        (error) => {
          console.error('Error updating donation:', error);
        }
      );
    }
  }
}
