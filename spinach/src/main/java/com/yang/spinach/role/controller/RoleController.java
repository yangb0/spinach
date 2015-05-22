package  com.yang.spinach.role.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import com.yang.spinach.role.entity.Role;
import com.yang.spinach.role.service.RoleService;


/**
 * 
 * @author <Auto generate>
 * @version  2015-05-21 23:16:42
 * @see com.yang.spinach.web.Role
 */
@Controller
@RequestMapping(value="/role")
public class RoleController {
	 @Autowired
	private RoleService roleService;
	
	
}
