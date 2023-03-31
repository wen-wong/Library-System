package ca.mcgill.ecse321.projectgroup1.model;

// Import JPA annotation tags
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * Employee - This class is used to represent a librarian in the online library
 * system. This is class inherits all the attributes and associations of the
 * Account and Employee.
 * 
 * @param TypeOfEmployee - enum for the type of employee
 * 
 * @author Chloe Nasrallah
 * @author Wen Hin Brandon Wong (Reviewer)
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Employee extends Account {

    // The enumeration class of TypeOfEmployee
    public enum TypeOfEmployee {
        Librarian, HeadLibrarian
    }

    // The typeOfEmployee attribute of Librarian class indicates if the employee is
    // a librarian or a head librarian
    private TypeOfEmployee typeOfEmployee;

    // The setter method for the typeOfEmployee attribute that takes a
    // TypeOfEmployee value parameter
    public void setTypeOfEmployee(TypeOfEmployee typeOfEmployee) {
        this.typeOfEmployee = typeOfEmployee;
    }

    // The getter method for the typeOfEmployee attribute that returns a
    // TypeOfEmployee
    public TypeOfEmployee getTypeOfEmployee() {
        return this.typeOfEmployee;
    }
}
