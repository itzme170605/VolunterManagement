import { Component, OnInit } from '@angular/core';
import { ScheduleService } from '../schedule.service';
import { Need } from '../need';


@Component({
  selector: 'app-calendar',
  templateUrl: './calendar.component.html',
  styleUrls: ['./calendar.component.css']
})
export class CalendarComponent implements OnInit {
strtoDate(arg0: string): Date {
  return new Date(arg0)
}
  days: { date: Date; needs: { need: Need; dateTime: Date }[] }[] = [];

  constructor(private scheduleService: ScheduleService) {}

  ngOnInit(): void {
    this.populateCalendar();
    this.scheduleService.scheduleUpdated.subscribe(() => {
      this.populateCalendar(); // Reload the calendar when schedule is updated
    });
  }

  populateCalendar(): void {
    this.days = [];  // Clear the existing data before repopulating
    const today = new Date();
    for (let i = 0; i < 7; i++) {
      const day = new Date(today);
      day.setDate(today.getDate() + i);
      const needsForDay = this.scheduleService
        .getSchedule()
        .filter(item => this.isSameDay(item.need.datetime, day));
      this.days.push({ date: day, needs: needsForDay });
    }
  }

  isSameDay(datetimeString: string, targetDate: Date): boolean {
    const needDate = new Date(datetimeString);
    return (
      needDate.getFullYear() === targetDate.getFullYear() &&
      needDate.getMonth() === targetDate.getMonth() &&
      needDate.getDate() === targetDate.getDate()
    );
  }
}
