package common;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GeneralSettings {
	public static final String LOG_NAME_SUFFIX = "_log.log";
	public static final String logFilePath = System.getProperty("user.dir") + System.getProperty("file.separator")
			+ "logs";
	public static File logFile;
	public static BufferedWriter logWriter;

	public static boolean LOG_FILE = true;

	public static void initLog() {
		if (LOG_FILE) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd_HH.mm.ss");
			LocalDateTime now = LocalDateTime.now();
			System.out.println(dtf.format(now));

			File logDirectory = new File(logFilePath);
			if (!logDirectory.exists()) {
				logDirectory.mkdir();
			}

			logFile = new File(logFilePath + System.getProperty("file.separator") + dtf.format(now) + LOG_NAME_SUFFIX);

			try {
				if (logFile.createNewFile()) {
					System.out.println(
							Features.VERBOSE_STR + "Created log file in " + logFile.getAbsolutePath().toString());
				}
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println(
						Features.VERBOSE_STR + "Failed to create log file in " + logFile.getAbsolutePath().toString());
			}
		}

	}
}
