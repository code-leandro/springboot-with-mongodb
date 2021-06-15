package com.leandrodev.api.controller;

import com.leandrodev.api.model.Customer;
import com.leandrodev.api.dto.CustomerDTO;
import com.leandrodev.api.service.CustomerService;
import com.leandrodev.api.dto.CustomerDTO;
import com.leandrodev.api.model.Customer;
import com.leandrodev.api.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/customers")
public class CustomerController {
	
	CustomerService service;

	@GetMapping
	@Operation(summary = "List customers", description = "List all registered customers")
	public ResponseEntity<List<Customer>> listAll(){
		return ResponseEntity.ok(service.listAll());
	}
	
	@GetMapping("/{id}")
	@Operation(summary = "Get one customer", description = "Get a specific customer using its identifier (id) ")
	public ResponseEntity<Customer> findById(@PathVariable String id){
		return ResponseEntity.ok(service.findByUuidOrThrowException(id));
	}
	
	@PostMapping
	@Operation(summary = "Save a customer", description = "Write a customer (store) ")
	public ResponseEntity<Customer> post(@RequestBody @Valid CustomerDTO dto) {
		return new ResponseEntity<>(service.save(dto), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Successful operation. No content is returned."),
			@ApiResponse(responseCode = "404", description = "Customer was not found in the database.")
	})
	@Operation(summary = "Delete a customer", description = "Delete a specific customer using its identifier (id) ")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	@Operation(summary = "Update a customer", description = "Update a customer. Send the identifier(id) in the url and the attributes on the entity of that sended on body ")
	public ResponseEntity<Customer> put(@PathVariable String id, @RequestBody CustomerDTO dto) {
		return ResponseEntity.ok(service.replace(id, dto));
	}

//	@GetMapping("/search")
//	public ResponseEntity<List<Customer>> search(@RequestParam(required = false) String q,
//			@RequestParam(required = false, name = "min_price") BigDecimal minPrice,
//			@RequestParam(required = false, name = "max_price") BigDecimal maxPrice)
//	{
//		return ResponseEntity.ok(service.search(q, minPrice, maxPrice));
//	}
}
