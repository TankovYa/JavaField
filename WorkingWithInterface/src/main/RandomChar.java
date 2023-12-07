package main;

import java.util.Random;

public class RandomChar {
	private static Random random = new Random();
	public char next() {
		int x = random.nextInt(94)+32;
		return (char) (x);
	}
}
