package com.astontech.hr.services.impl;

import com.astontech.hr.domain.Element;
import com.astontech.hr.domain.ElementType;
import com.astontech.hr.repositories.ElementTypeRepository;
import com.astontech.hr.services.ElementTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElementTypeServiceImpl implements ElementTypeService {

    @Autowired
    private ElementTypeRepository elementTypeRepository;

    @Override
    public ElementType findByElementTypeName(String elementTypeName) {
        return elementTypeRepository.findByElementTypeName(elementTypeName);
    }

    @Override
    public Iterable<ElementType> listAllElementTypes() {
        return elementTypeRepository.findAll();
    }

    @Override
    public ElementType getElementTypeById(int id) {
        return elementTypeRepository.findOne(id);
    }

    @Override
    public ElementType saveElementType(ElementType elementType) {
        return elementTypeRepository.save(elementType);
    }

    @Override
    public Iterable<ElementType> saveElementType(Iterable<ElementType> elementTypeIterable) {
        return elementTypeRepository.save(elementTypeIterable);
    }

    @Override
    public void deleteElementType(Integer id) {
        elementTypeRepository.delete(id);
    }

    @Override
    public List<ElementType> findAllByElementTypeNameContains(String substring) {
        return elementTypeRepository.findAllByElementTypeNameContains(substring);
    }

    @Override
    public List<ElementType> findAllByElementTypeNameContainsIgnoreCase(String substring) {
        return elementTypeRepository.findAllByElementTypeNameContainsIgnoreCase(substring);
    }

    @Override
    public List<Element> findAllByElementListElementNameContains(String substring) {
        return elementTypeRepository.findAllByElementListElementNameContains(substring);
    }

    @Override
    public List<Element> findByElementListElementNameContainsIgnoreCase(String substring) {
        return elementTypeRepository.findByElementListElementNameContainsIgnoreCase(substring);
    }

    @Override
    public List<ElementType> findAllByElementTypeNameContainsOrderByElementTypeNameAsc(String substring) {
        return elementTypeRepository.findAllByElementTypeNameContainsOrderByElementTypeNameAsc(substring);
    }
}
