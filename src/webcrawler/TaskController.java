package webcrawler;

import java.net.URL;
import java.util.List;

public class TaskController {
	private WebCrawler webCrawler;
	private URLQueue urlQueue = new URLQueue();
	private int maxThread;
	private int maxLevel;
	private int currLevel;
	private int numRunningTasks;
	
	
	public TaskController(WebCrawler webCrawler, URLQueue urlQueue, int maxThread, int maxLevel) {
		this.webCrawler = webCrawler;
		this.urlQueue = urlQueue;
		this.maxThread = maxThread;
		this.maxLevel = maxLevel;
		this.currLevel = 0;
		numRunningTasks = 0;
	}
	
	/*
	 * start crawling tasks on a new level
	 */
	public void startTasks() {
		if (currLevel >= maxLevel) {
			System.out.println("All levels of tasks have been finished");
			this.webCrawler.stop();
		}
		
		if (urlQueue.getUrlNum(currLevel) <= 0) {
			System.out.println("There is no URL to craw on level " + currLevel);
			this.webCrawler.stop();
		}
		
		int numThread = maxThread;
		if (numThread > urlQueue.getUrlNum(currLevel))
			numThread = urlQueue.getUrlNum(currLevel);
		
		for (int i = 0; i < numThread; i++) {
			WebCrawlerTask task = new WebCrawlerTask(this);
			new Thread(task).start();
			numRunningTasks++;
		}
	}
	
	synchronized public void taskFinished() {
		if (--numRunningTasks == 0) {
			/*
			 * start tasks on new level
			 */
			currLevel++;
			startTasks();
		}
	}
	
	public int getCurrLevel() {
		return currLevel;
	}
	
	public URL getNextUrlToCraw() {
		return this.urlQueue.pop(currLevel);
	}
	
	public void addUrlsToNextLeve(List<URL> urls) {
		this.urlQueue.pushAll(urls, currLevel + 1);
	}
}
