package repositories;

import com.astontech.hr.configuration.RepositoryConfiguration;
import com.astontech.hr.domain.Element;
import com.astontech.hr.domain.ElementType;
import com.astontech.hr.repositories.ElementTypeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {RepositoryConfiguration.class})
public class ElementTypeRepositoryTest {

    @Autowired
    private ElementTypeRepository elementTypeRepository;

    @Test
    public  void testFindByEntityTypeName() {
        ElementType elementType = new ElementType("findTestName");
        elementTypeRepository.save(elementType);

        ElementType test = elementTypeRepository.findByElementTypeName("findTestName");

        assertEquals(test.getId(),elementType.getId());
    }

    @Test
    public void testFindAllByNameContains() {
        ElementType elementType = new ElementType("findTestContainsQWERTY");
        elementTypeRepository.save(elementType);
        ElementType elementType2 = new ElementType("findTestContainsQWERTY");
        elementTypeRepository.save(elementType2);
        ElementType elementType3 = new ElementType("findTestContainsQWERTY");
        elementTypeRepository.save(elementType3);

        List<ElementType> foundAllByTypeNameList = elementTypeRepository.findAllByElementTypeNameContains("QWERTY");

        assertEquals(3, foundAllByTypeNameList.size());
    }

    @Test
    public void testFindAllByNameContainsIC() {
        ElementType elementType = new ElementType("findTestJANKY");
        elementTypeRepository.save(elementType);
        ElementType elementType2 = new ElementType("findTestJaNky");
        elementTypeRepository.save(elementType2);
        ElementType elementType3 = new ElementType("findTestJaNkY");
        elementTypeRepository.save(elementType3);
        ElementType elementType4 = new ElementType("findTestjAnKy");
        elementTypeRepository.save(elementType4);

        List<ElementType> foundAllByTypeNameContainsICList = elementTypeRepository.findAllByElementTypeNameContainsIgnoreCase("JANKY");

        assertEquals(4, foundAllByTypeNameContainsICList.size());
    }

    @Test
    public void testFindAllByElementListElementNameContains() {

        ElementType elementType = new ElementType();

        List<Element> elmList = new ArrayList<>();
        Element element = new Element("findTestContainsQWERTY");
        elmList.add(element);
        Element element2 = new Element("findTestContainsQWERTY");
        elmList.add(element2);
        Element element3 = new Element("findTestContainsQWERTY");
        elmList.add(element3);

        elementType.setElementList(elmList);
        elementTypeRepository.save(elementType);

        List<Element> elementList = elementTypeRepository.findAllByElementListElementNameContains("QWERTY");

        assertEquals(3,elementList.size());
    }

    @Test
    public void testFindByElementListElementNameContainsIgnoreCase() {
        ElementType elementType = new ElementType();

        List<Element> elmList = new ArrayList<>();
        Element element = new Element("findTestJaNkY");
        elmList.add(element);
        Element element2 = new Element("findTestjanKY");
        elmList.add(element2);
        Element element3 = new Element("findTestjAnKy");
        elmList.add(element3);

        elementType.setElementList(elmList);
        elementTypeRepository.save(elementType);

        int eId = element2.getId();
        List<Element> foundElements = elementTypeRepository.findByElementListElementNameContainsIgnoreCase("janky");

        assertEquals(3,foundElements.size());
    }

    @Test
    public void testFindAllByElementTypeNameOrderByElementTypeAsc() {
        ElementType elementType = new ElementType("brandNewA");
        elementTypeRepository.save(elementType);
        ElementType elementType2 = new ElementType("brandNewB");
        elementTypeRepository.save(elementType2);
        ElementType elementType3 = new ElementType("brandNewC");
        elementTypeRepository.save(elementType3);
        ElementType elementType4 = new ElementType("brandNewD");
        elementTypeRepository.save(elementType4);

        List<ElementType> foundAllByElementTypeNameOrderByElementTypeAsc = elementTypeRepository.findAllByElementTypeNameContainsOrderByElementTypeNameAsc("brandNew");

        assertEquals("brandNewA",foundAllByElementTypeNameOrderByElementTypeAsc.get(0).getElementTypeName());
        assertEquals(4, foundAllByElementTypeNameOrderByElementTypeAsc.size());
    }

    @Test
    public void testFindByEtIdNative() {
        ElementType elementType = new ElementType();

        List<Element> elmList = new ArrayList<>();
        Element element = new Element("findTestatQuery");
        elmList.add(element);
        Element element2 = new Element("findTestatQuery");
        elmList.add(element2);
        Element element3 = new Element("findTestatQuery");
        elmList.add(element3);

        elementType.setElementList(elmList);
        elementTypeRepository.save(elementType);

        List<ElementType> et = elementTypeRepository.findByEtIdNative(1);

        assertEquals(et.get(0).getElementList().size(),3);

    }
}
