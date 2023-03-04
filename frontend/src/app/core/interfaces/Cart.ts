export class CustomerCartDTO {
  cartId!: number;
  customerEmailId!: string;
  cartProducts!: CartProductDTO[];
}

export class CartProductDTO {
  cartProductId!: number;
  product!: ProductDTO;
  quantity!: number;
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
