package br.com.gae.vraptor.blank;

import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;

@Resource
public class IndexController {

	@Path("/")
	public List<Employee> index() {
        PersistenceManager pm = PMF.get().getPersistenceManager();

        Employee employee = new Employee("Alfred", "Smith", new Date());
        
        try {
            pm.makePersistent(employee);
        } finally {
            pm.close();
        }
        
        pm = PMF.get().getPersistenceManager();
        
        String query = "select from " + Employee.class.getName() + " where lastName == 'Smith'";
        List<Employee> employees = (List<Employee>) pm.newQuery(query).execute();
        
        return employees;
	}
}
