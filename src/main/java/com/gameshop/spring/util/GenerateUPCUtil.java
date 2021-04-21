package com.gameshop.spring.util;

import java.util.UUID;

public class GenerateUPCUtil {

	public static int generateUPCID() {

		Long id = 0L;
		UUID temp = UUID.randomUUID();

		id = temp.getMostSignificantBits();
		if (id < 0) {
			id = Math.abs(id);
		}

		id = Long.parseLong(id.toString().substring(0, 9));

		int upc = Integer.valueOf(id.toString());
		return upc;
	}

}
