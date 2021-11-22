package com.company.service;

import com.company.dto.Employee;
import com.company.exception.EmployeeNotFoundException;
import com.company.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository repo;
    public Page<Employee> listAll(int pageNum) {
        int pageSize = 5;

        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);

        return repo.findAll(pageable);
    }

    public void save(Employee employee) {
        repo.save(employee);
    }

    public Employee get(Integer id) throws EmployeeNotFoundException {
        Optional<Employee> result = repo.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new EmployeeNotFoundException("Could not find any users with ID" + id);
    }

    public void delete(Integer id) throws EmployeeNotFoundException {
        Long count = repo.countById(id);
        if (count == null || count == 0) {
            throw new EmployeeNotFoundException("Could not find any users with ID" + id);
        }
        repo.deleteById(id);

    }
}
