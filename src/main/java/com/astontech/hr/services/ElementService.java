package com.astontech.hr.services;

import com.astontech.hr.domain.Element;

import java.util.List;

public interface ElementService {

    Iterable<Element> listAllElements();

    Element getElementById(int id);

    Element saveElement(Element element);

    Iterable<Element> saveElement(Iterable<Element> elementIterable);

    void deleteElement(Integer id);


    //custom repository methods
    Element findByElementName(String elementName);

    List<Element> findAllByElementName(String elementName);

    List<Element> findAllByElementNameIgnoreCase(String elementName);

    List<Element> findAllByElementNameContains(String substring);

    List<Element> findAllByElementNameContainsIgnoreCase(String substring);
}
