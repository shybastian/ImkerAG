import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {map} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class UserApiService {

  private REST_API_SERVER = "http://localhost:8080/imker/v1/users";

  constructor(private httpClient: HttpClient) {
  }

  getTypeRequest(url: string) {
    return this.httpClient.get(this.REST_API_SERVER + url).pipe(map(res => {
      return res;
    }));
  }

  loginUser(payload: string) {
    return this.httpClient.post(this.REST_API_SERVER + "/login", payload).pipe(map(res => {
      return res;
    }))
  }

  // postTypeRequest(url: string, payload: string) {
  //   return this.httpClient.post(this.REST_API_SERVER+url, payload).pipe(map(res => {
  //     return res;
  //   }));
  // }

  putTypeRequest(url: string, payload: string) {
    return this.httpClient.put(this.REST_API_SERVER + url, payload).pipe(map(res => {
      return res;
    }))
  }
}
