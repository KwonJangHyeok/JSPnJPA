package com.mycompany.ap.Admin;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "")
public class AdminController {

	@Autowired
	AdminSearchService svc;

	// LoginAdmin
	@RequestMapping(value = "/LoginAdmin")
	public String LoginAdmin(Model model) {

		//System.out.println("!!");
		return "LoginAdmin";
	}

	@RequestMapping(value = "/Admin")
	public String Admin(Model model) {

		System.out.println("!!");
		return "Admin";
	}

	// HttpSession session, HttpServletRequest request
	@RequestMapping("/LoginCheck.do")
	@ResponseBody
	// admin1!yyyymmdd (날짜)
	public int check(@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "pw", required = false) String pw, Model model,HttpSession session, HttpServletResponse response) {
		
		Date today = new Date();

		SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMdd");
		String str = format1.format(today).toString();

		System.out.println("!");
		if (!id.equals("admin") || !pw.equals("admin1!" + str)) {
			System.out.println(id + " " + pw);
			return 0;
		}

		 
		{
			System.out.println("Else");
			try {
				System.out.println("Logined");
				session.setAttribute("isAdmin", "True");
				//response.sendRedirect("Admin.jsp");
				System.out.println("!!!!" + session.getAttribute("isAdmin"));
				return 1;
			}

			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

		return 0;
	}

	@RequestMapping("/delete.do")
	@ResponseBody
	public int remove(@RequestParam(value = "id", required = false) String id, HttpServletRequest request) {
		long _id = Long.parseLong(id);
		svc.remove(_id);
		return 1;
	}

	@RequestMapping(value = "/update.do", method = RequestMethod.POST)
	@ResponseBody
	public int update(@RequestParam(value = "dataList", required = true) String dataList, Model model) {

		try {
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObj = (JSONObject) jsonParser.parse(dataList);
			JSONArray searchArray = (JSONArray) jsonObj.get("dataList");
			System.out.println(searchArray.size());
			System.out.println(dataList);

			for (int i = 0; i < searchArray.size(); i++) {
				// 배열 안에 있는것도 JSON형식 이기 때문에 JSON Object 로 추출
				JSONObject Obj = (JSONObject) searchArray.get(i);

				AdminSearchEntity vo = new AdminSearchEntity();
				vo.setCompany((String) Obj.get("company"));
				vo.setPic((String) Obj.get("pic"));
				vo.setContact((String) Obj.get("contact"));
				vo.setSystem((String) Obj.get("system"));
			
				if (!((String) Obj.get("idx")).equals(""))
					vo.setIdx(Long.parseLong((String) Obj.get("idx")));
				System.out.println(vo.getSystem());
				svc.save(vo);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return 1;
	}

	@RequestMapping("/AdminSearch.do")
	@ResponseBody
	public List<AdminSearchEntity> selectModel(Model model, HttpServletRequest request) {
		HttpSession ss = request.getSession();

		if (ss.getAttribute("isAdmin") == "True") {
			List<AdminSearchEntity> lst = svc.findAll();
			System.out.println("Search.do" + lst.size());
			ArrayList<AdminSearchEntity> retv = (ArrayList<AdminSearchEntity>) lst;

			return retv;
		}
		return null;
	}

	@RequestMapping("/AdminSearch1.do")
	@ResponseBody
	public List<AdminSearchEntity> search(@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "word", required = false) String word, Model model, HttpServletRequest request, HttpSession ss) {
		
		try 
		{
			if (ss.getAttribute("isAdmin") == "True") 
			{
				ArrayList<AdminSearchEntity> retv;
				if (type.equals("NameSys")) 
				{
					retv = (ArrayList<AdminSearchEntity>) svc.findBySystem(word);
					return retv;
				}

				else// if (type.equals("NamePer"))
				{
					retv = (ArrayList<AdminSearchEntity>) svc.findbyName(word);
					return retv;
				}
			}
			else return null;
		}

		catch (Exception e) 
		{
			System.out.println(e.getMessage());
			return null;
		}
		
	}
}

