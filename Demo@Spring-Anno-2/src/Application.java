import com.cg.repository.CustomerRepository;
import com.cg.repository.HibernateCustomerRepositoryImpl;
import com.cg.service.CustomerService;
import com.cg.service.CustomerServiceImpl;
//import com.cg.service.CustomerServiceImpl2;


public class Application {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		CustomerRepository customerRepository = new HibernateCustomerRepositoryImpl();
		
//		CustomerService service = new CustomerServiceImpl(customerRepository);
		
		CustomerService service = new CustomerServiceImpl();
		((CustomerServiceImpl)service).setCustomerRepository(customerRepository);
		
		System.out.println(service.findAll().get(0).getFirstname());
	}

}
