package jp.co.neosystem.kadai.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Locale;

@Controller
public class MainController {

	@RequestMapping("/")
	public String profile(Locale locale, Model model) {
		return "index.html";
	}
}
