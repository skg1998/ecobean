import { TransactionStatus } from '../enum/TransactionStatus';

export class CardDTO {
  cardId!: number;
  cardType!: string;
  cardNumber!: string;
  nameOnCard!: string;
  hashCvv!: string;
  cvv!: number;
  expiryDate!: string;
  customerEmailId!: string;
}

export class OrderDTO {
  orderId!: number;
  customerEmailId!: string;
  dateOfOrder!: Date;
  totalPrice!: number;
  discount!: number;
  orderStatus!: string;
  paymentThrough!: string;
}

export class TransactionDTO {
  transactionId!: number;
  order!: OrderDTO;
  card!: CardDTO;
  totalPrice!: number;
  transactionDate!: Date;
  transactionStatus!: TransactionStatus;
}
