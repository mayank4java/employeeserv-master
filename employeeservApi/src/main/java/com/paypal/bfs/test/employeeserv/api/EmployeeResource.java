package com.paypal.bfs.test.employeeserv.api;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.paypal.bfs.test.employeeserv.api.model.Employee;

/**
 * Interface for employee resource operations.
 */
public interface EmployeeResource {

	/**
	 * Retrieves the {@link Employee} resource by id.
	 *
	 * @param id
	 *            employee id.
	 * @return {@link Employee} resource.
	 */
	@RequestMapping("/v1/bfs/employees/{id}")
	ResponseEntity<Employee> employeeGetById(@Size(min = 1) @PathVariable("id") String id) throws Exception;

	// ----------------------------------------------------------
	// TODO - add a new operation for creating employee resource.
	// ----------------------------------------------------------

	@RequestMapping("/v1/bfs/retrieve")
	ResponseEntity<List<Employee>> retrieve() throws Exception;

	@PostMapping("/v1/bfs/create")
	ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee) throws Exception;
	
	@RequestMapping("/v1/bfs/healthCheck/{name}")
	ResponseEntity<String> healthCheck(@PathVariable("name") String name) throws Exception;
}
