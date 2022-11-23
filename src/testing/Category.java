package testing;

import java.util.HashMap;

public class Category {
	String name = "[Category ";
	double weight, 
		   priceKoef,
		   minPrice,
		   maxPrice,
		   minTax,
		   maxTax,
		   minTopSpeed,
		   maxTopSpeed,
		   minAcc,
		   maxAcc,
		   minInertia,
		   maxInertia;
	
	public Category(int number, HashMap<String, Double> valueMap) {
		try {
			this.weight = valueMap.get("weight");
			this.priceKoef = valueMap.get("priceKoef");
			this.minPrice = valueMap.get("minPrice");
			this.maxPrice = valueMap.get("maxPrice");
			this.minTax = valueMap.get("minTax");
			this.maxTax = valueMap.get("maxTax");
			this.minTopSpeed = valueMap.get("minTopSpeed");
			this.maxTopSpeed = valueMap.get("maxTopSpeed");
			this.minAcc = valueMap.get("minAcc");
			this.maxAcc = valueMap.get("maxAcc");
			this.minInertia = valueMap.get("minInertia");
			this.maxInertia	= valueMap.get("maxInertia");
		} catch (Exception e) {
			System.out.println("A value, probably, is missing in the passed Category map!");
			return;
		}
		this.name = name + number+" - (" +(int) minPrice+ "-" +(int) maxPrice+")]";
	}

	public String toString() {
		return this.name;
	}
}
