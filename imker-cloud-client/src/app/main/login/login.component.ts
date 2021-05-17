import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {AuthService} from "../../services/auth/auth.service";
import {Router} from "@angular/router";
import {UserApiService} from "../../services/userApi/user-api.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  form!: FormGroup;

  constructor(private _userApi: UserApiService,
              private _auth: AuthService,
              private router: Router,
              public formBuilder: FormBuilder) {
  }

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  login() {
    let b = this.form.value
    console.log(b)
    this._userApi.loginUser(b).subscribe((res: any) => {
      console.log("Response of Login:")
      console.log(res)
      if (res !== undefined && res !== null) {
        console.log("We're saving the data")
        this._auth.setDataInLocalStorage('id', res.id)
        this._auth.setDataInLocalStorage('username', res.username)
        this._auth.setDataInLocalStorage('firstName', res.firstName)
        this._auth.setDataInLocalStorage('lastName', res.lastName)
        this._auth.setDataInLocalStorage('email', res.email)
        console.log("Auth contains the following:")
        console.log(localStorage)
        this.router.navigate(['dashboard/beehives'])
      }
    }, err => {
      console.log("Error encountered")
      console.log(err)
    });
  }

}
