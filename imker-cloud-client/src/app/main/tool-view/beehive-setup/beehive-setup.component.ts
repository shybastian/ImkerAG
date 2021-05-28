import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {BusinessApiService} from "../../../services/businessApi/business-api.service";
import {Beehive} from "../../beehive-view/beehive";

@Component({
  selector: 'app-beehive-setup',
  templateUrl: './beehive-setup.component.html',
  styleUrls: ['./beehive-setup.component.scss']
})
export class BeehiveSetupComponent implements OnInit {
  headElements = ['ID', 'Weight', 'Temperature', 'Population Number', 'Current Activity'];

  form!: FormGroup;
  isSubmitError: boolean;
  isCallSuccess: boolean;
  submitErrorMessage: string;
  beehives: Beehive[];

  constructor(private _businessApi: BusinessApiService,
              public formBuilder: FormBuilder) {
    this.isSubmitError = false;
    this.isCallSuccess = false;
    this.submitErrorMessage = '';
    this.beehives = [];
  }

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      nrBeehives: ['', Validators.required],
    });
  }

  submitNumberOfBeehives() {
    let nrOfBeehives = this.form.value.nrBeehives;
    console.log(nrOfBeehives);
    this._businessApi.postInitialSetup(nrOfBeehives).subscribe((data: Beehive[]) => {
        if (data !== undefined && data !== null && data.length !== 0) {
          this.beehives = data;
          this.beehives.sort((a, b) => {
            return a.id - b.id;
          });
          this.isCallSuccess = true;
        }
      },
      error => {
        this.isCallSuccess = false;
        this.isSubmitError = true;
        if (error.status === 500) {
          this.submitErrorMessage = error.message;
        } else {
          this.submitErrorMessage = error.message;
        }
      });
  }
}
