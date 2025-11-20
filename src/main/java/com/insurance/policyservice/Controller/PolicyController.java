package com.insurance.policyservice.Controller;

import com.insurance.policyservice.model.Policy;
import com.insurance.policyservice.Service.PolicyService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/policies")
public class PolicyController {

    private final PolicyService service;

    public PolicyController(PolicyService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Policy> create(@Valid @RequestBody Policy policy) {
        return ResponseEntity.ok(service.createPolicy(policy));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Policy> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getPolicyById(id));
    }

    @GetMapping
    public ResponseEntity<List<Policy>> getAll() {
        return ResponseEntity.ok(service.getAllPolicies());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Policy> update(@PathVariable Long id, @Valid @RequestBody Policy policy) {
        return ResponseEntity.ok(service.updatePolicy(id, policy));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deletePolicy(id);
        return ResponseEntity.noContent().build();
    }
}

