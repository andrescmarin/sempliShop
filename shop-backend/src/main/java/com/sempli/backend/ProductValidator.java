package com.sempli.backend;

public class ProductValidator {
	private boolean compliance = true;
	public  ProductValidator(String[] productValues) {
		if(productValues != null) {
			if(productValues[0].isEmpty())  compliance = compliance & false;
			if(productValues[2].isEmpty() || Integer.valueOf(productValues[2]) < 0) compliance = compliance & false;			
		}else {
			compliance = compliance & false;
		}
	}
	public boolean isComplianing() {
		return compliance;
	}
}
