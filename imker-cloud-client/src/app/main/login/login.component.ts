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
  loginError: boolean;
  loginErrorMessage: string;

  constructor(private _userApi: UserApiService,
              private _auth: AuthService,
              private router: Router,
              public formBuilder: FormBuilder) {
    this.loginError = false;
    this.loginErrorMessage = "";
  }

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  login() {
    let b = this.form.value
    this._userApi.loginUser(b).subscribe((res: any) => {
        if (res !== undefined && res !== null) {
          this.loginError = false;
          this.loginErrorMessage = "";

          this._auth.setDataInLocalStorage('id', res.id)
          this._auth.setDataInLocalStorage('username', res.username)
          this._auth.setDataInLocalStorage('firstName', res.firstName)
          this._auth.setDataInLocalStorage('lastName', res.lastName)
          this._auth.setDataInLocalStorage('email', res.email)
        }
      },
      error => {
        this.loginError = true;
        console.log(error);
        if (error.status === 404) {
          this.loginErrorMessage = "Username/Password combination not found!"
        } else {
          this.loginErrorMessage = error.message;
        }
      },
      () => {
        this.router.navigate(['dashboard/beehives']);
      });
  }

}
