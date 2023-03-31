package ca.mcgill.ecse321.projectgroup1.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.projectgroup1.model.Employee;

/**
 * EmployeeRepository - This class extends the CrudRepository and contains all
 * query methods related to the Employee class
 * 
 */

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

    // verifies if Employee instance exists
    Boolean existsById(int id);

    // find employee by ID
    Employee findById(int id);
}
