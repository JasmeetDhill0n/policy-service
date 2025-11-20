package com.insurance.policyservice.Repository;

import com.insurance.policyservice.model.Policy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

    public interface PolicyRepository extends JpaRepository<Policy, Long> {

        Optional<Policy> findByPolicyNumber(String policyNumber);
    }
