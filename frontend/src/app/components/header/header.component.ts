import { Component, OnInit, HostListener, Inject } from '@angular/core';
import {
  trigger,
  state,
  transition,
  style,
  animate,
} from '@angular/animations';
import { DOCUMENT } from '@angular/common';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
  animations: [
    trigger('fade', [
      state('void', style({ opacity: 0 })),
      transition(':enter', [animate(300)]),
      transition(':leave', [animate(500)]),
    ]),
  ],
})
export class HeaderComponent implements OnInit {
  constructor() {}

  ngOnInit() {}

  @HostListener('window:scroll', ['$event'])
  onWindowScroll() {
    if (window.pageYOffset > 100) {
      let element = document.getElementById('sticky-header');
      if (element !== null) {
        element.classList.add('sticky');
      }
    } else {
      let element = document.getElementById('sticky-header');
      if (element !== null) {
        element.classList.remove('sticky');
      }
    }
  }
}
