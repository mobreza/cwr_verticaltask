/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author HSOTELO
 */
public class Dialogs {
	public static File getFile(String msg) throws IOException {
		String a = read(msg);
		return new File(a);
	}

	public static File getDirectory(String msg) throws IOException {
		String a = read(msg);
		return new File(a);
	}

	public static void message(String msg) {
		System.out.println("\n" + msg);
	}

	public static String read(String msg) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("\n" + msg);
		return br.readLine();
	}

	public static int readInt(String msg) throws IOException {
		try {
			return Integer.parseInt(read(msg));
		} catch (NumberFormatException ex) {
			System.out.println(ex);
			return readInt(msg);
		}
	}
}
