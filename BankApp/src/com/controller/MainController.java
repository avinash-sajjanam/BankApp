package com.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dao.UserInfo;
import com.daoImpl.UserInfoImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.Account;
import com.model.User;
import com.utils.JsonMessage;

@Controller
public class MainController {

	@Autowired
	UserInfo userinfo;

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String loggingPage(HttpServletRequest request, HttpServletResponse response) {
		return "home";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String Register(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws IOException {
		Random random=new Random();
		String firstname=request.getParameter("fname");
		String lastname=request.getParameter("lname");
		int accountnumber=random.nextInt(99999999);
		String username = request.getParameter("uname");
		String password = request.getParameter("pass");

		System.out.println(username);
		try {
			Account account= new Account();
			account.setAccountnumber(accountnumber);
//			account.setChecking(0);
//			account.setSaving(0);
			
			User user = new User();
			user.setFirstname(firstname);
			user.setLastname(lastname);
			user.setUsername(username);
			user.setPassword(password);
			
			user.setAccount(account);
			model.addAttribute(user);
			userinfo.save(user);

		} catch (Exception e) {
			e.printStackTrace();
		}System.out.println("successfully registered");
		return "RegisterSuccess";
	}


	@RequestMapping(value = {"/logincheck"}, method = RequestMethod.POST)
	public String UserValidation(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
		String username = request.getParameter("uname");
		String password = request.getParameter("pass");
		
		HttpSession session=request.getSession();
		session.setAttribute("username", username);

		try {
			User user = new User();
			user.setUsername(username);
			user.setPassword(password);

			userinfo.checkUser(user);
			if(UserInfoImpl.b==true){	
				return "Welcome";
			}
			else{
				return "Invalid";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Error";
	}
	
	@RequestMapping(value = {"/deposit"}, method = RequestMethod.POST)
	public String deposit(HttpServletRequest request,HttpServletResponse response){
		
		String amt=request.getParameter("depositamt");
		int amount= Integer.parseInt(amt);
		String acntnumber=request.getParameter("accountnumber");
		int accountnumber=Integer.parseInt(acntnumber);
		String type=request.getParameter("accounttype");
//		System.out.println(amount+accountnumber+type+amount);
		try{
			Account account=new Account();
			account.setAccountnumber(accountnumber);
			if(type.equals("Checking")){
				account.setChecking(amount);
			}
			else{
				account.setSaving(amount);
			}
			userinfo.depositByAccount(account);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "Welcome";	
	}
	
	@RequestMapping(value="/get_AccountData", method=RequestMethod.GET)
	public void getAccountData(HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> model = new HashMap<String, Object>();
		ObjectMapper mapper = new ObjectMapper();
		HttpSession session=request.getSession();
		String username=session.getAttribute("username").toString();
		System.out.println(username);
		try {
			model.put("accountdatas", mapper.writeValueAsString(userinfo.findUserbyname(username)));
			JsonMessage msg = new JsonMessage(mapper.writeValueAsString(model));
			JSONObject obj = new JSONObject(msg);
			response.getWriter().write(obj.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
