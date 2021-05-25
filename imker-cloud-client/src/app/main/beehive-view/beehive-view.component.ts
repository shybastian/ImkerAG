import {AfterViewInit, Component, OnInit} from '@angular/core';
import {BeehiveApiService} from "../../services/beehiveApi/beehive-api.service";
import {Activity, Beehive} from "./beehive";

@Component({
  selector: 'app-beehive-view',
  templateUrl: './beehive-view.component.html',
  styleUrls: ['./beehive-view.component.css']
})
export class BeehiveViewComponent implements OnInit, AfterViewInit {
  headElements = ['', 'ID', 'Weight', 'Temperature', 'Population Number', 'Current Activity', 'Beehive Status', 'Ready For Harvest?'];

  public beehives: Beehive[];
  public noBeehives: boolean;

  private beehiveCoolingIcon: string;
  private beehiveHeatingIcon: string;

  constructor(private _beehiveApi: BeehiveApiService) {
    this.beehives = [];
    this.noBeehives = false;

    this.beehiveCoolingIcon = "assets/beehive-cooling.gif";
    this.beehiveHeatingIcon = "assets/beehive-heating.gif";
  }


  ngOnInit(): void {
  }

  /**
   * Lifecycle triggered after Angular fully initialized the component's view.
   * Get all Beehive data from the server.
   */
  ngAfterViewInit(): void {
    this._beehiveApi.getAllBeehives().subscribe((data: Beehive[]) => {
      if (data.length === 0){
        this.noBeehives = true;
      } else {
        this.beehives = data;
      }
    })
  }

  isCold(temperature: number) {
    return temperature < 35
  }

  isHot(temperature: number) {
    return temperature > 35
  }

  isNormalTemperature(temperature: number) {
    return temperature === 35
  }

  isHyperactive(type: Activity) {
    var castString: string = Activity[Activity.HYPERACTIVE];
    // @ts-ignore
    return type === castString;
  }

  isSlow(type: Activity) {
    var castString: string = Activity[Activity.SLOW];
    // @ts-ignore
    return type === castString;
  }

  isNormal(type: Activity) {
    var castString: string = Activity[Activity.NORMAL];
    // @ts-ignore
    return type === castString;
  }


}
