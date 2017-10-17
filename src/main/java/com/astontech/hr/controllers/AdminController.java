package com.astontech.hr.controllers;

import com.astontech.hr.domain.*;
import com.astontech.hr.domain.VO.ElementVO;
import com.astontech.hr.domain.VO.VehicleModelVO;
import com.astontech.hr.domain.VO.VehicleVO;
import com.astontech.hr.services.ElementTypeService;
import com.astontech.hr.services.VehicleMakeService;
import com.astontech.hr.services.VehicleService;
import com.astontech.hr.services.impl.VehicleModelService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sun.awt.X11.XException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private ElementTypeService elementTypeService;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private VehicleModelService vehicleModelService;

    @Autowired
    private VehicleMakeService vehicleMakeService;

    private Logger log = Logger.getLogger(AdminController.class);

    private boolean[] alertArray = {false,false,false};

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminHome() {

        return "admin/adminHome";
    }

    //region ELEMENT PAGES
    //region ELEMENT ADD (CREATE)
    @RequestMapping(value = "/admin/element/add", method = RequestMethod.GET)
    public String adminElementGet(Model model) {
        model.addAttribute("elementVO", new ElementVO());
        model.addAttribute("warningAlert", "visible");
        return "admin/element/element_add";
    }

    @RequestMapping(value = "/admin/element/add", method = RequestMethod.POST)
    public String adminElementPost(ElementVO elementVO, Model model) {
        elementVO.splitNewElementsIntoArray();
        logElementVO(elementVO);

        saveElementTypesAndElementsFromVO(elementVO);
        boolean success = true;
        if(success)
            model.addAttribute("successAlert", "visible");
        else
            model.addAttribute("errorAlert","visible");

        model.addAttribute("elementVO", new ElementVO());
        return "/admin/element/element_add";
    }
    //endregion

    //region ELEMENT LIST (RETRIEVE)
    @RequestMapping(value = "/admin/element/list", method = RequestMethod.GET)
        public String adminElementList(Model model) {
        model.addAttribute("elementTypeList", elementTypeService.listAllElementTypes());

        return "/admin/element/element_list";
    }
    //endregion

    //region ELEMENT EDIT (UPDATE)
    //set up elementType to be updated
    @RequestMapping(value = "/admin/element/edit/{id}", method = RequestMethod.GET)
    public String elementTypeEdit(@PathVariable int id, Model model) {
        ElementType elementType = elementTypeService.getElementTypeById(id);

        model.addAttribute("elementType", elementType);
        return "admin/element/element_edit";
    }

    //update elementList elements
    @RequestMapping(value = "/admin/element/update", method = RequestMethod.POST)
    public String elementTypeUpdate(ElementType elementType,
                                    Model model,
                                    @RequestParam("inputNewElement") String newElement) {

        //notes: if newElement (unbound text box) has a value, add it to the list
        if(!newElement.equals("")) {
            if(elementType.getElementList() == null) {
                List<Element> elementList = new ArrayList<>();
                elementList.add(new Element(newElement));
                elementType.setElementList(elementList);
            } else {
                elementType.getElementList().add(new Element(newElement));
            }
        }

        //notes: iterate through the list of elements, remove those with blank names
        for(int i=0;i<elementType.getElementList().size();i++) {
            if(elementType.getElementList().get(i).getElementName().equals(""))
                elementType.getElementList().remove(i);
        }

        elementTypeService.saveElementType(elementType);

        return "redirect:/admin/element/edit/" + elementType.getId();
    }
    //endregion

    //region ELEMENT DELETE (DELETE)
    @RequestMapping(value = "/admin/element/delete/{id}", method = RequestMethod.GET)
    public String elementTypeDelete(@PathVariable int id) {
        elementTypeService.deleteElementType(id);

        return "redirect:/admin/element/list";
    }
    //endregion
    //endregion

    //region VEHICLE PAGES
    //region VEHICLE ADD (CREATE)
    @RequestMapping(value = "/admin/vehicle/add", method = RequestMethod.GET)
    public String adminVehicleGet(Model model) {
        model.addAttribute("vehicleVO", new VehicleVO());
        model.addAttribute("warningAlert", "visible");

        model.addAttribute("vehicleMakeList", vehicleMakeService.listAllVehicleMakes());
        model.addAttribute("vehicleModelList", vehicleModelService.listAllVehicleModels());

        return "admin/vehicle/vehicle_add";
    }

    @RequestMapping(value = "/admin/vehicle/add", method = RequestMethod.POST)
    public String adminVehiclePost(VehicleVO vehicleVO, Model model) {

        if(vehicleVO.getNewVehicleMakeId() != 0 && vehicleVO.getNewVehicleModelId() != 0) {
            try {
                Integer.parseInt(vehicleVO.getNewYear());
                logVehicleVO(vehicleVO);
                saveVehicleFromVO(vehicleVO);
                model.addAttribute("successAlert", "visible");
            } catch (Exception ex) {
                model.addAttribute("errorAlert", "visible");
            }
        } else {
            model.addAttribute("errorAlert", "visible");
        }

        model.addAttribute("vehicleVO", new VehicleVO());

        model.addAttribute("vehicleMakeList", vehicleMakeService.listAllVehicleMakes());
        model.addAttribute("vehicleModelList", vehicleModelService.listAllVehicleModels());

        return "/admin/vehicle/vehicle_add";
    }
    //endregion

    //region VEHICLE LIST (RETRIEVE)
    @RequestMapping(value = "/admin/vehicle/list", method = RequestMethod.GET)
    public String adminVehicleList(Model model) {
        model.addAttribute("vehicleList", vehicleService.listAllVehicles());

        if(!alertArray[0] && !alertArray[1] && !alertArray[2])
            model.addAttribute("warningAlert", "visible");
        else if(alertArray[0]) {
            model.addAttribute("successAlert", "visible");
            alertArray[0] = false;
        }
        else if(alertArray[2]) {
            model.addAttribute("errorAlert", "visible");
            alertArray[2] = false;
        }
        return "/admin/vehicle/vehicle_list";
    }
    //endregion

    //region VEHICLE EDIT (UPDATE)
    //set up VEHICLE to be updated
    @RequestMapping(value = "/admin/vehicle/edit/{id}", method = RequestMethod.GET)
    public String vehicleEdit(@PathVariable int id, Model model) {
        Vehicle vehicle = vehicleService.getVehicleById(id);

        model.addAttribute("vehicle", vehicle);
        model.addAttribute("vehicleModelList", vehicleModelService.listAllVehicleModels());
        model.addAttribute("vehicleMakeList", vehicleMakeService.listAllVehicleMakes());
        if(!alertArray[0] && !alertArray[1] && !alertArray[2])
            model.addAttribute("warningAlert", "visible");
        else if(alertArray[0]) {
            model.addAttribute("successAlert", "visible");
            alertArray[0] = false;
        }
        else if(alertArray[2]) {
            model.addAttribute("errorAlert", "visible");
            alertArray[2] = false;
        }
        return "/admin/vehicle/vehicle_edit";
    }

    //update VEHICLE
    @RequestMapping(value = "/admin/vehicle/update", method = RequestMethod.POST)
    public String vehicleUpdate(Vehicle vehicle, Model model) throws Exception {

        int currVer = vehicleService.getVehicleById(vehicle.getId()).getVersion();

        //notes: validate fields
        if(vehicle.getVehicleModel() != null && vehicle.getYear() > 0 && vehicle.getVersion() >= currVer) {
            vehicleService.saveVehicle(vehicle);
            alertArray[0] = true;
        } else {
            alertArray[2] = true;
        }

        return "redirect:/admin/vehicle/edit/" + vehicle.getId();
    }
    //endregion

    //region VEHICLE DELETE (DELETE)
    @RequestMapping(value = "/admin/vehicle/delete/{id}", method = RequestMethod.GET)
    public String vehicleDelete(@PathVariable int id) {
        vehicleService.deleteVehicle(id);
        alertArray[0] = true;

        return "redirect:/admin/vehicle/list";
    }
    //endregion

    //endregion

    //region VEHICLE MODEL PAGES
    //region VEHICLE MODEL ADD (CREATE)
    @RequestMapping(value = "/admin/vehicleModel/add", method = RequestMethod.GET)
    public String adminVehicleModelGet(Model model) {
        model.addAttribute("vehicleModelVO", new VehicleModelVO());
        model.addAttribute("warningAlert", "visible");

        model.addAttribute("vehicleMakeList", vehicleMakeService.listAllVehicleMakes());

        return "admin/vehicleModel/vehicleModel_add";
    }

    @RequestMapping(value = "/admin/vehicleModel/add", method = RequestMethod.POST)
    public String adminVehicleModelPost(VehicleModelVO vehicleModelVO, Model model) {

        if(!vehicleModelVO.getNewVehicleModelName().equals("") && vehicleModelVO.getNewVehicleMakeId() != 0) {
            logVehicleModelVO(vehicleModelVO);
            saveVehicleModelFromVO(vehicleModelVO);
            model.addAttribute("successAlert", "visible");
        } else {
            model.addAttribute("errorAlert", "visible");
        }


        model.addAttribute("vehicleModelVO", new VehicleModelVO());

        model.addAttribute("vehicleMakeList", vehicleMakeService.listAllVehicleMakes());

        return "/admin/vehicleModel/vehicleModel_add";
    }
    //endregion

    //region VEHICLE MODEL LIST (RETRIEVE)
    @RequestMapping(value = "/admin/vehicleModel/list", method = RequestMethod.GET)
    public String adminVehicleModelList(Model model) {
        model.addAttribute("vehicleModelList", vehicleModelService.listAllVehicleModels());

        if(!alertArray[0] && !alertArray[1] && !alertArray[2])
            model.addAttribute("warningAlert", "visible");
        else if(alertArray[0]) {
            model.addAttribute("successAlert", "visible");
            alertArray[0] = false;
        }
        else if(alertArray[2]) {
            model.addAttribute("errorAlert", "visible");
            alertArray[2] = false;
        }

        return "/admin/vehicleModel/vehicleModel_list";
    }
    //endregion

    //region VEHICLE MODEL EDIT (UPDATE)
    //set up VEHICLE MODEL to be updated
    @RequestMapping(value = "/admin/vehicleModel/edit/{id}", method = RequestMethod.GET)
    public String vehicleModelEdit(@PathVariable int id, Model model) {
        VehicleModel vehicleModel = vehicleModelService.getVehicleModelById(id);

        model.addAttribute("vehicleModel", vehicleModel);
        model.addAttribute("vehicleModelList", vehicleModelService.listAllVehicleModels());
        model.addAttribute("vehicleMakeList", vehicleMakeService.listAllVehicleMakes());
        if(!alertArray[0] && !alertArray[1] && !alertArray[2])
            model.addAttribute("warningAlert", "visible");
        else if(alertArray[0]) {
            model.addAttribute("successAlert", "visible");
            alertArray[0] = false;
        }
        else if(alertArray[2]) {
            model.addAttribute("errorAlert", "visible");
            alertArray[2] = false;
        }
        return "/admin/vehicleModel/vehicleModel_edit";
    }

    //update VEHICLE MODEL
    @RequestMapping(value = "/admin/vehicleModel/update", method = RequestMethod.POST)
    public String vehicleModelUpdate(VehicleModel vehicleModel, Model model) throws Exception {

        int currVer = vehicleModelService.getVehicleModelById(vehicleModel.getId()).getVersion();

        //notes: validate fields
        if(vehicleModel.getVehicleMake() != null && vehicleModel.getVersion() >= currVer) {
            vehicleModelService.saveVehicleModel(vehicleModel);
            alertArray[0] = true;
        } else {
            alertArray[2] = true;
        }

        return "redirect:/admin/vehicleModel/edit/" + vehicleModel.getId();
    }
    //endregion

    //region VEHICLE MODEL DELETE (DELETE)
    @RequestMapping(value = "/admin/vehicleModel/delete/{id}", method = RequestMethod.GET)
    public String vehicleModelDelete(@PathVariable int id, Model model) {

        try {
            vehicleModelService.deleteVehicleModel(id);
            alertArray[0] = true;
        } catch(Exception ex) {
            log.info("Cannot delete Vehicle Model, SQL FK Constraint");
            alertArray[2] = true;
        }

        return "redirect:/admin/vehicleModel/list";
    }
    //endregion

    //endregion

    //region VEHICLE MAKE PAGES
    //region VEHICLE MAKE ADD (CREATE)
    @RequestMapping(value = "/admin/vehicleMake/add", method = RequestMethod.GET)
    public String adminVehicleMakeGet(Model model) {
        model.addAttribute("vehicleMake", new VehicleMake());
        model.addAttribute("warningAlert", "visible");

        return "admin/vehicleMake/vehicleMake_add";
    }

    @RequestMapping(value = "/admin/vehicleMake/add", method = RequestMethod.POST)
    public String adminVehicleMakePost(VehicleMake vehicleMake, Model model) {

        if(vehicleMake.getVehicleMakeName() != null) {
            logVehicleMake(vehicleMake);
            vehicleMakeService.saveVehicleMake(vehicleMake);
            model.addAttribute("successAlert", "visible");
        }
        else {
            model.addAttribute("errorAlert", "visible");
        }

        model.addAttribute("vehicleMake", new VehicleMake());

        return "/admin/vehicleMake/vehicleMake_add";
    }
    //endregion

    //region VEHICLE MAKE LIST (RETRIEVE)
    @RequestMapping(value = "/admin/vehicleMake/list", method = RequestMethod.GET)
    public String adminVehicleMakeList(Model model) {
        model.addAttribute("vehicleMakeList", vehicleMakeService.listAllVehicleMakes());
        if(!alertArray[0] && !alertArray[1] && !alertArray[2])
            model.addAttribute("warningAlert", "visible");
        else if(alertArray[0]) {
            model.addAttribute("successAlert", "visible");
            alertArray[0] = false;
        }
        else if(alertArray[2]) {
            model.addAttribute("errorAlert", "visible");
            alertArray[2] = false;
        }
        return "/admin/vehicleMake/vehicleMake_list";
    }
    //endregion

    //region VEHICLE MAKE EDIT (UPDATE)
    //set up VEHICLE MAKE to be updated
    @RequestMapping(value = "/admin/vehicleMake/edit/{id}", method = RequestMethod.GET)
    public String vehicleMakeEdit(@PathVariable int id, Model model) {
        VehicleMake vehicleMake = vehicleMakeService.getVehicleMakeById(id);

        model.addAttribute("vehicleMake", vehicleMake);
        model.addAttribute("warningAlert", "visible");
        if(!alertArray[0] && !alertArray[1] && !alertArray[2])
            model.addAttribute("warningAlert", "visible");
        else if(alertArray[0]) {
            model.addAttribute("successAlert", "visible");
            alertArray[0] = false;
        }
        else if(alertArray[2]) {
            model.addAttribute("errorAlert", "visible");
            alertArray[2] = false;
        }
        return "/admin/vehicleMake/vehicleMake_edit";
    }

    //update VEHICLE MAKE
    @RequestMapping(value = "/admin/vehicleMake/update", method = RequestMethod.POST)
    public String vehicleMakeUpdate(VehicleMake vehicleMake, Model model) throws Exception {

        int currVer = vehicleMakeService.getVehicleMakeById(vehicleMake.getId()).getVersion();

        //notes: validate fields
        if(vehicleMake.getVersion() >= currVer) {
            vehicleMakeService.saveVehicleMake(vehicleMake);
            alertArray[0] = true;
        } else {
            alertArray[2] = true;
        }

        return "redirect:/admin/vehicleMake/edit/" + vehicleMake.getId();
    }
    //endregion

    //region VEHICLE MAKE DELETE (DELETE)
    @RequestMapping(value = "/admin/vehicleMake/delete/{id}", method = RequestMethod.GET)
    public String vehicleMakeDelete(@PathVariable int id, Model model) {

        try {
            vehicleMakeService.deleteVehicleMake(id);
            alertArray[0] = true;
        } catch(Exception ex) {
            log.info("Cannot delete Vehicle Make, SQL FK Constraint");
            alertArray[2] = true;
        }

        return "redirect:/admin/vehicleMake/list";
    }
    //endregion

    //endregion

    //region AdminController HELPER METHODS
    //region ELEMENT HELPER METHODS
    private void saveElementTypesAndElementsFromVO(ElementVO elementVO) {
        List<Element> newElementList = new ArrayList<>();
        for(String str: elementVO.getNewElementArray()) {
            newElementList.add(new Element(str));
        }
        ElementType newElementType = new ElementType(elementVO.getNewElementType());
        newElementType.setElementList(newElementList);

        elementTypeService.saveElementType(newElementType);
    }

    private void logElementVO(ElementVO elementVO) {
        log.info("New Element Type: " + elementVO.getNewElementType());
        for(String s:elementVO.getNewElementArray())
            log.info("New Element: " + s);
    }
    //endregion

    //region VEHICLE HELPER METHODS
    private void saveVehicleFromVO(VehicleVO vehicleVO) {
        Vehicle vehicle = new Vehicle(Integer.parseInt(vehicleVO.getNewYear()), vehicleVO.getNewLicensePlate(), vehicleVO.getNewVin(),
                vehicleVO.getNewColor(), vehicleModelService.getVehicleModelById(vehicleVO.getNewVehicleModelId()));

        vehicleService.saveVehicle(vehicle);
    }

    private void logVehicleVO(VehicleVO vehicleVO) {
        log.info("New Vehicle Year: " + vehicleVO.getNewYear());
        log.info("New Vehicle License Plate: " + vehicleVO.getNewLicensePlate());
        log.info("New Vehicle VIN: " + vehicleVO.getNewVin());
        log.info("New Vehicle Color: " + vehicleVO.getNewColor());
        log.info("New Vehicle Vehicle Model: "+vehicleModelService.getVehicleModelById(vehicleVO.getNewVehicleModelId()).getVehicleModelName());
        log.info("New Vehicle Vehicle Make: "+vehicleMakeService.getVehicleMakeById(vehicleVO.getNewVehicleMakeId()).getVehicleMakeName()+" (not used)");
    }
    //endregion

    //region VEHICLE MODEL HELPER METHODS
    private void logVehicleModelVO(VehicleModelVO vehicleModelVO) {
        log.info("New Vehicle Model Vehicle Model Name: "+vehicleModelVO.getNewVehicleModelName());
        log.info("New Vehicle Model Vehicle Make: "+
                vehicleMakeService.getVehicleMakeById(vehicleModelVO.getNewVehicleMakeId()).getVehicleMakeName());
    }

    private void saveVehicleModelFromVO(VehicleModelVO vehicleModelVO) {
        System.out.println("vehicleMakeId: "+vehicleModelVO.getNewVehicleMakeId());
        VehicleModel vehicleModel = new VehicleModel(vehicleModelVO.getNewVehicleModelName(),
                vehicleMakeService.getVehicleMakeById(vehicleModelVO.getNewVehicleMakeId()));

        vehicleModelService.saveVehicleModel(vehicleModel);
    }
    //endregion

    //region VEHICLE MAKE HELPER METHODS
    private void logVehicleMake(VehicleMake vehicleMake) {
        log.info("New VehicleMake VehicleMakeName: "+ vehicleMake.getVehicleMakeName());
    }
    //endregion
    //endregion
}
