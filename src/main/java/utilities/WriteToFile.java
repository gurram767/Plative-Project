package utilities;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteToFile {

	private static final String FILENAME = "./src/main/resources/ConfigFiles/TestStatusForUI.txt";

	public static void writetoFile(String content) {

		BufferedWriter bw = null;
		FileWriter fw = null;

		try {


			fw = new FileWriter(FILENAME);
			bw = new BufferedWriter(fw);
			bw.write(content);

			System.out.println("Done");

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}

	}

}

