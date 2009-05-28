package br.mackenzie.tgi.log;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TSLog {
	public static void txt(String logText) {
		try {

			String outputFileName = "TSLog.txt";
			

			FileWriter fileWriter = new FileWriter(outputFileName);
			
			BufferedWriter outputStream = new BufferedWriter(fileWriter);
			
			outputStream.append(logText);

			outputStream.close();

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}
}
