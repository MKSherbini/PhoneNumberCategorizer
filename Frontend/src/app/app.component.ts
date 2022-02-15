import { Component, OnInit } from '@angular/core';
import { CustomerAppControllerService, CustomerDto } from './apis';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent implements OnInit {

  customers: CustomerDto[] = [];

  constructor(private customerService: CustomerAppControllerService) { }

  ngOnInit(): void {
    this.customerService.findAllCustomers().subscribe(ret =>
      this.customers = ret
    )
  }

}
