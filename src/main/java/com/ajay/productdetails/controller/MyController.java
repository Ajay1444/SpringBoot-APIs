package com.ajay.productdetails.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.ajay.productdetails.entity.Bike;
import com.ajay.productdetails.repository.BikeRepository;

@Controller
public class MyController {
	@Autowired
	private BikeRepository br;

	@GetMapping("/")
	public String home(Model m) {

		List<Bike> list = br.findAll();
		m.addAttribute("all_bikes", list);

		return "index";
	}

	@GetMapping("/load_form")
	public String loadForm() {
		return "add";
	}

	@GetMapping("/edit_form/{id}")
	public String editForm(@PathVariable(value = "id") long id, Model m) {

		Optional<Bike> bike = br.findById(id);

		Bike bk = bike.get();
		m.addAttribute("bike", bk);

		return "edit";
	}

	@PostMapping("/save_bikes")
	public String saveBikes(@ModelAttribute Bike bikes, HttpSession session) {

		br.save(bikes);
		session.setAttribute("msg", "Bike details Added Sucessfully..");

		return "redirect:/load_form";
	}

	@PostMapping("/update_bikes")
	public String updateBikes(@ModelAttribute Bike bikes, HttpSession session) {

		br.save(bikes);
		session.setAttribute("msg", "Bike details Update Sucessfully..");

		return "redirect:/";
	}

	@GetMapping("/delete/{id}")
	public String deleteBikes(@PathVariable(value = "id") long id, HttpSession session) {
		br.deleteById(id);
		session.setAttribute("msg", "Bike details Delete Sucessfully..");

		return "redirect:/";

	}

}
