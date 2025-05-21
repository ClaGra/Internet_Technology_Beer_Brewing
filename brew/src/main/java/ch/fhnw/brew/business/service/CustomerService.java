package ch.fhnw.brew.business.service;

import ch.fhnw.brew.data.domain.Customer;
import ch.fhnw.brew.data.repository.CustomerRepository;
import ch.fhnw.brew.data.repository.OrderRepository;
import ch.fhnw.brew.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderRepository orderRepository; 

    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer editCustomer(Integer id, Customer updatedCustomer) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Customer not found"));

        customer.setFirstName(updatedCustomer.getFirstName());
        customer.setLastName(updatedCustomer.getLastName());
        customer.setDateOfBirth(updatedCustomer.getDateOfBirth());
        customer.setStreet(updatedCustomer.getStreet());
        customer.setZipCode(updatedCustomer.getZipCode());
        customer.setCity(updatedCustomer.getCity());
        customer.setEmail(updatedCustomer.getEmail());
        customer.setPhone(updatedCustomer.getPhone());
        customer.setGender(updatedCustomer.getGender());

        return customerRepository.save(customer);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteCustomer(Integer id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Customer does not exist"));

        boolean hasOrders = orderRepository.existsByCustomerCustomerID(id);
        if (hasOrders) {
            throw new IllegalStateException("Customer cannot be deleted because they have existing orders");
        }

        customerRepository.delete(customer);
    }

    public Customer getCustomer(Integer id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Customer not found"));
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
}
