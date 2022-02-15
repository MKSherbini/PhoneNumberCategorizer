import { Component, OnInit } from '@angular/core';
import { CustomerAppControllerService, CustomerDto, Page } from './apis';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent implements OnInit {
  phoneStates: string[] = ["Show All", "Valid only", "Invalid only"];

  customers: CustomerDto[] = [];
  page: Page = { number: 0, size: 10, totalElements: 10, totalPages: 1 };
  pageButtonsVisible: number = 5;
  phoneStateFilter: string = this.phoneStates[0];
  countryFilter: string = "";


  constructor(private customerService: CustomerAppControllerService) { }

  ngOnInit(): void {
    this.submitFetchRequest();
  }

  submitFetchRequest(pageNumber?: number): void {
    this.customerService.findAllCustomers(
      this.countryFilter == "" ? undefined : this.countryFilter,
      this.getPhoneStateAsBoolean(),
      pageNumber,
      this.page.size
    ).subscribe(ret => {
      this.customers = ret.data as CustomerDto[];
      this.page = ret.page as Page;
    })
  }

  setPhoneState(phoneState: string): void {
    this.phoneStateFilter = phoneState;
  }

  getVisiblePages(): number[] {
    let pages: number[] = [];
    let offset: number = Math.floor(this.pageButtonsVisible / 2);
    let start: number = Math.max(0, this.page.number - offset);
    let end: number = Math.min(this.page.totalPages - 1, this.page.number + offset);

    for (let index = start; index <= end; index++) {
      pages.push(index);
    }

    return pages;
  }

  fetchPage(pageNumber: number): void {
    this.submitFetchRequest(pageNumber);
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
