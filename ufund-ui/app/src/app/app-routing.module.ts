import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BasketComponent } from './basket/basket.component';
import { ScheduleComponent } from './schedule/schedule.component';
import { NeedComponent } from './need/need.component';
import { EditCupboardComponent } from './edit-cupboard/edit-cupboard.component';
import { NeedSearchComponent } from './need-search/need-search.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { DonateComponent } from './donate/donate.component';
import { NeedHelperComponent } from './need-helper/need-helper.component';

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'needs/:id', component: NeedComponent },
  { path: 'basket', component: BasketComponent },
  { path: 'schedule', component: ScheduleComponent },
  { path: 'edit-cupboard', component: EditCupboardComponent },
  { path: 'donate', component: DonateComponent },
  { path: 'needs-helper/:id', component: NeedHelperComponent }
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
