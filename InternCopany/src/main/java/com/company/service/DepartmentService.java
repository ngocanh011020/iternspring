package com.company.service;

import com.company.exception.EmployeeNotFoundException;
import com.company.dto.Department;
import com.company.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository repo;
    public Page<Department> listAll(int pageNum) {
        int pageSize = 5;

        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);

        return repo.findAll(pageable);
    }

    public void save(Department department) {
        repo.save(department);
    }

    public Department get(Integer id) throws EmployeeNotFoundException {
        Optional<Department> result = repo.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new EmployeeNotFoundException("Could not find any department with ID" + id);
    }

    public void delete(Integer id) throws EmployeeNotFoundException {
        Long count = repo.countById(id);
        if (count == null || count == 0) {
            throw new EmployeeNotFoundException("Could not find any department with ID" + id);
        }
        repo.deleteById(id);

    }
    public List<Department> getAll(){
        return repo.findAll();
    }
}
