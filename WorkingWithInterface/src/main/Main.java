package main;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		printChar();
		System.out.println();
		printScanner();
	}
	
	private static void printScanner() {
		Scanner scanner= new Scanner(new AdapterRandomChar(10));
		while (scanner.hasNext()) {
			System.out.print(scanner.next());
		}
	}
	
	private static void printChar() {
		RandomChar randomChar = new RandomChar();
		for(int i = 0; i < 10; i++) {
			System.out.print(randomChar.next());
		}
	}

}
