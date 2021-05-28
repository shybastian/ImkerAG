import {AfterViewInit, Component, OnInit} from '@angular/core';
import {NotificationApiService} from "../../services/notificationApi/notification-api.service";
import {Notification, NotificationType} from "./notification";


@Component({
  selector: 'app-notification-view',
  templateUrl: './notification-view.component.html',
  styleUrls: ['./notification-view.component.scss'],
})
export class NotificationViewComponent implements OnInit, AfterViewInit {
  headElements = ['Type', 'Message', 'Time Stamp'];

  public notifications: Notification[];
  public noNotifications: boolean;

  constructor(private _notificationApi: NotificationApiService) {
    this.notifications = [];
    this.noNotifications = false;
  }

  ngOnInit() {
  }

  ngAfterViewInit(): void {
    this._notificationApi.getAllUnreadNotifications().subscribe((data: Notification[]) => {
      if (data.length === 0) {
        this.noNotifications = true;
      } else {
        this.notifications = data;
        this.notifications.sort((a, b) => {
          return a.id - b.id;
        })
      }
    })
  }

  isCritical(type: NotificationType) {
    var castString: string = NotificationType[NotificationType.CRITICAL];
    // @ts-ignore
    return type === castString;
  }

  isWarning(type: NotificationType) {
    var castString: string = NotificationType[NotificationType.WARNING];
    // @ts-ignore
    return type === castString;
  }

  isInfo(type: NotificationType) {
    var castString: string = NotificationType[NotificationType.INFO];
    // @ts-ignore
    return type === castString;
  }

}
