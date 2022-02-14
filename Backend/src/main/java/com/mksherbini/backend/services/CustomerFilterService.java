package com.mksherbini.backend.services;

import com.mksherbini.backend.repos.CustomerJpaRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerFilterService {
    private final CustomerJpaRepo customerJpaRepo;

    public void getCustomersByFilter(String country, boolean state) {

    }


}
