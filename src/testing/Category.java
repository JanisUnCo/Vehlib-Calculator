package testing;

import java.util.ArrayList;
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
	
	static ArrayList<Category> all = new ArrayList<Category>();
	
	
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
		this.name = name.substring(1) + number+" (" +(int) minPrice+ "-" +(int) maxPrice+")";
		all.add(this);
		System.out.println("Initialized " +this.name);
	}
	
	public static int getEmptyPlaceInAll() {
		int number = 1;
		try {
			for (Category c : all) {
				int _n = Integer.parseInt(c.name.substring(8, 10).trim());
				if (number < _n) {
					return number;
				}
				number++;
			}
		} catch (Exception e) {
			System.out.println("The developer of this method is a dumbass.");
			System.out.println(e);
			System.out.println(e.getStackTrace()[0]);
		}
		return Category.all.size()+1;
	}

	public static void printAll() {
		System.out.println(all.toString());
	}
	
	public String toString() {
		return this.name;
	}
}
