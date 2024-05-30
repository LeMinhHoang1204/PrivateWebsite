package com.example.springbootreact.pw.Controller;

import com.example.springbootreact.pw.Entity.Resource;
import com.example.springbootreact.pw.Service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/resources")
public class ResourceController {

    private final ResourceService resourceService;

    @Autowired
    public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @GetMapping
    public List<Resource> getResources() {
        return resourceService.getAllResources();
    }

    @PostMapping("/admin/add")
    public void addResource(@RequestBody Resource resource) {
        resourceService.addResource(resource);
    }

    @DeleteMapping("/admin/delete/{id}")
    public void deleteResource(@PathVariable Integer id) {
        resourceService.deleteResource(id);
    }

    @PutMapping("/admin/edit/{id}")
    public void updateResource(@PathVariable Integer id, @RequestBody Map<String, Object> updates) {
        resourceService.updateResource(id, updates);
    }

    @PostMapping("/register/{id}")
    public  ResponseEntity<?> responseEntity(@PathVariable Integer id,  @RequestBody Map<String, Integer> updates){
        Integer empid = updates.get("empid");
        Resource registeredResource = resourceService.registerResource(id, empid);
        if (registeredResource != null) {
            return ResponseEntity.ok("Resource registered successfully!");
        } else {
            return ResponseEntity.badRequest().body("Resource is not available for registration or not found.");
        }
    }

    @PostMapping("/admin/accept/{id}")
    public  ResponseEntity<?> responseAcceptEntity(@PathVariable Integer id){
        Resource acceptedResource = resourceService.acceptResource(id);
        if (acceptedResource != null) {
            return ResponseEntity.ok("Resource accepted successfully!");
        } else {
            return ResponseEntity.badRequest().body("Resource is not available for registration or not found.");
        }
    }

    @PostMapping("/admin/reject/{id}")
    public  ResponseEntity<?> responseRejectEntity(@PathVariable Integer id){
        Resource rejectedResource = resourceService.rejectResource(id);
        if (rejectedResource != null) {
            return ResponseEntity.ok("Resource rejected successfully!");
        } else {
            return ResponseEntity.badRequest().body("Resource is not available for registration or not found.");
        }
    }

}
