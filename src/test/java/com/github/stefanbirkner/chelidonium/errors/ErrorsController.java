package com.github.stefanbirkner.chelidonium.errors;

import static org.springframework.validation.ValidationUtils.rejectIfEmpty;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorsController {

	@RequestMapping("/errors/singleError.html")
	public void singleError(@ModelAttribute Model model, BindingResult result) {
		rejectIfEmpty(result, "a", "test.a");
	}

	@RequestMapping("/errors/twoErrors.html")
	public void twoErrors(@ModelAttribute Model model, BindingResult result) {
		rejectIfEmpty(result, "a", "test.a");
		rejectIfEmpty(result, "b", "test.b");
	}

	@RequestMapping("/errors/block.html")
	public void block(@ModelAttribute Model model, BindingResult result) {
		rejectIfEmpty(result, "a", "test.a");
	}

	@RequestMapping("/errors/closeable.html")
	public void closeable(@ModelAttribute Model model, BindingResult result) {
		rejectIfEmpty(result, "a", "test.a");
	}

	public static class Model {
		private String a, b;

		public String getA() {
			return a;
		}

		public void setA(String a) {
			this.a = a;
		}

		public String getB() {
			return b;
		}

		public void setB(String b) {
			this.b = b;
		}
	}
}
