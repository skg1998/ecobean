import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { CustomerCartDTO } from '../../interfaces/Cart';
import { CustomerService } from '../customer/customer.service';

@Injectable({
  providedIn: 'root',
})
export class CartService {
  constructor(
    private http: HttpClient,
    private customerService: CustomerService
  ) {}
  emailId = this.customerService.currentUserValue.emailId;

  addProductToCart(customerCartDto: CustomerCartDTO) {
    return this.http.post(
      `${environment.BASE_URL}/customercart-api/products`,
      customerCartDto
    );
  }

  getProductsFromCart() {
    return this.http.get(
      `${environment.BASE_URL}/customercart-api/customer/${this.emailId}/products`
    );
  }

  deleteProductFromCart(productId: number) {
    return this.http.delete(
      `${environment.BASE_URL}/customercart-api/customer/${this.emailId}/products/${productId}`
    );
  }

  modifyQuantityOfProductInCart(productId: number, quantity: number) {
    return this.http.put(
      `${environment.BASE_URL}/customercart-api/customer/${this.emailId}/products/${productId}`,
      quantity
    );
  }

  deleteAllProductsFromCart() {
    return this.http.delete(
      `${environment.BASE_URL}/customercart-api/customer/${this.emailId}/products`
    );
  }
}
