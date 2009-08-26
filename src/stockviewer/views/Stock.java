/**********************************************************************
 *         File: Stock.java
 *      Creator: Evan.Kong
 *         Date: Mar 17, 2008
 *  Description: 
 *               It's just a Value Object.
 *              
 *
 * MODIFICATION DESCRIPTION
 *      
 * Name                 Date                Description 
 * ============         ============        ============
 * Evan.Kong			Mar 17, 2008		Created
 * 
 * *********************************************************************/
package stockviewer.views;

public class Stock {

	private static String TMP = "var hq_str_sh600320=\"振华港机,16.89,16.93,16.05,16.89,15.92,16.05,16.08,16448576,266404013,700,16.05,8094,16.03,52011,16.02,36400,16.01,73700,16.00,1000,16.08,10184,16.09,6400,16.10,17115,16.11,12734,16.12,2008-03-17,14:30:45\";";

	public Stock(String str) {
		int p = str.indexOf("=");
		String[] tmpStr = str.substring(p + 2, str.length() - 2).split(",");
		tmpStr = checkLength(tmpStr);
		this.stockNum = str.substring(p - 6, p);
		this.stockName = tmpStr[0];
		this.openPrice = Float.parseFloat(tmpStr[1]);
		this.colsePrice = Float.parseFloat(tmpStr[2]);
		this.price = Float.parseFloat(tmpStr[3]);
		this.highestPrice = Float.parseFloat(tmpStr[4]);
		this.lowestPrice = Float.parseFloat(tmpStr[5]);
		this.buyPrice = Float.parseFloat(tmpStr[6]);
		this.salePrice = Float.parseFloat(tmpStr[7]);
		this.dealAmount = Long.parseLong(tmpStr[8]);
		this.dealTotal = Float.parseFloat(tmpStr[9]);
		this.buy1stBookAmount = Long.parseLong(tmpStr[10]);
		this.buy1stPrice = Float.parseFloat(tmpStr[11]);
		this.buy2ndBookAmount = Long.parseLong(tmpStr[12]);
		this.buy2ndPrice = Float.parseFloat(tmpStr[13]);
		this.buy3rdBookAmount = Long.parseLong(tmpStr[14]);
		this.buy3rdPrice = Float.parseFloat(tmpStr[15]);
		this.buy4thBookAmount = Long.parseLong(tmpStr[16]);
		this.buy4thPrice = Float.parseFloat(tmpStr[17]);
		this.buy5thBookAmount = Long.parseLong(tmpStr[18]);
		this.buy5thPrice = Float.parseFloat(tmpStr[19]);
		this.sale1stAmount = Long.parseLong(tmpStr[20]);
		this.sale1stPrice = Float.parseFloat(tmpStr[21]);
		this.sale2ndAmount = Long.parseLong(tmpStr[22]);
		this.sale2ndPrice = Float.parseFloat(tmpStr[23]);
		this.sale3rdAmount = Long.parseLong(tmpStr[24]);
		this.sale3rdPrice = Float.parseFloat(tmpStr[25]);
		this.sale4thAmount = Long.parseLong(tmpStr[26]);
		this.sale4thPrice = Float.parseFloat(tmpStr[27]);
		this.sale5thAmount = Long.parseLong(tmpStr[28]);
		this.sale5thPrice = Float.parseFloat(tmpStr[29]);
		this.date = tmpStr[30];
		this.time = tmpStr[31].replaceAll("\"", "");

	}

	private String[] checkLength(String[] tmpStr) {
		if (tmpStr.length == 32) {
			return tmpStr;
		}
		String[] t = new String[32];
		for (int i = 0; i < tmpStr.length && i < 32; i++) {
			t[i] = tmpStr[i];
		}
		for (int i = tmpStr.length; i < 32; i++) {
			t[i] = "0";
		}
		return t;

	}

	public void show() {
		System.out.println(this.stockName + "[" + this.getStockNum() + "]");
		System.out.println("=============================");
		System.out.println("当前价：" + this.getPrice());
		System.out.println("卖二：" + this.getSale2ndPrice() + " ,量：" + this.getSale2ndAmount());
		System.out.println("卖一：" + this.getSale1stPrice() + " ,量：" + this.getSale1stAmount());
		System.out.println("买一：" + this.getBuy1stPrice() + " ,量：" + this.getBuy1stBookAmount());
		System.out.println("买二：" + this.getBuy2ndPrice() + " ,量：" + this.getBuy2ndBookAmount());
	}

	public static void main(String[] arg) {
		System.out.println(TMP.substring(21, TMP.length() - 2));
		String[] a = TMP.substring(21, TMP.length() - 2).split(",");
		System.out.println(a.length);

		Stock a1 = new Stock(TMP);
		a1.show();

	}

	private String stockNum;

	private String stockName;

	private float openPrice;

	private float colsePrice;

	private float price;

	private float highestPrice;

	private float lowestPrice;

	private float buyPrice;

	private float salePrice;

	private long dealAmount;

	private float dealTotal;

	private long buy1stBookAmount;

	private float buy1stPrice;

	private long buy2ndBookAmount;

	private float buy2ndPrice;

	private long buy3rdBookAmount;

	private float buy3rdPrice;

	private long buy4thBookAmount;

	private float buy4thPrice;

	private long buy5thBookAmount;

	private float buy5thPrice;

	private long sale1stAmount;

	private float sale1stPrice;

	private long sale2ndAmount;

	private float sale2ndPrice;

	private long sale3rdAmount;

	private float sale3rdPrice;

	private long sale4thAmount;

	private float sale4thPrice;

	private long sale5thAmount;

	private float sale5thPrice;

	private String date;

	private String time;

	public String getStockNum() {
		return stockNum;
	}

	public void setStockNum(String stockNum) {
		this.stockNum = stockNum;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public float getOpenPrice() {
		return openPrice;
	}

	public void setOpenPrice(float openPrice) {
		this.openPrice = openPrice;
	}

	public float getColsePrice() {
		return colsePrice;
	}

	public void setColsePrice(float colsePrice) {
		this.colsePrice = colsePrice;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getHighestPrice() {
		return highestPrice;
	}

	public void setHighestPrice(float highestPrice) {
		this.highestPrice = highestPrice;
	}

	public float getLowestPrice() {
		return lowestPrice;
	}

	public void setLowestPrice(float lowestPrice) {
		this.lowestPrice = lowestPrice;
	}

	public float getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(float buyPrice) {
		this.buyPrice = buyPrice;
	}

	public float getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(float salePrice) {
		this.salePrice = salePrice;
	}

	public long getDealAmount() {
		return dealAmount / 100;
	}

	public void setDealAmount(long dealAmount) {
		this.dealAmount = dealAmount;
	}

	public float getDealTotal() {
		return dealTotal / 10000;
	}

	public void setDealTotal(float dealTotal) {
		this.dealTotal = dealTotal;
	}

	public long getBuy1stBookAmount() {
		return buy1stBookAmount / 100;
	}

	public void setBuy1stBookAmount(long buy1stBookAmount) {
		this.buy1stBookAmount = buy1stBookAmount;
	}

	public float getBuy1stPrice() {
		return buy1stPrice;
	}

	public void setBuy1stPrice(float buy1stPrice) {
		this.buy1stPrice = buy1stPrice;
	}

	public long getBuy3rdBookAmount() {
		return buy3rdBookAmount / 100;
	}

	public void setBuy3rdBookAmount(long buy3rdBookAmount) {
		this.buy3rdBookAmount = buy3rdBookAmount / 100;
	}

	public float getBuy3rdPrice() {
		return buy3rdPrice;
	}

	public void setBuy3rdPrice(float buy3rdPrice) {
		this.buy3rdPrice = buy3rdPrice;
	}

	public long getBuy4thBookAmount() {
		return buy4thBookAmount / 100;
	}

	public void setBuy4thBookAmount(long buy4thBookAmount) {
		this.buy4thBookAmount = buy4thBookAmount / 100;
	}

	public float getBuy4thPrice() {
		return buy4thPrice;
	}

	public void setBuy4thPrice(float buy4thPrice) {
		this.buy4thPrice = buy4thPrice;
	}

	public float getSale1stPrice() {
		return sale1stPrice;
	}

	public void setSale1stPrice(float sale1stPrice) {
		this.sale1stPrice = sale1stPrice;
	}

	public long getBuy2ndBookAmount() {
		return buy2ndBookAmount / 100;
	}

	public void setBuy2ndBookAmount(long buy2ndBookAmount) {
		this.buy2ndBookAmount = buy2ndBookAmount;
	}

	public float getBuy2ndPrice() {
		return buy2ndPrice;
	}

	public void setBuy2ndPrice(float buy2ndPrice) {
		this.buy2ndPrice = buy2ndPrice;
	}

	public long getSale1stAmount() {
		return sale1stAmount / 100;
	}

	public void setSale1stAmount(long sale1stAmount) {
		this.sale1stAmount = sale1stAmount;
	}

	public long getSale2ndAmount() {
		return sale2ndAmount / 100;
	}

	public void setSale2ndAmount(long sale2ndAmount) {
		this.sale2ndAmount = sale2ndAmount;
	}

	public float getSale2ndPrice() {
		return sale2ndPrice;
	}

	public void setSale2ndPrice(float sale2ndPrice) {
		this.sale2ndPrice = sale2ndPrice;
	}

	public long getSale3rdAmount() {
		return sale3rdAmount / 100;
	}

	public void setSale3rdAmount(long sale3rdAmount) {
		this.sale3rdAmount = sale3rdAmount;
	}

	public float getSale3rdPrice() {
		return sale3rdPrice;
	}

	public void setSale3rdPrice(float sale3rdPrice) {
		this.sale3rdPrice = sale3rdPrice;
	}

	public long getSale4thAmount() {
		return sale4thAmount / 100;
	}

	public void setSale4thAmount(long sale4thAmount) {
		this.sale4thAmount = sale4thAmount;
	}

	public float getSale4thPrice() {
		return sale4thPrice;
	}

	public void setSale4thPrice(float sale4thPrice) {
		this.sale4thPrice = sale4thPrice;
	}

	public long getBuy5thBookAmount() {
		return buy5thBookAmount / 100;
	}

	public void setBuy5thBookAmount(long buy5thBookAmount) {
		this.buy5thBookAmount = buy5thBookAmount;
	}

	public float getBuy5thPrice() {
		return buy5thPrice;
	}

	public void setBuy5thPrice(float buy5thPrice) {
		this.buy5thPrice = buy5thPrice;
	}

	public long getSale5thAmount() {
		return sale5thAmount / 100;
	}

	public void setSale5thAmount(long sale5thAmount) {
		this.sale5thAmount = sale5thAmount;
	}

	public float getSale5thPrice() {
		return sale5thPrice;
	}

	public void setSale5thPrice(float sale5thPrice) {
		this.sale5thPrice = sale5thPrice;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getFullDate() {
		return this.date + " " + this.time;
	}

}
