import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from "./main/login/login.component";
import {AuthGuardService} from "./services/guards/auth-guard.service";
import {DashboardComponent} from "./main/dashboard/dashboard.component";
import {NotificationViewComponent} from "./main/notification-view/notification-view.component";
import {BeehiveViewComponent} from "./main/beehive-view/beehive-view.component";

const routes: Routes = [
  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full'
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'dashboard',
    component: DashboardComponent,
    canActivate: [AuthGuardService],
    children: [
      {
        path: 'beehives',
        component: BeehiveViewComponent,
      },
      {
        path: 'notifications',
        component: NotificationViewComponent,
      }
    ]
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
