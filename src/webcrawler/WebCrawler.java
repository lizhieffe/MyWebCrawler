package webcrawler;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class WebCrawler {
	
	TaskController taskController;
	
	public WebCrawler(List<URL> startUrls, int maxThread, int maxLevel) {
		URLQueue urlQueue = new URLQueue();
		urlQueue.pushAll(startUrls, 0);
		this.taskController = new TaskController(this, urlQueue, maxThread, maxLevel);
	}
	
	public void start() {
//		List<URL> result = URLUtils.getContainedURL(urlQueue.pop(0));
//		for (URL url : result)
//			System.out.println(url);
		
		
		this.taskController.startTasks();
	}
	
	void stop() {
		System.out.println("WebCrawler stop");
		System.exit(1);
	}
	
	public static void main(String[] args) {
		try {
			int maxLevel = 2;
			int maxThreads = 5;
			if (args.length == 3) {
				maxThreads = Integer.parseInt(args[2]);
			}
			if (args.length >= 2) {
				maxLevel = Integer.parseInt(args[1]);
			}
			if (args.length >= 1) {
				List<URL> startUrls = new ArrayList<URL>();
				startUrls.add(new URL(args[0]));
				WebCrawler crawler = new WebCrawler(startUrls, maxThreads, maxLevel);
				crawler.start();
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
