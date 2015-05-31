package  com.yang.spinach.dict.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import com.yang.spinach.dict.entity.Dict;
import com.yang.spinach.dict.service.DictService;


/**
 * 
 * @author <Auto generate>
 * @version  2015-05-31 15:46:09
 * @see com.yang.spinach.web.Dict
 */
@Controller
@RequestMapping(value="/dict")
public class DictController {
	 @Autowired
	private DictService dictService;
	
	
}
