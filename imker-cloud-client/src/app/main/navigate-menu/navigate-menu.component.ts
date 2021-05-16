import {Component, OnInit} from '@angular/core';
import {Router, ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-navigate-menu',
  templateUrl: './navigate-menu.component.html',
  styleUrls: ['./navigate-menu.component.css']
})
export class NavigateMenuComponent implements OnInit {
  constructor(private router: Router, private activatedRoute: ActivatedRoute) {}

  ngOnInit() {}

  atNotificationButtonPress(){
    this.router.navigate(['./notifications'], {relativeTo: this.activatedRoute});
  }

  atBeehivesButtonPress(){
    this.router.navigate(['./beehives'], {relativeTo: this.activatedRoute})
  }

  atLogout() {
    localStorage.setItem("instantiatedIntervalMethod", "true");
    this.router.navigate(['../login']);
  }

}
