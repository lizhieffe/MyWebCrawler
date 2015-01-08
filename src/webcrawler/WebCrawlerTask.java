package webcrawler;

import java.net.URL;
import java.util.List;

public class WebCrawlerTask implements Runnable {
	
	private final TaskController controller;
	private static int globalCount = 0;
	private int id;
	
	public WebCrawlerTask(TaskController controller) {
		this.controller = controller;
		this.id = globalCount++;
	}
	
	@Override
	public void run() {
		while (true) {
			URL url = this.controller.getNextUrlToCraw();
			if (url == null) {
				finish();
				break;
			}
			
			System.out.println("[Task " + id + ", level " + controller.getCurrLevel() + "] is crawing: " + url.toString());
			List<URL> urlsFound = URLUtils.getContainedURL(url);
			this.controller.addUrlsToNextLeve(urlsFound);
		}
	}
	
	private void finish() {
		globalCount--;
		this.controller.taskFinished();
	}
}
