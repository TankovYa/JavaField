package main;

import java.io.IOException;
import java.nio.CharBuffer;

public class AdapterRandomChar extends RandomChar implements Readable{
	private int count;
	
	public AdapterRandomChar(int count) {
		this.count = count;
	}
	
	@Override
	public int read(CharBuffer cb) throws IOException {
		if(count--==0) {
			return -1;
		}
		cb.append(next());
		return 1;
	}

}
