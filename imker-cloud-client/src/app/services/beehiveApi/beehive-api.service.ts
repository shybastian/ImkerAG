import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {map} from "rxjs/operators";
import {Beehive} from "../../main/beehive-view/beehive";
import {AuthService} from "../auth/auth.service";

@Injectable({
  providedIn: 'root'
})
export class BeehiveApiService {

  private REST_API_SERVER = "http://localhost:8080/imker/v1/beehives";

  constructor(private httpClient: HttpClient, private _auth: AuthService) {
  }

  getAllBeehives() {
    return this.httpClient.get<Beehive[]>(this.REST_API_SERVER).pipe(map(res => {
      return res;
    }));
  }

  getBeehivesBelongingToUser() {
    let userIdQueryParam = "?userId=" + this._auth.getId();
    return this.httpClient.get<Beehive[]>(this.REST_API_SERVER + userIdQueryParam).pipe(map(res => {
      return res;
    }))
  }
}
