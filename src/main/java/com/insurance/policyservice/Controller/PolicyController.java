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
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            Policy policy = service.getPolicyById(id);

            if (policy == null) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("Policy not found with id: " + id);
            }

            return ResponseEntity.ok(policy);

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Something went wrong while fetching policy. Error: " + e.getMessage());
        }
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

