import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {LoginComponent} from './main/login/login.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {InterceptorService} from "./services/interceptor-service.service";
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { DashboardComponent } from './main/dashboard/dashboard.component';
import { NavigateMenuComponent } from './main/navigate-menu/navigate-menu.component';
import { NotificationViewComponent } from './main/notification-view/notification-view.component';
import { BeehiveViewComponent } from './main/beehive-view/beehive-view.component';
import {MatSidenavModule} from "@angular/material/sidenav";
import {MatToolbarModule} from "@angular/material/toolbar";
import {ExtendedModule, FlexModule} from "@angular/flex-layout";
import { BeehiveSetupComponent } from './main/tool-view/beehive-setup/beehive-setup.component';
import { RegisterComponent } from './main/register/register.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    DashboardComponent,
    NavigateMenuComponent,
    NotificationViewComponent,
    BeehiveViewComponent,
    BeehiveSetupComponent,
    RegisterComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatSidenavModule,
    MatToolbarModule,
    FlexModule,
    ExtendedModule,
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS, useClass: InterceptorService, multi: true
    }
  ],
  bootstrap: [AppComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AppModule {
}
