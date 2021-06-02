import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {UserApiService} from "../../services/userApi/user-api.service";
import {AuthService} from "../../services/auth/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
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
      password: ['', Validators.required],
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', Validators.required],
    });
  }

  register() {
    let user = this.form.value
    this._userApi.registerUser(user).subscribe((res: any) => {
        if (res !== undefined && res !== null) {
          this.loginError = false;
          this.loginErrorMessage = "";

          this._auth.setDataInLocalStorage('id', res.id)
          this._auth.setDataInLocalStorage('username', res.username)
          this._auth.setDataInLocalStorage('firstName', res.firstName)
          this._auth.setDataInLocalStorage('lastName', res.lastName)
          this._auth.setDataInLocalStorage('email', res.email)

          this.router.navigate(['dashboard/beehives']);
        }
      },
      error => {
        this.loginError = true;
        this.loginErrorMessage = error.message;
      });
  }

  moveToLoginView() {
    this.router.navigate(["./login"]);
  }
}
