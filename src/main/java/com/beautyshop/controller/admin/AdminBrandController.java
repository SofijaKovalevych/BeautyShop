package com.beautyshop.controller.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.beautyshop.entity.Brand;
import com.beautyshop.service.BrandService;
import com.beautyshop.validators.BrandValidator;

@Controller
@RequestMapping("/admin/brand")
@SessionAttributes("brand")
public class AdminBrandController {

	@Autowired
	private BrandService brandService;

	@InitBinder("brand")
	protected void bind(WebDataBinder binder) {
		binder.setValidator(new BrandValidator(brandService));
	}

	@ModelAttribute("brand")
	public Brand getForm() {
		return new Brand();
	}

	@RequestMapping
	public String show(Model model, @PageableDefault Pageable pageable) {
		model.addAttribute("page", brandService.findPage(pageable));
		return "admin/adminBrand";
	}

	@GetMapping("/update/{id}")
	public String update(@PathVariable("id") Integer id, Model model, @PageableDefault Pageable pageable) {
		model.addAttribute("brand", brandService.findByOne(id));
		return show(model, pageable);
	}

	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer id, Model model, @PageableDefault Pageable pageable) {
		brandService.deleteBrand(id);
		return "redirect:/admin/brand";
	}

	@PostMapping
	public String save(@ModelAttribute("brand") @Valid Brand brand, BindingResult br, Model model,
			@PageableDefault Pageable pageable, SessionStatus status) {
		if (br.hasErrors())
			return show(model, pageable);
		brandService.saveBrand(brand);
		return cancel(status);
	}

	@RequestMapping("/cancel")
	public String cancel(SessionStatus status) {
		status.setComplete();
		return "redirect:/admin/brand";
	}
}
