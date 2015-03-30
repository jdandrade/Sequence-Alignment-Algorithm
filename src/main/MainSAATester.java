package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import api.algorithm.SequenceAlignmentAlgorithm;

public class MainSAA {

	public static void main(String[] args) {
		MainSAA saa = new MainSAA();
		saa.init();
	}

	private void init() {
		File f = new File("./input.txt");
		BufferedReader reader = null;
		String sequence1 = null;
		String sequence2 = null;
		try {
			reader = new BufferedReader(new FileReader(f));
			String text = null;
			int i = 1;
			while ((text = reader.readLine()) != null) {

				if (i == 1)
					sequence1 = text;
				else
					sequence2 = text;

				i++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null)
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		System.out.println(">> SEQUENCE ALIGNMENT ALGORITHM <<\n");
		System.out.println(">> INPUT:\n\n" +sequence1);
		System.out.println(sequence2 + "\n");
		
		String[] SAA = SequenceAlignmentAlgorithm.ALIGN(sequence1, sequence2);
		
		System.out.println("\n>> OUTPUT:\n");
		for(String s : SAA){
			System.out.println(s);
		}
	}
}
