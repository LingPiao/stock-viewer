/**********************************************************************
 *         File: StockFactory.java
 *      Creator: Evan.Kong
 *         Date: Mar 20, 2008
 *  Description: 
 *            Load datas from Sina's Stock interface and create Stock objects.
 *              
 *
 * MODIFICATION DESCRIPTION
 *      
 * Name                 Date                Description 
 * ============         ============        ============
 * Evan.Kong			Mar 20, 2008		Created
 * 
 * *********************************************************************/
package stockviewer.views;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.preference.IPreferenceStore;

import stockviewer.Activator;
import stockviewer.preferences.StockViewerPreferencePage;

public class StockFactory {

	private static final String DEFAULT_STOCK_LOCATION = "sh";
	private static final String DEFAULT_HTTP_INTERFACE = "http://61.172.207.163/list=";

	public static List getStocks() {
		return parseStocks(defaultStocks());
	}

	private static List parseStocks(String str) {
		List stocks = new ArrayList();
		String[] strs = getStockStrings(str).split("\n");
		for (int i = 0; i < strs.length; i++) {
			Stock stock = new Stock(strs[i]);
			stocks.add(stock);
		}
		return stocks;
	}

	public static List getStock(String str) {
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		String l = store.getString(StockViewerPreferencePage.EXCHANG_LOCATION);
		if (l == null || l.length() < 1)
			l = DEFAULT_STOCK_LOCATION;
		return parseStocks(l + str);
	}

	public static List getStocks(Object o) {
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		String l = store.getString(StockViewerPreferencePage.EXCHANG_LOCATION);
		if (l == null || l.length() < 1)
			l = DEFAULT_STOCK_LOCATION;
		List cstocks = (List) o;
		StringBuffer sb = new StringBuffer();
		for (Iterator it = cstocks.iterator(); it.hasNext();) {
			Stock st = (Stock) it.next();
			sb.append(l + st.getStockNum());
			if (it.hasNext())
				sb.append(",");
		}
		return parseStocks(sb.toString());

	}

	private static String getStockStrings(String stock) {
		StringBuffer sb = new StringBuffer();
		try {
			URL url = getURL(stock);
			URLConnection conn = url.openConnection();
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null)
				sb.append(line + "\n");
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
			// System.out.println("IOException when connecting to server");
		}
		return sb.toString();
	}

	private static String defaultStocks() {
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		String l = store.getString(StockViewerPreferencePage.EXCHANG_LOCATION);
		String s = store.getString(StockViewerPreferencePage.MY_STOCKS);
		s = l + s;// 在最开始地方补上'sh'or's_sz'
		s = s.replaceAll(",", "," + l);
		return s;
	}

	private static String getHTTPString(String stock) {
		String u = "";
		// IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		// String h = store.getString(StockViewerPreferencePage.HTTP_URL);
		u = DEFAULT_HTTP_INTERFACE + stock;
		// System.out.println("URL:" + u);
		// return URLSTRING + MYSTOCKS;
		return u;
	}

	private static URL getURL(String stock) {
		URL url = null;
		try {
			IPreferenceStore store = Activator.getDefault().getPreferenceStore();

			// System.out.println("proxyServer:" +
			// store.getString(StockViewerPreferencePage.HTTP_PROXY_SERVER));
			// System.out.println("port:" +
			// store.getString(StockViewerPreferencePage.HTTP_PROXY_PORT));

			// have a proxy server
			if (store.getString(StockViewerPreferencePage.HTTP_PROXY_SERVER).length() > 5
					&& store.getString(StockViewerPreferencePage.HTTP_PROXY_PORT).length() > 1) {
				url = new URL("HTTP", "10.10.175.240", 8888, getHTTPString(stock));
			} else {// no proxy
				url = new URL(getHTTPString(stock));
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
			// System.out.println("Unable to connect to URL: " +
			// getHTTPString(stock));
		}
		return url;
	}

}
