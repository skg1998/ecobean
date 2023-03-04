import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  constructor(private http: HttpClient) {}

  getAllProducts() {
    return this.http.get(`${environment.BASE_URL}/product-api/products`);
  }

  getProductById(productId: string) {
    return this.http.get(
      `${environment.BASE_URL}/product-api/product/${productId}`
    );
  }

  reduceAvailableQuantity(quantity: string, productId: string) {
    return this.http.put(
      `${environment.BASE_URL}/product-api/update/${productId}`,
      quantity
    );
  }
}
