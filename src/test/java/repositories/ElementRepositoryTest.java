package repositories;

import com.astontech.hr.configuration.RepositoryConfiguration;
import com.astontech.hr.domain.Element;
import com.astontech.hr.repositories.ElementRepository;
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
public class ElementRepositoryTest {

    @Autowired
    private ElementRepository elementRepository;

    @Test
    public void testSaveElement() {
        //setup element
        Element element = new Element();
        element.setElementName("Phone");

        //save element, verify it has an ID after the save
        assertNull(element.getId());
        elementRepository.save(element);
        assertNotNull(element.getId());

        //fetch
        Element fetchedElement = elementRepository.findOne(element.getId());
        assertNotNull(fetchedElement);
        assertEquals(element.getId(),fetchedElement.getId());

        //update
        fetchedElement.setElementName("Email");
        elementRepository.save(fetchedElement);

        Element updatedElement = elementRepository.findOne(fetchedElement.getId());
        assertEquals(updatedElement.getElementName(), "Email");
    }

    @Test
    public void testSaveElementList() {
        List<Element> elementList = new ArrayList<>();
        elementList.add(new Element("Phone"));
        elementList.add(new Element("Email"));
        elementList.add(new Element("Laptop"));
        elementList.add(new Element("PayRate"));

        elementRepository.save(elementList);

        Iterable<Element> fetchedElementList = elementRepository.findAll();

        int count = 0;
        for(Element element: fetchedElementList)
            count++;
//        assertEquals(5, count);
    }

    @Test
    public void testFindByName() {
        Element element = new Element("findTestSingle");
        elementRepository.save(element);

        Element foundByNameElement = elementRepository.findByElementName("findTestSingle");

        assertEquals(element.getId(), foundByNameElement.getId());
    }

    @Test
    public void testFindAllByName() {
        Element element = new Element("findTest");
        elementRepository.save(element);
        Element element2 = new Element("findTest");
        elementRepository.save(element2);
        Element element3 = new Element("findTest");
        elementRepository.save(element3);

        List<Element> foundAllByNameElement = elementRepository.findAllByElementName("findTest");

        assertEquals(3, foundAllByNameElement.size());
    }

    @Test
    public void testFindAllByNameIgnoreCase() {
        Element element = new Element("findTestIC");
        elementRepository.save(element);
        Element element2 = new Element("findTestic");
        elementRepository.save(element2);
        Element element3 = new Element("findTestIc");
        elementRepository.save(element3);

        List<Element> foundAllByNameICElement = elementRepository.findAllByElementNameIgnoreCase("findTestIC");

        assertEquals(3, foundAllByNameICElement.size());
    }

    @Test
    public void testFindAllByNameContains() {
        Element element = new Element("findTestContainsQWERTY");
        elementRepository.save(element);
        Element element2 = new Element("findTestContainsQWERTY");
        elementRepository.save(element2);
        Element element3 = new Element("findTestContainsQWERTY");
        elementRepository.save(element3);

        List<Element> foundAllByTypeList = elementRepository.findAllByElementNameContains("QWERTY");

        for(Element et : foundAllByTypeList)
            System.out.println("Element: "+et.getId()+" "+et.getElementName());
        assertEquals(3, foundAllByTypeList.size());
    }

    @Test
    public void testFindAllByNameContainsIC() {
        Element Element = new Element("findTestJANKY");
        elementRepository.save(Element);
        Element Element2 = new Element("findTestJaNky");
        elementRepository.save(Element2);
        Element Element3 = new Element("findTestJaNkY");
        elementRepository.save(Element3);
        Element Element4 = new Element("findTestjAnKy");
        elementRepository.save(Element4);

        List<Element> foundAllByTypeContainsICList = elementRepository.findAllByElementNameContainsIgnoreCase("JANKY");

        for(Element et : foundAllByTypeContainsICList)
            System.out.println("Element: "+et.getId()+" "+et.getElementName());
        assertEquals(4, foundAllByTypeContainsICList.size());
    }

}
