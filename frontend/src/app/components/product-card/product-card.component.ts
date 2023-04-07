import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';
import { ProductDTO } from 'src/app/core/interfaces/Product';

@Component({
  selector: 'app-product-card',
  templateUrl: './product-card.component.html',
  styleUrls: ['./product-card.component.css'],
})
export class ProductCardComponent {
  @Input('product') product!: ProductDTO;

  constructor(private router: Router) {}

  addWishlist(id: any) {
    console.log('add to wishlist');
  }

  productDetail(id: any) {
    this.router.navigate([`product/${id}`]);
  }

  addToCart(id: any) {
    console.log('add to cart');
  }
}
