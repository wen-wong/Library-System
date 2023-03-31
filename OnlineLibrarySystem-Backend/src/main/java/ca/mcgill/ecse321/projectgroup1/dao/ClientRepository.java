package ca.mcgill.ecse321.projectgroup1.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.projectgroup1.model.Client;

/**
 * The ClientRepository class extends the CrudRepository Class and contains all
 * the query methods for the Client class
 * 
 * Author: Emilia Solaberrieta
 */

public interface ClientRepository extends CrudRepository<Client, Integer> {
    // verifies if the client exists
    Boolean existsById(int id);

    // find client by client Id
    Client findById(int id);
}
