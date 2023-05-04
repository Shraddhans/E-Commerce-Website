package com.shraddha.website.global;

import java.util.ArrayList;
import java.util.List;

import com.shraddha.website.model.Product;

public class GlobalData {
	public static List<Product> cart;
	static {
		cart = new ArrayList<Product>();
	}

}
