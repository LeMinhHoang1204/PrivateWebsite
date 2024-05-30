package com.example.springbootreact.pw.Service;

import com.example.springbootreact.pw.Entity.ResourceType;
import com.example.springbootreact.pw.Repository.ResourceTypeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ResourceTypeService {

    private final ResourceTypeRepository resourceTypeRepository;

    @Autowired
    public ResourceTypeService(ResourceTypeRepository resourceTypeRepository) {
        this.resourceTypeRepository = resourceTypeRepository;
    }

    public List<ResourceType> getAllResourceType() {
        return resourceTypeRepository.findAll();
    }

    public void addResourceType(ResourceType resourceType) {
        resourceTypeRepository.save(resourceType);
    }

    public void deleteResourceType(Integer id) {
        resourceTypeRepository.deleteById(id);
    }

    @Transactional
    public void updateResourceType(int id, Map<String, Object> updates) {
        ResourceType resourceType = resourceTypeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("resourceType not found"));

        if (updates.containsKey("quantity")) {
            Integer quantity = (Integer) updates.get("quantity");
            resourceType.setQuantity(quantity);
        }

        if (updates.containsKey("borrowQuantity")) {
            Integer borrowQuantity = (Integer) updates.get("borrowQuantity");
            resourceType.setBorrowedQuantity(borrowQuantity);
        }

        if (updates.containsKey("note")) {
            String note = (String) updates.get("note");
            resourceType.setNote(note);
        }

        resourceTypeRepository.save(resourceType);
    }
}
