package com.mycompany.ap.Normal;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handles requests for the application home page.
 */
@Controller
public class NormalController {

	private static final Logger logger = LoggerFactory.getLogger(NormalController.class);

	@Autowired
	NormalSearchService svc;

	@RequestMapping(value = "/")
	public String home(Locale locale, Model model) {

		// logger.info("Welcome home! The client locale is {}.", locale);

		model.addAttribute("auth");
		return "home";
	}

	@RequestMapping("/Search.do")
	@ResponseBody
	public List<NormalSearchEntity> selectModel(Model model) {
		List<NormalSearchEntity> lst = svc.findAll();
		System.out.println("Search.do" + lst.size());
		ArrayList<NormalSearchEntity> retv = (ArrayList<NormalSearchEntity>) lst;
		
		return retv;
	}

	@RequestMapping("/Search1.do")
	@ResponseBody
	public List<NormalSearchEntity> search(@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "word", required = false) String word, Model model) {

		ArrayList<NormalSearchEntity> retv;
		if (type.equals("NameSys")) {
			retv = (ArrayList<NormalSearchEntity>) svc.findBySystem(word);
			return retv;
		}

		else// if (type.equals("NamePer"))
		{
			System.out.println("Room2");
			retv = (ArrayList<NormalSearchEntity>) svc.findbyName(word);
			return retv;
		}
	}


}
