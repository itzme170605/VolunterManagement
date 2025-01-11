import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { NeedSearchComponent } from './need-search/need-search.component';
import { FormsModule } from '@angular/forms';
import { BasketComponent } from './basket/basket.component';
import { ScheduleComponent } from './schedule/schedule.component';
import { NeedComponent } from './need/need.component';
import { EditCupboardComponent } from './edit-cupboard/edit-cupboard.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { CalendarComponent } from './calendar/calendar.component';
import { DonateComponent } from './donate/donate.component';
import { NeedHelperComponent } from './need-helper/need-helper.component';

@NgModule({
  declarations: [
    AppComponent,
    NeedComponent,
    NeedSearchComponent,
    BasketComponent,
    ScheduleComponent,
    EditCupboardComponent,
    LoginComponent,
    RegisterComponent,
    DashboardComponent,
    CalendarComponent,
    DonateComponent,
    NeedHelperComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
