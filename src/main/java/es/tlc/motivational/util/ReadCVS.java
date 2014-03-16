package es.tlc.motivational.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadCVS {

	public static List<String[]> getFrases(String csvFile) {

		//String csvFile = "C:/Users/dlopezfe/Desktop/FrasesMotivacion.csv";
		BufferedReader br = null;
		ArrayList<String[]> lista = new ArrayList<String[]>();
		String line = "";
		String cvsSplitBy = "-";

		try {

			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] frase = line.split(cvsSplitBy);

				lista.add(frase);

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return lista;
	}

}
