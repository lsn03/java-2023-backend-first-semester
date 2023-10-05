package edu.hw1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Task1 {
	private final static Logger LOGGER = LogManager.getLogger();

	@SuppressWarnings("MagicNumber")
	public int minutesToSeconds(String string) {

		boolean checkThatIsNotNullOrEmpty = string == null || string.isEmpty();

		try {

			if (!checkThatIsNotNullOrEmpty) {
				String[] parts = string.split(":");
				boolean checkThatFormatIsGood = parts[0].length() > 1 && parts[1].length() == 2;

				if (checkThatFormatIsGood) {

					int mm = Integer.parseInt(parts[0]);
					int ss = Integer.parseInt(parts[1]);


					if (ss < 60 && ss >= 0 && mm > 0) {
						return Math.multiplyExact(mm, 60) + ss;
					}

				}
			}


		} catch (Exception ex) {
			LOGGER.info(ex.getMessage());
		}
		return -1;


	}
}
