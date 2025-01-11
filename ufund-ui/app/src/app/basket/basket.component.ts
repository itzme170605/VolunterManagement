import { Component, OnInit } from '@angular/core';
import { BasketService } from '../basket.service';
import { Need } from '../need';

@Component({
  selector: 'app-basket',
  templateUrl: './basket.component.html',
  styleUrls: ['./basket.component.css']
})
export class BasketComponent implements OnInit {
  needsInBasket: Need[] = [];

  constructor(private basketService: BasketService) {}

  ngOnInit(): void {
    this.getBasketItems();
  }

  getBasketItems(): void {
    this.needsInBasket = this.basketService.getBasketItems();
  }

  removeFromBasket(need: Need): void {
    this.basketService.removeFromBasket(need);
    this.getBasketItems(); // Refresh the basket list
  }

  checkout(): void {
    this.basketService.checkout();
    this.getBasketItems(); // Refresh the basket list to reflect it is empty
  }
}
