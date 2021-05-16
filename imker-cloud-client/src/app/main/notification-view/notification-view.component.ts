import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';


@Component({
  selector: 'app-notification-view',
  templateUrl: './notification-view.component.html',
  styleUrls: ['./notification-view.component.scss'],
})
export class NotificationViewComponent implements OnInit {

  notifications: Notification[] = [];

  constructor(private router: Router) {}

  /**
   * When this component is initialized,
   * this function is called.
   * It initializes the values of the notifications in the table
   */
  ngOnInit() {
    // debugger;
    // this.userService.getUserNotifications(this.storageService.getUserWithoutIdRolesCounterStatusFromSessionStorage().username)
    //   .subscribe(notifications => {
    //     this.notifications = notifications;
    //   }, Error => {
    //     alert(this.translate.instant("NOTIFICATIONS_VIEW.ERROR"));
    //   });
  }

}
