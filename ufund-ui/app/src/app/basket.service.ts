import { Injectable } from '@angular/core';
import { Need } from './need';
import { ScheduleService } from './schedule.service';
import { UserService } from './user.service';
import { Helper } from './user.model';
import { NeedService } from './need.service';

@Injectable({
  providedIn: 'root'
})
export class BasketService {
  public basket: Need[] = [];
  constructor(private scheduleService: ScheduleService, private userService: UserService, private needService:NeedService) {}

addToBasket(need: Need): boolean {
  // Check if the need is already in the basket
  const isAlreadyInBasket = this.basket.some(item => item.id === need.id); // Assuming 'id' is a unique identifier of 'Need'

  if (isAlreadyInBasket || !need.active) {
    console.log('This item is already in the basket. or the need is closed');
    return false;
  } else {
    // Add the need to the basket if it's not already there
    this.basket.push(need);
    console.log(this.userService.helper);
    this.userService.helper.basket = this.basket || [];
    console.log(this.userService.username);
    this.userService.updateUser(this.userService.helper).subscribe(iuser => {
      console.log("subscribed update:", iuser)
    });
  }
  return true;
  }

  setBasket(userBasket: Need[]): void{
    
    this.basket = userBasket ? userBasket : [];
  }


  getBasketItems(): Need[] {
    // this.basket = this.userService.helper.basket;
    return this.basket;
  }

  removeFromBasket(need: Need): void {
    this.basket = this.basket.filter(item => item.id !== need.id);
    this.userService.helper.basket = this.basket || [];
    this.userService.updateUser(this.userService.helper).subscribe(iuser => {
      console.log("removed:need",iuser);
    });
  }

  checkout(): void {
    // Move each need from the basket to the schedule
    this.basket.forEach(need => {
      
      const isAlreadyInSchedule = need.volunteers.some(item => item === this.userService.helper.userName);
      if (isAlreadyInSchedule || !need.active) {
          console.log('This item is already in the the schedule. or the need is closed');
      } else {
        this.scheduleService.addToSchedule(need);
        need.volunteers.push(this.userService.helper.userName);
        need.volunteerCount+=1;
        if(need.volunteerCount === need.required){
          need.active = false;
        }else{
          need.active = true;
        }
        console.log(need);
        this.needService.updateNeed(need).subscribe(ineed=>{
        console.log("in subscribe",ineed);
        });
      }
    });
    // Clear the basket after checkout
    this.basket = [];
    this.userService.helper.basket = this.basket || [];
    console.log("before update user in checkout:", this.userService.helper);
    this.userService.updateUser(this.userService.helper).subscribe(iuser => {
      console.log("removed:need",iuser);
    });
  }
}
