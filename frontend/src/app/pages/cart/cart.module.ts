import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CheckoutComponent } from './checkout/checkout.component';
import { CartComponent } from './cart/cart.component';
import { PaymentComponent } from './payment/payment.component';

@NgModule({
  declarations: [CheckoutComponent, CartComponent, PaymentComponent],
  imports: [CommonModule, ReactiveFormsModule, FormsModule],
})
export class CartModule {}
