import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Router} from '@angular/router';
import {AuthService} from './auth/auth.service';

@Injectable({
  providedIn: 'root'
})
export class InterceptorService {

  constructor(
    private router: Router,
    private _auth: AuthService) {
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if (!request.headers.has('Content-Type')) {
      request = request.clone({headers: request.headers.set('Content-Type', 'application/json')});
    }
    if (!request.headers.has('Access-Control-Allow-Origin')) {
      request = request.clone({headers: request.headers.set('Access-Control-Allow-Origin', '*')})
    }
    request = request.clone({headers: request.headers.set('Accept', 'application/json')})
    return next.handle(request)
  }
}
