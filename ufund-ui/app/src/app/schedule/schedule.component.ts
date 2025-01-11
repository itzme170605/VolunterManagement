import { Component, OnInit } from '@angular/core';
import { ScheduleService } from '../schedule.service';
import { Need } from '../need';

@Component({
  selector: 'app-schedule',
  templateUrl: './schedule.component.html',
  styleUrls: ['./schedule.component.css']
})
export class ScheduleComponent implements OnInit {
  scheduledNeeds: { need: Need; dateTime: Date }[] = [];

  constructor(private scheduleService: ScheduleService) {}

  ngOnInit(): void {
    this.getScheduledNeeds();
  }

  getScheduledNeeds(): void {
    this.scheduledNeeds = this.scheduleService.getSchedule();
  }

  removeFromSchedule(need:Need){
    this.scheduleService.removeFromSchedule(need);
  }
}
