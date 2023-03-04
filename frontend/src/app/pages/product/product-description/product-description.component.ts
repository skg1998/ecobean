import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { ProductService } from 'src/app/core/sevices/product/product.service';

@Component({
  selector: 'app-product-description',
  templateUrl: './product-description.component.html',
  styleUrls: ['./product-description.component.css'],
})
export class ProductDescriptionComponent implements OnInit {
  productData!: any;

  constructor(
    private productService: ProductService,
    private router: Router,
    private toastr: ToastrService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    let productId = this.route.paramMap.subscribe((params) => params.get('id'));
    this.getProductById(productId);
  }

  getProductById(productId: any) {
    this.productService.getProductById(productId).subscribe(
      (data) => {
        this.productData = data;
        this.toastr.success('Get All Product', 'Product Fetch');
      },
      (error) => {
        this.toastr.error('Something went wrong', 'Product Fetch');
      }
    );
  }

  //Add to cart
  //Add to wish list
}
