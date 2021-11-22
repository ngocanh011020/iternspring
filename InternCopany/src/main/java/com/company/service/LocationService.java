package com.company.service;

import com.company.exception.EmployeeNotFoundException;
import com.company.dto.Location;
import com.company.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class LocationService {
    @Autowired
    private LocationRepository repo;
    public Page<Location> listAll(int pageNum) {
        int pageSize = 5;

        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);

        return repo.findAll(pageable);
    }

    public void save(Location location) {
        repo.save(location);
    }

    public Location get(Integer id) throws EmployeeNotFoundException {
        Optional<Location> result = repo.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new EmployeeNotFoundException("Could not find any location with ID" + id);
    }

    public void delete(Integer id) throws EmployeeNotFoundException {
        Long count = repo.countById(id);
        if (count == null || count == 0) {
            throw new EmployeeNotFoundException("Could not find any location with ID" + id);
        }
        repo.deleteById(id);

    }
    @Transactional
    public List<Location> getall (){
        return repo.findAll();
    }

}
