package com.rookies4.myspringboot.repository;

import org.junit.jupiter.api.Disabled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.rookies4.myspringboot.entity.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

//@Transactional
@SpringBootTest
class CustomerRepositoryTest {
    @Autowired
    CustomerRepository customerRepository;

    @Test @Disabled
    void testFindCustomer() {
        //Customer 조회
        Optional<Customer> customerById = customerRepository.findById(1L);

        //assertThat(customerById).isNotEmpty();
        //assertThat(customerById).isEmpty();
        if(customerById.isPresent()){
            Customer existCustomer = customerById.get();
            assertThat(existCustomer.getId()).isEqualTo(1L);
        }

        Optional<Customer> customerByCustomerId = customerRepository.findByCustomerId("AC001");
        Customer ac001Customer = customerByCustomerId.orElseGet(() -> new Customer());
        assertThat(ac001Customer.getCustomerName()).isEqualTo("스프링부트");

        Customer notFoundCustomer =
                customerRepository.findByCustomerId("AC003").orElseGet(() -> new Customer());
        assertThat(notFoundCustomer.getCustomerName()).isNull();
    }

    @Test
//    @Rollback(value = false)
    void testUpdateCustomer(){
        Customer customer =
                customerRepository.findByCustomerId("AC001")
                        .orElseThrow(() -> new RuntimeException("Customer Not Found"));
        customer.setCustomerName("SpringBoot");
//        customer.setCustomerName("마이둘리");
//        customerRepository.save(customer);
        customer.setCustomerName("마이둘리2");
        customerRepository.save(customer);
    }

    @Test
    @Disabled
    void testNotFoundCustomer() {
        //Customer 조회 존재하지 않으면 예외발생
        Customer notFoundCustomer =
                customerRepository.findByCustomerId("AC003")
                        .orElseThrow(() -> new RuntimeException("Customer Not Found"));
    }

    @Test
    @Rollback(value = false)
//    @Disabled
    void testSaveCustomer() {
        //Given (준비단계)
        Customer customer = new Customer();
//        customer.setCustomerId("AC001");
//        customer.setCustomerName("스프링부트");
//        customer.setCustomerId("AC002");
//        customer.setCustomerName("스프링FW");
        customer.setCustomerId("AC003");
        customer.setCustomerName("스프링FW3");
        //When (실행단계)
        Customer savedCustomer = customerRepository.save(customer);
        //Then (검증단계)

//        assertThat(savedCustomer).isNotNull();
//        assertThat(savedCustomer.getCustomerName()).isEqualTo("스프링FW");
    }
}