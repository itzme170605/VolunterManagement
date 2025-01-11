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
  selector: 'app-need-helper',
  templateUrl: './need-helper.component.html',
  styleUrls: ['./need-helper.component.css']
})
export class NeedHelperComponent implements OnInit {
  need: Need | undefined;
  isScheduled: boolean = false;
  volunteer!: Helper;

  constructor(
    private route: ActivatedRoute,
    private needService: NeedService,
    private location: Location,
    private basketService: BasketService,
    private scheduleService: ScheduleService,
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
}
