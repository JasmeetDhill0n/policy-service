package com.insurance.policyservice.Service;

import com.insurance.policyservice.model.Policy;
import com.insurance.policyservice.Repository.PolicyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

    @Service
    public class PolicyService {

        private final PolicyRepository repository;

        public PolicyService(PolicyRepository repository) {
            this.repository = repository;
        }

        public Policy createPolicy(Policy policy) {
            return repository.save(policy);
        }

        public Policy getPolicyById(Long id) {
            return repository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Policy not found with id: " + id));
        }

        public List<Policy> getAllPolicies() {
            return repository.findAll();
        }

        public Policy updatePolicy(Long id, Policy updated) {
            Policy existing = getPolicyById(id);
            existing.setPolicyNumber(updated.getPolicyNumber());
            existing.setPolicyHolderName(updated.getPolicyHolderName());
            existing.setPremiumAmount(updated.getPremiumAmount());
            existing.setStartDate(updated.getStartDate());
            existing.setEndDate(updated.getEndDate());
            existing.setStatus(updated.getStatus());
            return repository.save(existing);
        }

        public void deletePolicy(Long id) {
            repository.deleteById(id);
        }
    }

