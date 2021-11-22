package com.company.controller;

import com.company.dto.Department;
import com.company.dto.Employee;
import com.company.exception.EmployeeNotFoundException;
import com.company.dto.Location;
import com.company.service.DepartmentService;
import com.company.service.EmployeeService;
import com.company.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService service;
    @Autowired
    private LocationService locationService;
    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/employees")
    public String showEmployeeList(Model model) {
        return viewPage(model,1);
    }
    @RequestMapping("/page/{pageNum}")
    public String viewPage(Model model,
                           @PathVariable(name = "pageNum") int pageNum) {


        Page<Employee> page = service.listAll(pageNum);
        List<Employee> listEmployees = page.getContent();
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("listEmployee", listEmployees);
        return "employees";
    }

    @GetMapping("/employee/new")
    public String showNewForm(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("pageTitle", "Add New Employee");
        List<Department> departments = departmentService.getAll();
        model.addAttribute("department",departments);
        List<Location> locations = locationService.getall();
        model.addAttribute("location",locations);
        return "employee_form";
    }

    @PostMapping("/employee/save")
    public String saveEmployee(Employee employee, RedirectAttributes ra) {

        service.save(employee);
        ra.addFlashAttribute("message", "The Employee has been saved successfully");
        return "redirect:/employees";
    }

    @GetMapping("/employee/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        try {
            Employee employee = service.get(id);
            model.addAttribute("employee", employee);
            model.addAttribute("pageTitle", "Edit Employee (ID: " + id +")");
            List<Department> departments = departmentService.getAll();
            model.addAttribute("department",departments);
            List<Location> locations = locationService.getall();
            model.addAttribute("location",locations);
            return "employee_form";
        } catch (EmployeeNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/employees";
        }
    }

    @GetMapping("/employee/delete/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id, RedirectAttributes ra){
        try {
            service.delete(id);
            ra.addFlashAttribute("message", "The Employee ID "+ id + " has been deleted");
        } catch (EmployeeNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/employees";
    }
}
