import {
  NgModule,
  CUSTOM_ELEMENTS_SCHEMA,
  NO_ERRORS_SCHEMA,
} from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProductsComponent } from './products/products.component';
import { ProductDescriptionComponent } from './product-description/product-description.component';
import { ComponentsModule } from '../../components/components.module';
@NgModule({
  imports: [CommonModule, ComponentsModule],
  declarations: [ProductsComponent, ProductDescriptionComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA, NO_ERRORS_SCHEMA],
})
export class ProductModule {}
