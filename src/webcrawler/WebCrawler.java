package webcrawler;

import java.net.URL;

public class WebCrawler {
	
	URLQueue urlQueue = new URLQueue();
	int maxThread;
	
	public WebCrawler(URLQueue urlQueue, int maxThread) {
		this.urlQueue = urlQueue;
		this.maxThread = maxThread;
	}
	
	public static void main(String[] args) {
		try {
			int maxLevel = 2;
			int maxThreads = 5;
			if (args.length == 4) {
				maxThreads = Integer.parseInt(args[3]);
			}
			if (args.length >= 3) {
				maxLevel = Integer.parseInt(args[2]);
			}
			if (args.length >= 2) {
				URLQueue q = new URLQueue();
				q.push(new URL(args[0]));
				new WebCrawler(q, maxThreads);
				return;
			}
		} catch (Exception e) {
			System.err.println("An error occured: ");
			e.printStackTrace();
			System.exit(0);
		}
		System.err.println("Usage: java WSDLCrawler <url> <filenamePrefix> [<maxLevel> [<maxThreads>]]");
		System.err.println("Crawls the web for WSDL descriptions.");
	}
}
