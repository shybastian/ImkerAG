import {AfterViewInit, Component, OnInit} from '@angular/core';
import {BeehiveApiService} from "../../services/beehiveApi/beehive-api.service";
import {Beehive, Beehives} from "./beehive";

@Component({
  selector: 'app-beehive-view',
  templateUrl: './beehive-view.component.html',
  styleUrls: ['./beehive-view.component.css']
})
export class BeehiveViewComponent implements OnInit, AfterViewInit {
  headElements = ['ID', 'Weight', 'Temperature', 'Population Number'];

  public beehives: Beehive[] = [];

  constructor(private _beehiveApi: BeehiveApiService) {
  }


  ngOnInit(): void {

  }

  ngAfterViewInit(): void {
    this._beehiveApi.getAllBeehives().subscribe((data: Beehives) => {
      this.beehives = [];
      console.log("after")
      console.log(data)
      this.beehives = data.beehives;
    })
  }

}
