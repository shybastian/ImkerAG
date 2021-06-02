import {AfterViewInit, Component, OnInit} from '@angular/core';
import {BeehiveApiService} from "../../services/beehiveApi/beehive-api.service";
import {Activity, Beehive} from "./beehive";
import {faCheck} from "@fortawesome/free-solid-svg-icons/faCheck";
import {faStop} from "@fortawesome/free-solid-svg-icons/faStop";

@Component({
  selector: 'app-beehive-view',
  templateUrl: './beehive-view.component.html',
  styleUrls: ['./beehive-view.component.css']
})
export class BeehiveViewComponent implements OnInit, AfterViewInit {
  faCheckmark = faCheck
  faX = faStop

  headElements = ['', 'ID', 'Weight', 'Temperature', 'Population Number', 'Current Activity', 'Beehive Status', 'Ready For Harvest?'];

  public beehives: Beehive[];
  public noBeehives: boolean;

  private beehiveCoolingIcon: string;
  private beehiveHeatingIcon: string;

  private areBeehivesForUser: boolean;
  public switchButtonText: string;

  constructor(private _beehiveApi: BeehiveApiService) {
    this.beehives = [];
    this.noBeehives = false;

    this.beehiveCoolingIcon = "assets/beehive-cooling.gif";
    this.beehiveHeatingIcon = "assets/beehive-heating.gif";

    this.areBeehivesForUser = true;
    this.switchButtonText = "Click here to see all Beehives!";
  }


  ngOnInit(): void {
  }

  /**
   * Lifecycle triggered after Angular fully initialized the component's view.
   * Get all Beehive data from the server.
   */
  ngAfterViewInit(): void {
    this.areBeehivesForUser = true;
    this._beehiveApi.getBeehivesBelongingToUser().subscribe((data: Beehive[]) => {
      if (data.length === 0) {
        this.noBeehives = true;
      } else {
        this.beehives = data;
        this.beehives.sort((a, b) => {
          return a.id - b.id;
        })
      }
    })
  }

  switchButton() {
    if (this.areBeehivesForUser) {
      this.switchButtonText = "Click here to see your Beehives!";
    } else {
      this.switchButtonText = "Click here to see all Beehives!";
    }
    this.areBeehivesForUser = !this.areBeehivesForUser;
  }

  switchBeehives(): void {
    if (this.areBeehivesForUser) {
      this._beehiveApi.getAllBeehives().subscribe((data: Beehive[]) => {
        if (data.length === 0) {
          this.noBeehives = true;
        } else {
          this.beehives = data;
          this.beehives.sort((a, b) => {
            return a.id - b.id;
          })
        }
      })
    } else {
      this._beehiveApi.getBeehivesBelongingToUser().subscribe((data: Beehive[]) => {
        if (data.length === 0) {
          this.noBeehives = true;
        } else {
          this.beehives = data;
          this.beehives.sort((a, b) => {
            return a.id - b.id;
          })
        }
      })
    }
    this.switchButton();
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
