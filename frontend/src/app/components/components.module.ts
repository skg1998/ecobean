import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { ProductCardComponent } from './product-card/product-card.component';

@NgModule({
  declarations: [HeaderComponent, FooterComponent, ProductCardComponent],
  imports: [CommonModule],
})
export class ComponentsModule {}
