import { Component } from '@angular/core';
import { Need } from '../need';
import { NeedService } from '../need.service';

@Component({
  selector: 'app-edit-cupboard',
  templateUrl: './edit-cupboard.component.html',
  styleUrls: ['./edit-cupboard.component.css']
})
export class EditCupboardComponent {
  needs: Need[] = [];
  selectedNeed: any;
  
  // Define available time slots
  timeSlots: string[] = [
    '08:00:00',
    '09:00:00',
    '10:00:00',
    '11:00:00',
    '12:00:00',
    '13:00:00',
    '14:00:00',
    '15:00:00',
    '16:00:00',
    '17:00:00',
  ];

  constructor(private needService: NeedService) {}

  ngOnInit(): void {
    this.getNeeds();
  }

  getNeeds(): void {
    this.needService.getNeeds().subscribe(needs => this.needs = needs);
  }

  add(name: string, facility: string, description: string, date: string, time: string, reqstring: string): void {
    name = name.trim();
    facility = facility.trim();
    description = description.trim();
    date = date.trim();
    time = time.trim();
    let required = +reqstring;

    if (!name || !facility || !description || !date || !time) { return; }

    // Combine the selected date and time into a datetime string
    const datetime = `${date}T${time}`;

    // Add the new need with the combined datetime
    this.needService.addNeed({ 
      name, 
      facility, 
      description, 
      datetime, 
      required 
    } as Need).subscribe(need => {
      need.active = need.active ? need.active : true;
      this.needs.push(need);
      this.needService.updateNeed(need);
    });
  }

  delete(need: Need): void {
    this.needs = this.needs.filter(n => n !== need);
    this.needService.deleteNeed(need.id).subscribe();
  }
}
