/**********************************************************************
 *         File: Sorter.java
 *      Creator: Evan.Kong
 *         Date: Mar 27, 2008
 *  Description: 
 *               File Desc.
 *              
 *
 * MODIFICATION DESCRIPTION
 *      
 * Name                 Date                Description 
 * ============         ============        ============
 * Evan.Kong			Mar 27, 2008			Created
 * 
 * *********************************************************************/
package stockviewer.views;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;

public class Sorter extends ViewerSorter {
	private static final int OPEN_PRICE = 2;

	private static final int STOCK_NO = 1000;

	private int sortType;

	public static final Sorter OPEN_PRICE_ASC = new Sorter(OPEN_PRICE);

	public static final Sorter OPEN_PRICE_DESC = new Sorter(-OPEN_PRICE);

	public static final Sorter STOCK_NO_ASC = new Sorter(STOCK_NO);

	public static final Sorter STOCK_NO_DESC = new Sorter(-STOCK_NO);

	private Sorter(int type) {
		this.sortType = type;
	}

	public int compare(Viewer viewer, Object e1, Object e2) {
		Stock s1 = (Stock) e1;
		Stock s2 = (Stock) e2;
		switch (sortType) {
		case OPEN_PRICE: {
			Float l1 = new Float(s1.getOpenPrice());
			Float l2 = new Float(s2.getOpenPrice());
			return l1.compareTo(l2);
		}
		case -OPEN_PRICE: {
			Float l1 = new Float(s1.getOpenPrice());
			Float l2 = new Float(s2.getOpenPrice());
			return l2.compareTo(l1);
		}
		case STOCK_NO: {
			Long l1 = new Long(s1.getStockNum());
			Long l2 = new Long(s2.getStockNum());
			return l1.compareTo(l2);
		}
		case -STOCK_NO: {
			Long l1 = new Long(s1.getStockNum());
			Long l2 = new Long(s2.getStockNum());
			return l2.compareTo(l1);
		}
		}
		return 0;
	}

}
