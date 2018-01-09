/**
 * 
 */
package com.sy.ShowSy.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** 
* @ClassName: IndexController 
* @Description: 
* @author albert
* @date 2017年11月3日 下午4:19:50 
*  
*/
@RestController
@RequestMapping("/")
public class IndexController {
	@RequestMapping(value={"","/index"})
	public String index(){
		System.out.println("hahah fdsafdsa");
		return "indexfuck";
	}
}
