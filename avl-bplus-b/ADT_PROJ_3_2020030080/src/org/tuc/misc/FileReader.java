package org.tuc.misc;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileReader {
	
	private File file;
	private FileInputStream fin;
	private BufferedInputStream bin;
	private DataInputStream din;
	
	private int[] values;

	public FileReader(String filename) {
		file = new File(filename);
		
		try {
			fin = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		bin = new BufferedInputStream(fin);
		din = new DataInputStream(bin);

		int count = (int) (file.length() / 4);
		values = new int[count];
		for (int i = 0; i < count; i++) {
		    try {
				values[i] = din.readInt();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public int[] getValues() {
		return this.values;
	}
	
	public int fileSize() {
		return this.values.length;
	}
	
	public boolean isEmpty() { 
		return (values == null) ? true : false;
	}
	
	public void close() {
		
		try {
			din.close();
			bin.close();
			fin.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
