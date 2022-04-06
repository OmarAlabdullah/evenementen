package evenementen.event.application;

import evenementen.event.data.CustomerRepository;
import evenementen.event.domain.Customer;
import evenementen.event.domain.exception.CustomerAlreadyExistsException;
import evenementen.event.domain.exception.CustomerNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Transactional
@Service
public class CustomerService {
    private CustomerRepository customerRepository;


    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void save(Long customerId, String firstname, String lastname, String password, String email) {
        if (customerRepository.existsById(customerId)) {
            throw new CustomerAlreadyExistsException();
        }
        this.customerRepository.save(new Customer(customerId, firstname, lastname, password, email));

    }

    public void update(Long customerId, String firstname, String lastname, String password, String email) {
        if (customerRepository.existsById(customerId)) {

            this.customerRepository.save(new Customer(customerId, firstname, lastname, password, email));
        } else {
            throw new CustomerNotFoundException();
        }

    }

    public void deleteCustomerById(Long customerId) {
        this.customerRepository.deleteById(customerId);
    }

    public Customer findCustomerById(Long customerId) {
        return this.customerRepository.findById(customerId).
                orElseThrow(CustomerNotFoundException::new);
    }

    public Customer findByUserName(String customerUsername) {
        System.out.println(this.customerRepository.findByEmail(customerUsername));
        return  this.customerRepository.findByEmail(customerUsername).
                orElseThrow(CustomerNotFoundException::new);
    }


    public List<Customer> getAllCustomers() {
        return this.customerRepository.findAll();
    }



}
