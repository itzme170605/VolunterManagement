import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { Need } from '../need';
import { NeedService } from '../need.service';
import { BasketService } from '../basket.service';
import { ScheduleService } from '../schedule.service';
import { Helper } from '../user.model';
import { UserService } from '../user.service';

@Component({
  selector: 'app-need',
  templateUrl: './need.component.html',
  styleUrls: ['./need.component.css']
})
export class NeedComponent implements OnInit {
  need: Need | undefined;
  isScheduled: boolean = false;
  volunteer!: Helper;

  constructor(
    private route: ActivatedRoute,
    private needService: NeedService,
    private location: Location,
    private basketService: BasketService,
    private scheduleService: ScheduleService,
    private userService: UserService
  ) {}

  ngOnInit(): void {
    this.getNeed();
  }

  getNeed(): void {
    const id = parseInt(this.route.snapshot.paramMap.get('id')!, 10);
    this.needService.getNeed(id).subscribe(need => {
      this.need = need;
      // Check if this need is already scheduled
      this.isScheduled = this.scheduleService.isInSchedule(need.id);
    });
  }


  goBack(): void {
    this.location.back();
  }

  addToBasket(): void {
    if (this.need) {
      this.basketService.addToBasket(this.need);
      this.isScheduled = true; // Update after adding to schedule
    }
  }

  save(): void {
    // Checks to make sure the manager cannot push any blank info
    if(!this.need || !this.need.name || !this.need.description || !this.need.facility || !this.need.required || !this.need.datetime) {
      return;
    }

    if(this.need) {
      this.needService.updateNeed(this.need)
        .subscribe(() => this.goBack());
    }
  }
}
