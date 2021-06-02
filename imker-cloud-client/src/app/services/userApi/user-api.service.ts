import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {map} from "rxjs/operators";
import {UserToAdd} from "../../main/register/user";

@Injectable({
  providedIn: 'root'
})
export class UserApiService {

  private REST_API_SERVER = "http://localhost:8080/imker/v1/users";

  constructor(private httpClient: HttpClient) {
  }

  loginUser(payload: string) {
    return this.httpClient.post(this.REST_API_SERVER + "/login", payload).pipe(map(res => {
      return res;
    }))
  }

  registerUser(payload: UserToAdd) {
    return this.httpClient.post(this.REST_API_SERVER, payload).pipe(map(res => {
      return res;
    }))
  }
}
