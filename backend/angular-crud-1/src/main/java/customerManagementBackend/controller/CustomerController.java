package customerManagementBackend.controller;


import customerManagementBackend.entity.Customer;
import customerManagementBackend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@ResponseBody
@CrossOrigin
@RequestMapping("/api/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @RequestMapping("/addCustomer/{customerCode}/{firstName}/{lastName}/{email}/{contact}")
    public int addCustomer(@PathVariable("customerCode")String customerCode, @PathVariable("firstName")String firstName, @PathVariable("lastName")String lastName, @PathVariable("email")String email, @PathVariable("contact")String contact){
        System.out.println("addCustomer Called!");
        System.out.println("customer code : "+customerCode);
        System.out.println("first Name : "+firstName);
        System.out.println("last Name : "+lastName);
        System.out.println("email  : "+email);
        System.out.println("contact  : "+contact);
        Customer customerToAdd=new Customer();
        customerToAdd.setCustomerCode(customerCode);
        customerToAdd.setFirstName(firstName);
        customerToAdd.setLastName(lastName);
        customerToAdd.setEmailAddress(email);
        customerToAdd.setContact(contact);
        Customer customer=customerService.addCustomer(customerToAdd);
        System.out.println("Added customer : "+customer);
        if (customer!=null){
            return 1;
        }
        return 0;
    }

    @RequestMapping("/getAllCustomer")
    public List<Customer> getAllCustomer(){
        System.out.println("get All Customer Called!");
        List<Customer> customerList=new ArrayList<>();
         customerList= customerService.getAllCustomers();
        System.out.println("customer List in getAllCustomer : "+customerList);
        return customerList;
    }

    @RequestMapping("/deleteCustomer/{customerCode}")
    public int deleteCustomer(@PathVariable("customerCode")String customerCode){
           customerService.deleteCustomer(customerCode);
           return 1;
    }


    @RequestMapping("/modifyCustomer/{customerCode}/{firstName}/{lastName}/{email}/{contact}")
    public int modifyCustomer(@PathVariable("customerCode")String customerCode, @PathVariable("firstName")String firstName, @PathVariable("lastName")String lastName, @PathVariable("email")String email, @PathVariable("contact")String contact){
        System.out.println("modifyCustomer Called!");
        System.out.println("customer code : "+customerCode);
        System.out.println("first Name : "+firstName);
        System.out.println("last Name : "+lastName);
        System.out.println("email  : "+email);
        System.out.println("contact  : "+contact);
        Customer customerToModify=new Customer();
        customerToModify.setCustomerCode(customerCode);
        customerToModify.setFirstName(firstName);
        customerToModify.setLastName(lastName);
        customerToModify.setEmailAddress(email);
        customerToModify.setContact(contact);
        Customer modifiedCustomer=customerService.modifyCustomer(customerToModify);
        System.out.println("modified customer : "+modifiedCustomer);
        if (modifiedCustomer!=null){
            return 1;
        }
        return 0;
    }

}
