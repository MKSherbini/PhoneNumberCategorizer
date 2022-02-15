import { Component, OnInit } from '@angular/core';
import { CustomerAppControllerService, CustomerDto } from './apis';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent implements OnInit {
  phoneStates: string[] = ["Show All", "Valid only", "Invalid only"];
  customers: CustomerDto[] = [];

  phoneStateFilter: string = this.phoneStates[0];
  countryFilter: string = "";

  constructor(private customerService: CustomerAppControllerService) { }

  ngOnInit(): void {
    this.submitFetchRequest();
  }

  submitFetchRequest(): void {
    this.customerService.findAllCustomers(
      this.countryFilter == "" ? undefined : this.countryFilter,
      this.getPhoneStateAsBoolean(),
      undefined, undefined).subscribe(ret => {
        let blob: Blob = ret as any as Blob;
        blob.text().then(data => {
          this.customers = JSON.parse(data);
        })
      })
  }

  setPhoneState(phoneState: string): void {
    this.phoneStateFilter = phoneState;
  }
  getPhoneStateAsBoolean(): boolean | undefined {
    let idx = this.phoneStates.findIndex((value) => this.phoneStateFilter == value);
    switch (idx) {
      case 1:
        return true;
      case 2:
        return false;
      default:
        return undefined;
    }
  }

}
