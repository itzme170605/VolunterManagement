import { Need } from './need';
import { NeedService } from './need.service';
import { UserService } from './user.service';
import { Injectable, EventEmitter } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ScheduleService {
  public schedule!: { need: Need; dateTime: Date }[];
  scheduleUpdated = new EventEmitter<void>();
  constructor(private userService:UserService, private needService:NeedService){}

  addToSchedule(need: Need): void {
    const existingNeed = this.schedule.find(scheduledNeed => scheduledNeed.need.id === need.id);

    if (existingNeed) {
      console.log(`Need with ID ${need.id} is already scheduled.`);
      return; 
    }

    const scheduledNeed = { need, dateTime: new Date() };
    this.schedule.push(scheduledNeed);
    this.userService.helper.schedule = this.schedule;
    console.log("added schedule",this.userService.helper)
    console.log(JSON.stringify(this.userService.helper));
    this.userService.updateUser(this.userService.helper).subscribe(iuser => {
      console.log("after subscribing in schedule",iuser);
    });

    this.scheduleUpdated.emit();
  }

  setSchedule(userSchedule: { need: Need; dateTime: Date }[]): void {
    this.schedule = userSchedule ? userSchedule : [];
  }

  getSchedule(): { need: Need; dateTime: Date }[] {
    return this.schedule ? this.schedule : [];
  }

  isInSchedule(needId: number): boolean {
    return this.schedule.some(item => item.need.id == needId);
  }
  updateSchedule(newSchedule: { need: Need; dateTime: Date }[]) {
    this.schedule = newSchedule;
    this.scheduleUpdated.emit(); // Emit the update event
  }
  removeFromSchedule(need: Need): void {
    this.schedule = this.schedule.filter(item => item.need.id !== need.id);
    this.userService.helper.schedule = this.schedule || [];
    this.userService.updateUser(this.userService.helper).subscribe(iuser => {
      console.log("removed:need",iuser);
      need.volunteerCount-=1;
        if(need.volunteerCount == need.required){
          need.active = false;
        }else{
          need.active = true;
        }
        console.log(need);
        this.needService.updateNeed(need).subscribe(ineed=>{
        console.log("in subscribe",ineed);
        });
    });
  }
}
