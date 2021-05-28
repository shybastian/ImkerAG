import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {AuthService} from "../../services/auth/auth.service";
import {BusinessApiService} from "../../services/businessApi/business-api.service";

@Component({
  selector: 'app-navigate-menu',
  templateUrl: './navigate-menu.component.html',
  styleUrls: ['./navigate-menu.component.css']
})
export class NavigateMenuComponent implements OnInit {
  isToolsButtonActive: boolean;

  constructor(private router: Router,
              private activatedRoute: ActivatedRoute,
              public _auth: AuthService,
              private _business: BusinessApiService) {
    this.isToolsButtonActive = false;
  }

  ngOnInit() {
  }

  atNotificationButtonPress() {
    this.router.navigate(['./notifications'], {relativeTo: this.activatedRoute});
  }

  atBeehivesButtonPress() {
    this.router.navigate(['./beehives'], {relativeTo: this.activatedRoute})
  }

  atLogout() {
    localStorage.setItem("instantiatedIntervalMethod", "true");
    localStorage.clear();
    this.router.navigate(['../login']);
  }

  clickToolsButton() {
    this.isToolsButtonActive = !this.isToolsButtonActive;
  }

  initialSetupBeehives() {
    this.router.navigate(['./tools/beehives'], {relativeTo: this.activatedRoute})
  }
}
