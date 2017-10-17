package com.astontech.hr.repositories;

import com.astontech.hr.domain.Element;
import com.astontech.hr.domain.ElementType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ElementTypeRepository extends CrudRepository<ElementType, Integer>{

    ElementType findByElementTypeName(String elementTypeName);

    List<ElementType> findAllByElementTypeNameContains(String substring);

    List<ElementType> findAllByElementTypeNameContainsIgnoreCase(String substring);

    List<Element> findAllByElementListElementNameContains(String substring);

    List<Element> findByElementListElementNameContainsIgnoreCase(String substring);

    List<ElementType> findAllByElementTypeNameContainsOrderByElementTypeNameAsc(String substring);

    @Query(value = "SELECT * FROM " +
            "ELEMENT_TYPE ET WHERE " +
            "ET.ENTITY_TYPE_ID = :etID", nativeQuery = true)
    List<ElementType> findByEtIdNative(@Param("etID") int etID);

}
