package webcrawler;

import java.net.URL;
import java.util.LinkedList;

public class URLQueue {
	private LinkedList<URL> queue = new LinkedList<URL>();
	
	synchronized public URL pop() {
		return queue.pop();
	}
	
	synchronized public void push(URL url) {
		queue.add(url);
	}
}
