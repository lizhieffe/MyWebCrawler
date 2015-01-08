package webcrawler;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;

public class URLQueue {
	private LinkedList<URL> evenQueue = new LinkedList<URL>();
	private LinkedList<URL> oddQueue = new LinkedList<URL>();
	URLHistoryChecker historyChecker = new HashURLHistoryChecker();
	
	synchronized public URL pop(int level) {
		if (level % 2 == 0 && evenQueue.size() > 0)
			return evenQueue.removeFirst();
		if (level % 2 == 1 && oddQueue.size() > 0)
			return oddQueue.removeFirst();
		return null;
	}
	
	synchronized public void push(URL url, int level) {
		if (url == null || historyChecker.contains(url))
			return;
		if (level % 2 == 0)
			evenQueue.add(url);
		if (level % 2 == 1)
			oddQueue.add(url);
		historyChecker.add(url);
	}
	
	synchronized public void pushAll(List<URL> urls, int level) {
		if (urls == null || historyChecker.contains(urls))
			return;
		if (level % 2 == 0)
			evenQueue.addAll(urls);
		if (level % 2 == 1)
			oddQueue.addAll(urls);
	}
	
	synchronized public int getUrlNum(int level) {
		if (level % 2 == 0)
			return evenQueue.size();
		if (level % 2 == 1)
			return oddQueue.size();
		return 0;
	}
}
