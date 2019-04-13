package code.empmgmt;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EmployeeStore {
	private String SUCESS = "Sucess";
	private Map<String, Employee> map;
	private int empid = 1;
	
	private Status addEmployee(Employee emp) {
		/*if(map.get(emp.getEmilId())) {
			
		}*/
		
		//validateEmployeeDetails(emp);
		
		map.put(emp.getEmilId(), emp);

		Employee manager = map.get(emp.getManagerId());
		manager.addReportee(emp);
		
		return new Status(Code.SUCESS, SUCESS);
	}
	
	private Status relieveEmployee(String empid) {
		Employee emp = map.get(empid);
		
		if(emp == null) {
			// return error
		}
		
		//If CEO gets changed
		
		if(emp.getManagerId() == "" || emp.getManagerId() == null) {
			// CEO can't be removed before appointing the new one
		}
		
		//Changes reportee manager id to removed manager id
		List<Employee> reportee = emp.getReportees();
		Employee manager = map.get(emp.getManagerId());
		
		for(int i=0; i<reportee.size(); i++) {
			reportee.get(i).setManagerId(manager.getEmilId());
			manager.addReportee(reportee.get(i));
		}

		return new Status(Code.SUCESS, SUCESS);
	}
	
	private List<Employee> getHeirarchyOFEmployee(String empid) {
		List<Employee> lst = new ArrayList<Employee>();
		Employee emp = map.get(empid);
		lst.add(emp);
		
		while(emp.getManagerId() != null && emp.getManagerId() != " ") {
			emp = map.get(emp.getManagerId());
			lst.add(emp);
		}
		
		return lst;
	}
	
	private Status promoteEmployee(String empid, String manageid) {
		Employee emp = map.get(empid);
		Level level = emp.getLevel();
		
		switch(level.toString()) {
		case "L1" :
			emp.setLevel(Level.L2);
			break;
		case "L2" :
			emp.setLevel(Level.L3);
			break;
		case "L3" :
			emp.setLevel(Level.L4);
			break;
		case "L4" :
			emp.setLevel(Level.L5);
			break;
		case "L5" :
			emp.setLevel(Level.L6);
			break;
		case "L6" : // CEO changes
			emp.setLevel(Level.L7);
			
			Employee manager = map.get(emp.getManagerId());
			
			List<Employee> reportee = manager.getReportees();
			
			for(int i=0; i<reportee.size(); i++) {
				reportee.get(i).setManagerId(manager.getEmilId());
				emp.addReportee(reportee.get(i));
			}
			
			break;
		case "L7" :
			new Status(Code.FAILURE, "No higher level exists");
		}
		
		if(manageid != null && manageid!= " ") {
			
		}
		
		return new Status(Code.SUCESS, SUCESS);
	}
	
}
