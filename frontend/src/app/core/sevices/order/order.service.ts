import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { OrderDTO } from '../../interfaces/Order';
import { CustomerService } from '../customer/customer.service';

@Injectable({
  providedIn: 'root',
})
export class OrderService {
  constructor(
    private http: HttpClient,
    private customerService: CustomerService
  ) {}

  emailId = this.customerService.currentUserValue.emailId;

  placeOrder(orderDTO: OrderDTO) {
    return this.http.post(
      `${environment.BASE_URL}/customerorder-api/place-order`,
      orderDTO
    );
  }

  getOrderDetails(orderId: string) {
    return this.http.get(
      `${environment.BASE_URL}/customerorder-api/order/${orderId}`
    );
  }

  getOrdersOfCustomer() {
    return this.http.get(
      `${environment.BASE_URL}/customerorder-api/customer/${this.emailId}/orders`
    );
  }

  updateOrderAfterPayment(orderId: string, transactionStatus: string) {
    return this.http.put(
      `${environment.BASE_URL}/customerorder-api/order/${orderId}/update/order-status`,
      transactionStatus
    );
  }

  updatePaymentOption(orderId: string, paymentThrough: string) {
    return this.http.put(
      `${environment.BASE_URL}/customerorder-api/order/${orderId}/update/payment-through`,
      paymentThrough
    );
  }
}
