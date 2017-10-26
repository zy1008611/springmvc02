package com.zhidi.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zhidi.entity.Student;

@Controller
public class IndexController {
	//返回值为String 表示跳转jsp页面
	@RequestMapping("/index")
	public String testRequest(HttpServletRequest req){
		req.setAttribute("username", "测试请求");
		return "request";
	}
	
	@RequestMapping("/ByRequest")
	public void testAPI(HttpServletRequest req ,HttpServletResponse resp) throws IOException{
		req.setCharacterEncoding("utf-8");
		resp.setContentType("application/json;charset=utf-8");
		//req.setAttribute("username", "测试请求与响应");
		//{"username":"测试","password":"qingqiu","newpassword":"响应"}
		PrintWriter out = resp.getWriter();
		out.print("{\"username\":\"测试\",\"password\":\"qingqiu\",\"newpassword\":\"响应\"}");
		out.flush();
		out.close();
	}
	
	//相当于request接收
	@RequestMapping("/ByModel")
	public String byModel(Model model){
		model.addAttribute("username", "我找model");
		return "request";
	}
	//相当于request接收
	@RequestMapping("/ByModelMap")
	public String byModelMap(ModelMap mm){
		mm.addAttribute("username", "我找modelmap");
		return "request";
	}
	
	@RequestMapping("/bysession")
	public String bySession(HttpServletRequest req){
		HttpSession session = req.getSession();
		session.setAttribute("username", "我找session");
		return "request";
	}
	//简单数据类型+String 可以自动匹配类型
	@RequestMapping("/string")
	public String byString(String username){
		System.out.println(username);
		return "request";
	}
	@RequestMapping("/Integer")
	public String byInteger(Integer id){
		System.out.println(id);
		return "request";
	}
	//javaBean类型 传参时url上带参数
	@RequestMapping("/pojo")
	public String byStudent(Student stu){
		System.out.println(stu);
		return "request";
	}
	//当使用@RequestParam时， 其中的required属性默认为true，url路径笔必须带参数
	//defaultValue 代表id没传时，的默认值 ，当value 后面的值名与Integer的 id不一致时，默认识别value后的值
	@RequestMapping("/encoding")
	public String byEncoding(@RequestParam(value="id",defaultValue="110",required=true) Integer id){
		System.out.println(id);
		return "request";
	}
	//跳转页面
	@RequestMapping("/toparam")
	public String testToParam(){
		return "register";
	}
	//页面处理
	@RequestMapping("/date")
	public String parm(Student stu){
		System.out.println(stu);
		return "request";
	}
	/**
	 * 由于浏览器默认不能将String类型转成Date类型，
	 * 1，使用@DateTimeFormat注解（使用时，把该注解当做参数传入方法中），xml中需开启springMVC的驱动注解 （页面传参）
	 * 2，自定义时间转化类，
	 *  1、、实现converter接口，并重写convert方法
	 *  2、、将自定义的时间转化器，放入Bean中 
	 *  3、、将格式化转换器工厂放入到Bean中，并将自定义的日期转化器注入到格式化转换器工厂
	 *  4、、将格式化转换工厂注册到注解驱动 
	 * @param date 
	 * @return
	 */
	@RequestMapping("/date3")
	public String testparam(@DateTimeFormat(pattern="yyyy-MM-dd")Date date){
		System.out.println(date);
		return "request";
	}
	/**
	 * 参数绑定问题测试()
	 */
	@RequestMapping("/{date},{sex}/urlParam/{name}")
	public String testUrlParam(@PathVariable("date") Date date,
			@PathVariable("name") String name,
			@PathVariable("sex") Integer sex){
		System.out.println(date);
		System.out.println(name);
		System.out.println(sex);
		return "request";
	}
	
}
