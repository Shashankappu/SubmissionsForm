package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class FormController {
	@Autowired
	CustomersRepo repo;

	@RequestMapping("/")
	public String details() {
		return "webeduforms";
	}

	@RequestMapping("/details")
	public String details(Customers customers) {
		repo.save(customers);
		return "webeduforms";
	}

	@RequestMapping("/getdetails")
	public String getdetails(Customers customers) {
		return "ViewCustomers";
	}

	@PostMapping("/getdetails")
	public ModelAndView getdetails(@RequestParam("cid") int cid) {
		ModelAndView mv = new ModelAndView("Retrieve");
		Customers customers = repo.findById(cid).orElse(null);
		mv.addObject(customers);
		return mv;
	}
	
	@RequestMapping("/customers")
	@ResponseBody
	public List<Customers> getCustomers() {
		return repo.findAll();
	}
	
	@RequestMapping("/customers/{cid}")
	@ResponseBody
	public Optional<Customers> getCustomers(@PathVariable("cid") int cid) {
		return repo.findById(cid);
	}
	@PostMapping("/customers")
	public Customers insertCustomers(@RequestBody Customers customers) {
		repo.save(customers);
		return customers;
	}
	@DeleteMapping("/customers/{cid}")
	public Customers deleteCustomer(@PathVariable("cid") int cid) {
		Customers cust = repo.getOne(cid);
		repo.delete(cust);
		return cust;
	}
	@PutMapping(path = "/customers",consumes = {"application/json"})
	public Customers deleteCustomer(@RequestBody Customers customers) {
		repo.save(customers);
		return customers;
	}
}
