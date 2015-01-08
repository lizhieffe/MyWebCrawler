package webcrawler;

import java.net.URL;
import java.util.List;

public interface URLHistoryChecker {
	public void add(URL url);
	public void addAll(List<URL> urls);
	public boolean contains(URL url);
	public boolean contains(List<URL> urls);
}
