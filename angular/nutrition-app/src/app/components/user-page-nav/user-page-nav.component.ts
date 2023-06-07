import { Component } from '@angular/core';
import { Router } from '@angular/router';
@Component({
  selector: 'app-user-page-nav',
  templateUrl: './user-page-nav.component.html',
  styleUrls: ['./user-page-nav.component.css']
})
export class UserPageNavComponent {
  constructor(public router: Router){

  }
}
