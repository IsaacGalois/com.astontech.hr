package com.astontech.hr.services;

import com.astontech.hr.domain.Element;
import com.astontech.hr.domain.ElementType;

import java.util.List;

public interface ElementTypeService {

    ElementType findByElementTypeName(String elementTypeName);

    Iterable<ElementType> listAllElementTypes();

    ElementType getElementTypeById(int id);

    ElementType saveElementType(ElementType elementType);

    Iterable<ElementType> saveElementType(Iterable<ElementType> elementTypeIterable);

    void deleteElementType(Integer id);


    //custom methods
    List<ElementType> findAllByElementTypeNameContains(String substring);

    List<ElementType> findAllByElementTypeNameContainsIgnoreCase(String substring);

    List<Element> findAllByElementListElementNameContains(String substring);

    List<Element> findByElementListElementNameContainsIgnoreCase(String substring);

    List<ElementType> findAllByElementTypeNameContainsOrderByElementTypeNameAsc(String substring);
}
