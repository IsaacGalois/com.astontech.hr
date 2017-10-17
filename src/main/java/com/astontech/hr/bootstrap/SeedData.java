package com.astontech.hr.bootstrap;

import com.astontech.hr.domain.*;
import com.astontech.hr.services.*;
import com.astontech.hr.services.impl.VehicleModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SeedData implements ApplicationListener<ContextRefreshedEvent>{

    @Autowired
    private ElementService elementService;

    @Autowired
    private ElementTypeService elementTypeService;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private VehicleModelService vehicleModelService;

    @Autowired
    private VehicleMakeService vehicleMakeService;

    @Autowired
    private EmployeeService employeeService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
//        generateElementAndElementTypes();
//        generateVehicleMakes();

//        generateEmployees();
    }

    private void generateElementAndElementTypes() {

        ElementType laptopType = new ElementType("Laptop");

        List<Element> elementList = new ArrayList<>();
        elementList.add(new Element("Acer"));
        elementList.add(new Element("Dell"));
        elementList.add(new Element("Samsung"));
        elementList.add(new Element("Apple"));
        elementList.add(new Element("Asus"));

        laptopType.setElementList(elementList);

        elementTypeService.saveElementType(laptopType);

        ElementType phoneType = new ElementType("Phone");

        List<Element> elementList2 = new ArrayList<>();
        elementList2.add(new Element("Mobile"));
        elementList2.add(new Element("Home"));
        elementList2.add(new Element("Work"));

        phoneType.setElementList(elementList2);

        elementTypeService.saveElementType(phoneType);

        ElementType emailType = new ElementType("Email");

        List<Element> elementList3 = new ArrayList<>();
        elementList3.add(new Element("Work"));
        elementList3.add(new Element("Personal"));
        elementList3.add(new Element("Delegated"));

        emailType.setElementList(elementList3);

        elementTypeService.saveElementType(emailType);

        ElementType stateType = new ElementType("State");

        List<Element> elementList4 = new ArrayList<>();
        elementList4.add(new Element("Minnesota"));
        elementList4.add(new Element("California"));
        elementList4.add(new Element("New York"));

        stateType.setElementList(elementList4);

        elementTypeService.saveElementType(stateType);
    }

    private void generateVehicleMakes() {

        List<VehicleMake> vmList = new ArrayList<>();

        vmList.add(new VehicleMake("Ford"));
        vmList.add(new VehicleMake("Volkswagen"));
        vmList.add(new VehicleMake("Toyota"));

        vehicleMakeService.saveVehicleMake(vmList);

        generateVehicleModels();
    }

    private void generateVehicleModels() {


        List<VehicleModel> vmodList = new ArrayList<>();

        vmodList.add(new VehicleModel("F-150", vehicleMakeService.getVehicleMakeById(1)));      //1
        vmodList.add(new VehicleModel("Mustang", vehicleMakeService.getVehicleMakeById(1)));    //2
        vmodList.add(new VehicleModel("Passat", vehicleMakeService.getVehicleMakeById(2)));     //3
        vmodList.add(new VehicleModel("Jetta", vehicleMakeService.getVehicleMakeById(2)));      //4
        vmodList.add(new VehicleModel("Corolla", vehicleMakeService.getVehicleMakeById(3)));    //5
        vmodList.add(new VehicleModel("Tundra", vehicleMakeService.getVehicleMakeById(3)));     //6

        vehicleModelService.saveVehicleModel(vmodList);

        generateVehicles();
    }

    private void generateVehicles() {

        List<Vehicle> vList = new ArrayList<>();

        vList.add(new Vehicle(1970,"OWW0440","1B3BD1FB5BN536879","Red",vehicleModelService.getVehicleModelById(1)));    //1
        vList.add(new Vehicle(1988,"FUN4540","2G1WB55K281187682","Green",vehicleModelService.getVehicleModelById(2)));  //2
        vList.add(new Vehicle(2004,"JAK3698","JKALX8A1X5DA03192","Silver",vehicleModelService.getVehicleModelById(3))); //3
        vList.add(new Vehicle(1987,"HEY6589","1FMZU73W34UA59428","Blue",vehicleModelService.getVehicleModelById(4)));   //4
        vList.add(new Vehicle(2012,"IMA7521","3HSCNAPTX6N396451","Black",vehicleModelService.getVehicleModelById(5)));  //5
        vList.add(new Vehicle(2016,"ROK4589","1GCDT19W4YK254719","White",vehicleModelService.getVehicleModelById(6)));  //6

        vehicleService.saveVehicle(vList);
    }

    private void generateEmployees() {

        Employee employee1 = new Employee();
        employee1.setFirstName("Bipin");
        employee1.setLastName("Butala");
        employee1.setBackground("Java Developer");
        employeeService.saveEmployee(employee1);

        Employee employee2 = new Employee();
        employee2.setFirstName("Tony");
        employee2.setLastName("Morano");
        employee2.setBackground("Java Developer as well");
        employeeService.saveEmployee(employee2);

        Employee employee3 = new Employee();
        employee3.setFirstName("Dan");
        employee3.setLastName("Simmer");
        employee3.setBackground("DotNet Developer");
        employeeService.saveEmployee(employee3);

        Employee employee4 = new Employee();
        employee4.setFirstName("Sean");
        employee4.setLastName("Nilsen");
        employee4.setBackground("Contact Center Engineer");
        employeeService.saveEmployee(employee4);
    }
}
