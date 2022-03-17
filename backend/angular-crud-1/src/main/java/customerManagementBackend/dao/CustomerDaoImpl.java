package customerManagementBackend.dao;

import customerManagementBackend.entity.Customer;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.Date;
import java.util.List;

@Repository
public class CustomerDaoImpl implements CustomerDao{
    @Autowired
    public SessionFactory sessionFactory;
    @Override
    public Customer addCustomer(Customer customer) {

        if (customer==null){
            return null;
        }
        sessionFactory.getCurrentSession().saveOrUpdate(customer);
        return customer;
    }

    @Override
    public List<Customer> getAllCustomers() {
        System.out.println("~~~~enter in getAllCustomers method in dao~~~~~");
        List<Customer> customerLst=sessionFactory.getCurrentSession().createQuery("from Customer").list();

        if (customerLst==null){
            return null;
        }
        System.out.println("customerLst in dao from database : "+customerLst);
        return customerLst;
    }

    @Override
    public Customer modifyCustomer(Customer customer) {
        if (customer==null){
            return null;
        }
        Customer customerToModify = getCustomerByCode(customer.getCustomerCode());

        customerToModify.setFirstName(customer.getFirstName());
        customerToModify.setLastName(customer.getLastName());
        customerToModify.setEmailAddress(customer.getEmailAddress());
        customerToModify.setContact(customer.getContact());
        sessionFactory.getCurrentSession().merge(customerToModify);

        return customerToModify;

    }

    @Override
    public void deleteCustomer(String customerCode) {
        Customer customer = getCustomerByCode(customerCode);
        sessionFactory.getCurrentSession().delete(customer);
    }

    @Override
    public Customer getCustomerByCode(String customerCode) {
        Customer customer = new Customer();

        Query query = sessionFactory.getCurrentSession().createQuery("select c from Customer c where c.customerCode=:customerCodeParam");

        query.setParameter("customerCodeParam", customerCode);

        @SuppressWarnings("unchecked")
        List<Customer> customers = query.getResultList();

        if (customers.size() > 0) {

            customer = customers.get(0);

        }
        System.out.println("customer to modify obtained in dao : "+customer);
        return customer;    }
}
