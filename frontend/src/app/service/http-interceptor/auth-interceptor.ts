import { DetailError } from './../../models/entity/detailError.model';
import { Injectable } from '@angular/core';
import {
  HttpInterceptor,
  HttpRequest,
  HttpHandler,
  HttpErrorResponse,
} from '@angular/common/http';
import { throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { AuthService } from '../auth/auth.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(private authService: AuthService) {}

  intercept(req: HttpRequest<any>, next: HttpHandler) {
    const token = this.authService.getAuthorizationToken();
    let request: HttpRequest<any> = req;

    if (token && !this.authService.isTokenExpired(token)) {
      request = req.clone({
        headers: req.headers
          .set('Access-Control-Allow-Origin', '*')
          .set('Authorization', `Bearer ${token}`),
      });
    }

    return next.handle(request).pipe(catchError(this.handleError));
  }

  private handleError(error: HttpErrorResponse) {
    if (error.status >= 400)
      if (error.error instanceof ErrorEvent) {
        return throwError(`Ocorreu um erro: ${error.error.message}`);
      } else {
        return throwError(JSON.stringify(error.error));
      }
    return throwError('Ocorreu um erro, tente novamente');
  }
}
