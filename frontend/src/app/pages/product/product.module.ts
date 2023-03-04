import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProductsComponent } from './products/products.component';
import { ProductDescriptionComponent } from './product-description/product-description.component';
import { ComponentsModule } from 'src/app/components/components.module';

@NgModule({
  declarations: [ProductsComponent, ProductDescriptionComponent],
  imports: [CommonModule],
})
export class ProductModule {}
