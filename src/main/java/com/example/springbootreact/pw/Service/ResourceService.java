package com.example.springbootreact.pw.Service;

import com.example.springbootreact.pw.Entity.Employee;
import com.example.springbootreact.pw.Entity.Resource;
import com.example.springbootreact.pw.Repository.EmployeeRepository;
import com.example.springbootreact.pw.Repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.core.JdbcAggregateOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ResourceService {
    
    private final ResourceRepository resourceRepository;

    private final EmployeeRepository employeeRepository;

    @Autowired
    public ResourceService(ResourceRepository resourceRepository, EmployeeRepository employeeRepository) {
        this.resourceRepository = resourceRepository;
        this.employeeRepository = employeeRepository;
    }

    public List<Resource> getAllResources() {

        return resourceRepository.findAll();
    }

    public void addResource(Resource resource) {
        resourceRepository.save(resource);
    }


    public void deleteResource(Integer id) {
        resourceRepository.deleteById(id);
    }

    public void updateResource(Integer id, Map<String, Object> updates) {
        Resource resource = resourceRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Resource not found"));

        if (updates.containsKey("status")) {
            String status = (String) updates.get("status");
            resource.setStatus(status);
        }

        if (updates.containsKey("note")) {
            String note = (String) updates.get("note");
            resource.setNote(note);
        }

        resourceRepository.save(resource);
    }

    public Resource registerResource(Integer id, Integer empid) {
        Optional<Resource> resourceOptional = resourceRepository.findById(id);
        if (resourceOptional.isPresent()) {
            Resource resource = resourceOptional.get();
            if ("Còn trống".equals(resource.getStatus())) {
                resource.setStatus("Đã đăng ký");
                Optional<Employee> employeeOptional = employeeRepository.findById(empid);
                if(employeeOptional.isPresent()) {
                    resource.setEmployee(employeeOptional.get());
                }
                return resourceRepository.save(resource);
            }
        }
        return null;
    }

    public Resource acceptResource(Integer id) {
        Optional<Resource> resourceOptional = resourceRepository.findById(id);
        if (resourceOptional.isPresent()) {
            Resource resource = resourceOptional.get();
            if ("Đã đăng ký".equals(resource.getStatus())) {
                resource.setStatus("Đã được mượn");
                return resourceRepository.save(resource);
            }
        }
        return null;
    }

    public Resource rejectResource(Integer id) {
        Optional<Resource> resourceOptional = resourceRepository.findById(id);
        if (resourceOptional.isPresent()) {
            Resource resource = resourceOptional.get();
            resource.setStatus("Còn trống");
            resource.setEmployee(null);
            return resourceRepository.save(resource);
        }
        return null;
    }
}
