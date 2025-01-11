import { Component, OnInit } from '@angular/core';

import { Observable, Subject } from 'rxjs';
import { debounceTime, distinctUntilChanged, isEmpty, switchMap } from 'rxjs/operators';
import { Need } from '../need';
import { NeedService } from '../need.service';
import { BasketService } from '../basket.service';

@Component({
  selector: 'app-need-search',
  templateUrl: './need-search.component.html',
  styleUrls: ['./need-search.component.css'],
})
export class NeedSearchComponent implements OnInit {
  needs$!: Observable<Need[]>; // Observable to hold the needs
  searchTerms = new Subject<string>(); // Subject to hold search terms
  searchTerm: string = ''; // Local variable to hold current search term
  message: string = '';

  constructor(private needService: NeedService, private basketService: BasketService) {}

  search(): void {
    if (this.searchTerm === '') {
      this.needs$ = this.needService.getNeeds();
    } else {
      this.needs$ = this.needService.searchNeeds(this.searchTerm);
    }
  }

  ngOnInit(): void {
    this.needs$ = this.needService.getNeeds();
    this.message = "";
  }

  addToBasket(need: Need): void {
    if (need) {
      if(!this.basketService.addToBasket(need)){
        this.message = "Already in the basket, or the need is closed"
      }else{
        this.message = "added to basket!";
      }
    }
  }
}
