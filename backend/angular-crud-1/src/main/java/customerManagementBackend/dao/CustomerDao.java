package customerManagementBackend.dao;

import customerManagementBackend.entity.Customer;

import java.util.List;

public interface CustomerDao {
    Customer addCustomer(Customer customer);
    List<Customer> getAllCustomers();
    Customer modifyCustomer(Customer customer);
    void deleteCustomer(String customerCode);
    Customer getCustomerByCode(String customerCode);
}
