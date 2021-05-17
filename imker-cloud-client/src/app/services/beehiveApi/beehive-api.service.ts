import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {map} from "rxjs/operators";
import {Beehives} from "../../main/beehive-view/beehive";

@Injectable({
  providedIn: 'root'
})
export class BeehiveApiService {

  private REST_API_SERVER = "http://localhost:8080/imker/v1/beehives";

  constructor(private httpClient: HttpClient) {
  }

  getAllBeehives() {
    return this.httpClient.get<Beehives>(this.REST_API_SERVER).pipe(map(res => {
      console.log(res);
      return res;
    }));
  }
}
