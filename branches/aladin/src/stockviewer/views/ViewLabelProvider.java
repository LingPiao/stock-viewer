/**********************************************************************
 *         File: MyLabelProvider.java
 *      Creator: Evan.Kong
 *         Date: Mar 20, 2008
 *  Description: 
 *               ViewLabelProvider to show the table data.
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

import java.text.DecimalFormat;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

/**
 * ±êÇ©Æ÷
 */
public class ViewLabelProvider extends LabelProvider implements ITableLabelProvider {

	public String getColumnText(Object element, int col) {
		DecimalFormat nf = new DecimalFormat("#,##0.00");
		String r = "";
		Stock s = (Stock) element;
		switch (col) {
		case 0:
			r = s.getStockNum();
			break;
		case 1:
			r = s.getStockName();
			break;
		case 2:
			r = nf.format(s.getOpenPrice());
			break;
		case 3:
			r = nf.format(s.getColsePrice());
			break;
		case 4:
			r = nf.format(s.getPrice());
			break;
		case 5:
			r = nf.format(s.getHighestPrice());
			break;
		case 6:
			r = nf.format(s.getLowestPrice());
			break;
		case 7:
			r = nf.format(s.getBuyPrice());
			break;
		case 8:
			r = nf.format(s.getSalePrice());
			break;
		case 9:
			r = nf.format(s.getDealAmount());
			break;
		case 10:
			r = nf.format(s.getDealTotal());
			break;
		case 11:
			r = String.valueOf(s.getBuy1stBookAmount());
			break;
		case 12:
			r = nf.format(s.getBuy1stPrice());
			break;
		case 13:
			r = String.valueOf(s.getBuy2ndBookAmount());
			break;
		case 14:
			r = nf.format(s.getBuy2ndPrice());
			break;
		case 15:
			r = String.valueOf(s.getBuy3rdBookAmount());
			break;
		case 16:
			r = nf.format(s.getBuy3rdPrice());
			break;
		case 17:
			r = String.valueOf(s.getBuy4thBookAmount());
			break;
		case 18:
			r = nf.format(s.getBuy4thPrice());
			break;
		case 19:
			r = String.valueOf(s.getBuy5thBookAmount());
			break;
		case 20:
			r = nf.format(s.getBuy5thPrice());
			break;
		case 21:
			r = String.valueOf(s.getSale1stAmount());
			break;
		case 22:
			r = nf.format(s.getSale1stPrice());
			break;
		case 23:
			r = String.valueOf(s.getSale2ndAmount());
			break;
		case 24:
			r = nf.format(s.getSale2ndPrice());
			break;
		case 25:
			r = String.valueOf(s.getSale3rdAmount());
			break;
		case 26:
			r = nf.format(s.getSale3rdPrice());
			break;
		case 27:
			r = String.valueOf(s.getSale4thAmount());
			break;
		case 28:
			r = nf.format(s.getSale4thPrice());
			break;
		case 29:
			r = String.valueOf(s.getSale5thAmount());
			break;
		case 30:
			r = nf.format(s.getSale5thPrice());
			break;
		case 31:
			r = s.getFullDate();
			break;

		}
		return r;
	}

	public Image getColumnImage(Object element, int columnIndex) {
		return null;
	}
}