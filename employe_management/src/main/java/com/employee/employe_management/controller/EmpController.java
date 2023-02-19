package com.employee.employe_management.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.employee.employe_management.entity.Employee;
import com.employee.employe_management.service.EmpService;
import org.springframework.data.domain.Page;

import jakarta.servlet.http.HttpSession;








@Controller
public class EmpController {

	@Autowired
	private EmpService service;

	@GetMapping("/")
	public String home(Model m) {
		return findPaginated(0, m);
	}

	@GetMapping("/addemp")
	public String addEmpForm() {
		return "add_emp";
	}

	@PostMapping("/register")
	public String empRegister(@ModelAttribute Employee e) {
		service.addEmp(e);
		
		return "redirect:/";
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable int id, Model m) {
		Employee e = service.getEMpById(id);
		m.addAttribute("emp", e);
		return "edit";
	}

	@PostMapping("/update")
	public String updateEmp(@ModelAttribute Employee e, HttpSession session) {
		service.addEmp(e);
		
		return "redirect:/";
	}

	@GetMapping("/delete/{id}")
	public String deleteEMp(@PathVariable int id) {

		service.deleteEMp(id);
		
		return "redirect:/";
	}

	@GetMapping("/page/{pageno}")
	public String findPaginated(@PathVariable int pageno, Model m) {

		Page<Employee> emplist = service.getEMpByPaginate(pageno, 2);
		m.addAttribute("emp", emplist);
		m.addAttribute("currentPage", pageno);
		m.addAttribute("totalPages", emplist.getTotalPages());
		m.addAttribute("totalItem", emplist.getTotalElements());
		return "index";
	}

}