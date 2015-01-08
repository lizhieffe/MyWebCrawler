package webcrawler;

import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HashURLHistoryChecker implements URLHistoryChecker {

	private Set<URL> history = new HashSet<URL>();
	
	@Override
	synchronized public void add(URL url) {
		history.add(url);
	}

	@Override
	synchronized public void addAll(List<URL> urls) {
		history.addAll(urls);
	}
	
	@Override
	synchronized public boolean contains(URL url) {
		return history.contains(url);
	}
	
	/*
	 * (non-Javadoc)
	 * @see webcrawler.URLHistoryChecker#contains(java.util.Set)
	 * return true only if none of the urls already exists
	 */
	@Override
	synchronized public boolean contains(List<URL> urls) {
		for (URL url : urls) {
			if (history.contains(url))
				return true;
		}
		return false;
	}

}
