import * as authReducer from '../store/reducers/auth.reducer';
import * as productReducer from '../store/reducers/product.reducer';
import * as orderReducer from '../store/reducers/order.reducer';
import * as cartReducer from '../store/reducers/cart.reducer';
import * as paymentReducer from '../store/reducers/payment.reducer';

import { ActionReducerMap } from '@ngrx/store';

export interface AppState {}

export const reducers: ActionReducerMap<AppState> = {
  auth: authReducer.authReducer,
  cart: cartReducer.cartReducer,
  order: orderReducer.orderReducer,
  product: productReducer.productReducer,
  payment: paymentReducer.paymentReducer,
};
