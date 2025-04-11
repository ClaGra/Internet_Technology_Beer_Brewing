package ch.fhnw.brew.business.service;

import ch.fhnw.brew.data.domain.Customer;
import ch.fhnw.brew.data.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer editCustomer(Integer id, Customer updatedCustomer) throws Exception {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new Exception("Customer not found"));
        customer.setFirstName(updatedCustomer.getFirstName());
        customer.setLastName(updatedCustomer.getLastName());
        customer.setDateOfBirth(updatedCustomer.getDateOfBirth());
        customer.setStreet(updatedCustomer.getStreet());
        customer.setPostalAddress(updatedCustomer.getPostalAddress());
        customer.setZipCode(updatedCustomer.getZipCode());
        customer.setCity(updatedCustomer.getCity());
        customer.setEmail(updatedCustomer.getEmail());
        customer.setPhone(updatedCustomer.getPhone());
        customer.setGender(updatedCustomer.getGender());
        return customerRepository.save(customer);
    }

    public void deleteCustomer(Integer id) throws Exception {
        if (!customerRepository.existsById(id)) {
            throw new Exception("Customer does not exist");
        }
        customerRepository.deleteById(id);
    }

    public Customer getCustomer(Integer id) throws Exception {
        return customerRepository.findById(id).orElseThrow(() -> new Exception("Customer not found"));
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
}
