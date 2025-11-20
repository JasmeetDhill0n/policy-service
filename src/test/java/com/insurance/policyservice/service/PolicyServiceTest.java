package com.insurance.policyservice.service;


import com.insurance.policyservice.Service.PolicyService;
import com.insurance.policyservice.model.Policy;
import com.insurance.policyservice.Repository.PolicyRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class PolicyServiceTest {

    @Test
    void getPolicyById_shouldReturnPolicy() {
        PolicyRepository repository = Mockito.mock(PolicyRepository.class);
        PolicyService service = new PolicyService(repository);

        Policy policy = Policy.builder()
                .id(1L)
                .policyNumber("POL123")
                .policyHolderName("Jasmeet")
                .premiumAmount(5000.0)
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusYears(1))
                .status("ACTIVE")
                .build();

        when(repository.findById(1L)).thenReturn(Optional.of(policy));

        Policy result = service.getPolicyById(1L);

        assertThat(result.getPolicyNumber()).isEqualTo("POL123");
    }
}
