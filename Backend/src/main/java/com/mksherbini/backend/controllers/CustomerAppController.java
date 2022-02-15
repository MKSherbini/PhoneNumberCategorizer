package com.mksherbini.backend.controllers;

import com.mksherbini.backend.models.Page;
import com.mksherbini.backend.models.dto.CustomerDto;
import com.mksherbini.backend.services.CustomerFilterService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.mksherbini.backend.models.RequestResponse;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
public class CustomerAppController {
    private final CustomerFilterService customerFilterService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public RequestResponse<List<CustomerDto>> findAllCustomers(
            @RequestParam(required = false) String country,
            @RequestParam(required = false) Boolean state,
            @RequestParam(name = "page", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(name = "size", required = false, defaultValue = "10") int pageSize) {
        Link selfRel = linkTo(methodOn(CustomerAppController.class)
                .findAllCustomers(country, state, pageNumber, pageSize)).withSelfRel();
        final List<CustomerDto> customersByFilter = customerFilterService.getCustomersByFilter(country, state);
        var page = new Page(pageNumber, Math.min(pageSize, customersByFilter.size()));
        page.setTotalElements(customersByFilter.size());
        page.setTotalPages((page.getSize() - 1 + customersByFilter.size()) / page.getSize());

        final List<CustomerDto> paginatedCustomers = customerFilterService.paginateCustomers(customersByFilter, pageNumber, pageSize);

        var response = new RequestResponse<List<CustomerDto>>();
        response.setData(paginatedCustomers);
        response.getLinks().put("self", selfRel.getHref());
        response.setPage(page);
        return response;
    }
}
