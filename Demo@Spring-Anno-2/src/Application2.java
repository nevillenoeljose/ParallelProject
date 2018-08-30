import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.cg.service.CustomerService;

public class Application2 {

	public static void main(String[] args) {
		
		ApplicationContext applicationContext
			= new ClassPathXmlApplicationContext("applicationContext.xml");
		
		CustomerService service = applicationContext.getBean("customerService", CustomerService.class);
		
		System.out.println(service.findAll().get(0).getFirstname());
		
		CustomerService service3 =
				applicationContext.getBean("customerService", CustomerService.class);
		
		System.out.println(service == service3);
		ApplicationContext appContext2 = 
				new FileSystemXmlApplicationContext("./config/applicationContext.xml");
		CustomerService service2 = 
				appContext2.getBean("customerService", CustomerService.class);
		
		System.out.println(service == service2);
	}

}
