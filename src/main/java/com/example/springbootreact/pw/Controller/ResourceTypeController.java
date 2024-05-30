package com.example.springbootreact.pw.Controller;

import com.example.springbootreact.pw.Entity.ResourceType;
import com.example.springbootreact.pw.Service.ResourceTypeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/resourcetypes")
public class ResourceTypeController {

    private final ResourceTypeService resourceTypeService;

    public ResourceTypeController(ResourceTypeService resourceTypeService) {
        this.resourceTypeService = resourceTypeService;
    }

    @GetMapping
    public List<ResourceType> getAllResourceTypes() {
        return resourceTypeService.getAllResourceType();
    }

    @PostMapping("/add")
    public void addResourceType(@RequestBody ResourceType resourceType) {
        resourceTypeService.addResourceType(resourceType);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteResourceType(@PathVariable Integer id) {
        resourceTypeService.deleteResourceType(id);
    }

    @PutMapping(path ="/edit/{id}")
    public void updateEmployee(@PathVariable int id, @RequestBody Map<String, Object> updates){
        resourceTypeService.updateResourceType(id, updates);
    }
}
