package com.leandrodev.api.service;

import com.leandrodev.api.dto.CustomerDTO;
import com.leandrodev.api.exceptions.NotFoundException;
import com.leandrodev.api.model.Customer;
import com.leandrodev.api.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import static com.leandrodev.api.util.StreamUtil.areAllNull;

@AllArgsConstructor
@Service
public class CustomerService {

    private CustomerRepository repository;
    ModelMapper modelMapper;

    public Customer findByUuidOrThrowException(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("NOT FOUND"));
    }

    @Transactional
    public Customer save(CustomerDTO dto){
        var customer = modelMapper.map(dto, Customer.class);
        return repository.save(customer);
    }

    public List<Customer> listAll() {
        return repository.findAll();
    }

    public void delete(String id){
        repository.delete(findByUuidOrThrowException(id));
    }

    public Customer replace(String id, CustomerDTO dto) {
        findByUuidOrThrowException(id);
        var customer = modelMapper.map(dto, Customer.class);
        customer.setId(id);
        return repository.save(customer);
    }

//    public List<Customer> search(String search, BigDecimal minPrice, BigDecimal maxPrice) {
//
//        if (areAllNull(search, minPrice, maxPrice))
//            return repository.findAll();
//
//        return repository.findAll(new SpecificationCustomerSearch(search, minPrice, maxPrice));
//    }
}
