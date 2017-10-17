package repositories;

import com.astontech.hr.configuration.RepositoryConfiguration;
import com.astontech.hr.domain.Employee;
import com.astontech.hr.repositories.EmployeeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {RepositoryConfiguration.class})
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void testSave(){
        Employee employee1 = new Employee();
        employee1.setFirstName("BipinTest");
        employee1.setLastName("ButalaTest");
        employee1.setBackground("Java Developer");

        assertNull(employee1.getId());
        employeeRepository.save(employee1);

        assertNotNull(employee1.getId());

        //fetch
        Employee fetchedEmployee = employeeRepository.findOne(employee1.getId());
        assertNotNull(fetchedEmployee);
        assertEquals(employee1.getId(), fetchedEmployee.getId());

        //update
        fetchedEmployee.setFirstName("Bipin");
        employeeRepository.save(fetchedEmployee);

        Employee fetchUpdatedEmployee = employeeRepository.findOne(fetchedEmployee.getId());
        assertEquals("Bipin", fetchUpdatedEmployee.getFirstName());

    }
}
