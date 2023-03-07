import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { ProductDTO } from 'src/app/core/interfaces/Product';
import { ProductService } from 'src/app/core/sevices/product/product.service';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css'],
})
export class ProductsComponent implements OnInit {
  productList!: any; //ProductDTO[];
  constructor(
    private productService: ProductService,
    private router: Router,
    private toastr: ToastrService
  ) {}

  ngOnInit(): void {
    this.getAllProduct();
  }

  getAllProduct() {
    this.productService.getAllProducts().subscribe(
      (data) => {
        this.productList = data;
        console.log('product', this.productList);
        this.toastr.success('Get All Product', 'Product Fetch');
      },
      (error) => {
        this.toastr.error('Something went wrong', 'Product Fetch');
      }
    );
  }

  productDetail(id: any) {
    this.router.navigate([`product/${id}`]);
  }

  //add to cart
  //add to wishlist
}
