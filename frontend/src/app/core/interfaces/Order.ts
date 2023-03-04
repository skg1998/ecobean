export class OrderDTO {
  orderId!: string;
  customerEmailId!: string;
  dateOfOrder!: Date;
  totalPrice!: number;
  orderStatus!: string;
  discount!: string;
  paymentThrough!: string;
  dateOfDelivery!: Date;
  deliveryAddress!: string;
}

export class OrderedProductDTO {
  orderedProductId!: string;
  product!: ProductDTO;
  quantity!: string;
}

export class ProductDTO {
  productId!: number;
  name!: string;
  description!: string;
  category!: string;
  brand!: string;
  price!: number;
  availableQuantity!: number;
}
