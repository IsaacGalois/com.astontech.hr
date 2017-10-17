package com.astontech.hr.services.impl;

import com.astontech.hr.domain.Element;
import com.astontech.hr.repositories.ElementRepository;
import com.astontech.hr.services.ElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElementServiceImpl implements ElementService {

    @Autowired
    private ElementRepository elementRepository;

    @Override
    public Iterable<Element> listAllElements() {
        return elementRepository.findAll();
    }

    @Override
    public Element getElementById(int id) {
        return elementRepository.findOne(id);
    }

    @Override
    public Element saveElement(Element element) {
        return elementRepository.save(element);
    }

    @Override
    public Iterable<Element> saveElement(Iterable<Element> elementIterable) {
        return elementRepository.save(elementIterable);
    }

    @Override
    public void deleteElement(Integer id) {
        elementRepository.delete(id);
    }

    @Override
    public Element findByElementName(String elementName) {
        return elementRepository.findByElementName(elementName);
    }

    @Override
    public List<Element> findAllByElementName(String elementName) {
        return elementRepository.findAllByElementName(elementName);
    }

    @Override
    public List<Element> findAllByElementNameIgnoreCase(String elementName) {
        return elementRepository.findAllByElementNameIgnoreCase(elementName);
    }

    @Override
    public List<Element> findAllByElementNameContains(String substring) {
        return elementRepository.findAllByElementNameContains(substring);
    }

    @Override
    public List<Element> findAllByElementNameContainsIgnoreCase(String substring) {
        return elementRepository.findAllByElementNameContainsIgnoreCase(substring);
    }
}
