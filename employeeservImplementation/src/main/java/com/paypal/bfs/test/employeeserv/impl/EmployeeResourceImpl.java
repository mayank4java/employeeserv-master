package com.paypal.bfs.test.employeeserv.impl;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.jsonschema.JsonSchema;
import com.paypal.bfs.test.employeeserv.api.EmployeeResource;
import com.paypal.bfs.test.employeeserv.api.model.Address;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.exception.EmployeeException;

/**
 * Implementation class for employee resource.
 */
@Validated
@RestController
public class EmployeeResourceImpl implements EmployeeResource {

	private List<Employee> cache = new ArrayList<>();

	@Override
	public ResponseEntity<Employee> employeeGetById(String id ) throws EmployeeException {

		if (id == null) {
			throw new EmployeeException("Search key not found");
		}

		Employee employee = new Employee();
		employee.setId(Integer.valueOf(id));
		employee.setFirstName("BFS");
		employee.setLastName("Developer");

		Address address = new Address();
		address.setLine1("line1");
		address.setZipCode("12345");
		address.setCity("City");
		address.setState("State");
		address.setCountry("Country");

		employee.setAddress(address);
		employee.setDateOfBirth("01/01/2020");

		return new ResponseEntity<>(employee, HttpStatus.OK);
	}

	@Cacheable("Employees")
	@Override
	public ResponseEntity<List<Employee>> retrieve() throws EmployeeException {

		
		if (cache == null) {
			throw new EmployeeException("Search key not found");
		}

		return new ResponseEntity<>(cache, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Employee> createEmployee(Employee employee) throws EmployeeException {

		if (employee == null) {
			throw new EmployeeException("Search key not found");
		}

		return new ResponseEntity<>(cache.add(employee) ? employee : new Employee(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> healthCheck(String name) {

		return new ResponseEntity<>("Hi " + name + ", Service is up!", HttpStatus.OK);
	}

}
