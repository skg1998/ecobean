import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ForgetPasswordComponent } from './pages/auth/forget-password/forget-password.component';
import { LoginComponent } from './pages/auth/login/login.component';
import { ProfileComponent } from './pages/auth/profile/profile.component';
import { RegisterComponent } from './pages/auth/register/register.component';
import { CartComponent } from './pages/cart/cart/cart.component';
import { CheckoutComponent } from './pages/cart/checkout/checkout.component';
import { PaymentComponent } from './pages/cart/payment/payment.component';
import { HomeComponent } from './pages/home/home/home.component';
import { ProductDescriptionComponent } from './pages/product/product-description/product-description.component';
import { ProductsComponent } from './pages/product/products/products.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'product', component: ProductsComponent },
  { path: 'product/:id', component: ProductDescriptionComponent },
  { path: 'cart', component: CartComponent },
  { path: 'checkout', component: CheckoutComponent },
  { path: 'payment', component: PaymentComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'forget-password', component: ForgetPasswordComponent },
  { path: '**', redirectTo: '' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
