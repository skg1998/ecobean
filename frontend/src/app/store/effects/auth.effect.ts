import { Injectable } from '@angular/core';
import { Actions, ofType, createEffect } from '@ngrx/effects';
import { of } from 'rxjs';
import { catchError, concatMap, map, switchMap } from 'rxjs/operators';
import { Router } from '@angular/router';

import * as AuthActions from '../actions/auth.action';
import * as CartActions from '../actions/cart.action';
import * as OrderActions from '../actions/order.action';

import { CustomerService } from 'src/app/core/sevices/customer/customer.service';
import { User } from 'src/app/core/interfaces/User';

@Injectable()
export class AuthEffects {
  // signUp = createEffect(() =>
  //   this.actions$.pipe(
  //     ofType(AuthActions.SIGN_UP),
  //     map((action: AuthActions.SignUp) => {
  //       return action.payload;
  //     }),
  //     switchMap((user: User) => {
  //       return this.accountService.signup(user)
  //         .pipe(switchMap(res => {
  //           return [
  //             {
  //               type: AuthActions.SIGN_UP_SUCCESS,
  //               payload: { effect: AuthActions.SIGN_UP }
  //             }
  //             ,
  //             {
  //               type: AuthActions.SIGN_IN,
  //               payload: {user}
  //             }
  //           ];
  //         }), catchError(error => of(new AuthActions.AuthError({ error, errorEffect: AuthActions.SIGN_UP }))));
  //     })
  //   )
  // )

  // signIn = createEffect(() =>
  //   this.actions$.pipe(
  //     ofType(AuthActions.SIGN_IN),
  //     map((action: AuthActions.SignIn) => {
  //       return action.payload;
  //     }),
  //     switchMap((credentials: { email: string, password: string}) => {
  //       return this.accountService.login(credentials.email, credentials.password)
  //         .pipe(switchMap(res => {
  //           //this.tokenService.saveToken(res);
  //           this.router.navigate(['/']);
  //           return [
  //             { type: AuthActions.SIGN_IN_SUCCESS, payload: { effect: AuthActions.SIGN_IN } },
  //             { type: AuthActions.FETCH_VERIFICATION_STATUS }
  //           ];
  //         }), catchError(error => of(new AuthActions.AuthError({ error, errorEffect: AuthActions.SIGN_IN }))));
  //     })
  //   )
  // )

  // signOut = createEffect(() =>
  //   this.actions$.pipe(
  //     ofType(AuthActions.SIGN_OUT),
  //     concatMap((action: AuthActions.SignOut) => {
  //       this.accountService.logout();
  //       return [
  //         {
  //           type: AuthActions.SIGN_OUT_SUCCESS
  //         },
  //         {
  //           type: CartActions.EMPTY_CART_SUCCESS // clearing memory
  //         },
  //         {
  //           type: OrderActions.EMPTY_ORDER // clearing memory
  //         }
  //       ];
  //     })
  //   )
  // )

  // checkIfLoggedIn = createEffect(() =>
  //   this.actions$.pipe(
  //   ofType(AuthActions.CHECK_IF_LOGGED_IN),
  //     switchMap((action: AuthActions.CheckIfLoggedIn) => {
  //       if (this.accountService.checkIfTokenExists()) {
  //         return [
  //           {
  //             type: AuthActions.SIGN_IN_SUCCESS, payload: { effect: AuthActions.SIGN_IN_SUCCESS }
  //           },
  //           {
  //             type: AuthActions.FETCH_VERIFICATION_STATUS
  //           }
  //         ];
  //       }
  //       return [{
  //         type: AuthActions.SIGN_OUT_SUCCESS, payload: { effect: AuthActions.SIGN_OUT }
  //       }];
  //     })
  //   )
  // )

  // //updateProfile
  // updateProfile = createEffect(() => {
  //   this.actions$.pipe(
  //     ofType(AuthActions.UPDATE_PROFILE),
  //     map((action: AuthActions.UpdateProfile) => {
  //       return action.payload;
  //     }),
  //     switchMap((action: AuthActions.UpdateProfile) => {
  //       this.accountService.updateProfile(user: User).pipe()
  //     })
  //   );
  // })

  //changePassword
  //updateShippingAddress
  //deleteShippingAddress
  constructor(
    private actions$: Actions,
    private router: Router,
    private accountService: CustomerService
  ) {}
}
