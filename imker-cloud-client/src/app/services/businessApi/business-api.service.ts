import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Beehive} from "../../main/beehive-view/beehive";
import {map} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class BusinessApiService {

  private REST_API_SERVER = "http://localhost:8080/imker/v1/business";

  constructor(private httpClient: HttpClient) {
  }

  postInitialSetup(nrBeehives: number) {
    if (nrBeehives != null || nrBeehives != undefined) {
      let nrBeehivesQueryParam = "?nrBeehives=" + nrBeehives;
      console.log(nrBeehivesQueryParam);
      return this.httpClient.post<Beehive[]>(this.REST_API_SERVER + "/initialSetup" + nrBeehivesQueryParam, null).pipe(map(res => {
        return res;
      }))
    } else {
      return this.httpClient.post<Beehive[]>(this.REST_API_SERVER + "/initialSetup", null).pipe(map(res => {
        return res;
      }));
    }
  }

}
