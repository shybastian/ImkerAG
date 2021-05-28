import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {map} from "rxjs/operators";
import {Notification} from "../../main/notification-view/notification";
import {AuthService} from "../auth/auth.service";

@Injectable({
  providedIn: 'root'
})
export class NotificationApiService {

  private REST_API_SERVER = "http://localhost:8080/imker/v1/notifications";

  constructor(private httpClient: HttpClient, private _auth: AuthService) {
  }

  getAllUnreadNotifications() {
    let queryParam = "?userId=" + this._auth.getId();
    return this.httpClient.get<Notification[]>(this.REST_API_SERVER + queryParam).pipe(map(res => {
      return res;
    }));
  }
}
