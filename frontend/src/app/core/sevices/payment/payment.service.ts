import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { CardDTO, TransactionDTO } from '../../interfaces/Payment';
import { CustomerService } from '../customer/customer.service';

@Injectable({
  providedIn: 'root',
})
export class PaymentService {
  constructor(
    private http: HttpClient,
    private customerService: CustomerService
  ) {}

  emailId = this.customerService.currentUserValue.emailId;

  addNewCard(transactionDTO: TransactionDTO) {
    return this.http.post(
      `${environment.BASE_URL}/payment-api/customer/${this.emailId}/pay-order`,
      transactionDTO
    );
  }

  updateCustomerCard(cardDTO: CardDTO) {
    return this.http.put(
      `${environment.BASE_URL}/payment-api/update/card`,
      cardDTO
    );
  }

  deleteCustomerCard(cardID: number) {
    return this.http.delete(
      `${environment.BASE_URL}/payment-api/customer/${this.emailId}/card/${cardID}/delete`
    );
  }

  getCardsOfCustomer(cardType: string) {
    return this.http.get(
      `${environment.BASE_URL}/payment-api/customer/${this.emailId}/card-type/${cardType}`
    );
  }

  payForOrder(transactionDTO: TransactionDTO) {
    return this.http.post(
      `${environment.BASE_URL}/payment-api/customer/${this.emailId}/pay-order`,
      transactionDTO
    );
  }
}
