package stockviewer.preferences;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.RadioGroupFieldEditor;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import stockviewer.Activator;

/**
 * This class represents a preference page that is contributed to the
 * Preferences dialog. By subclassing <samp>FieldEditorPreferencePage</samp>,
 * we can use the field support built into JFace that allows us to create a page
 * that is small and knows how to save, restore and apply itself.
 * <p>
 * This page is used to modify preferences only. They are stored in the
 * preference store that belongs to the main plug-in class. That way,
 * preferences can be accessed directly via the preference store.
 */

public class StockViewerPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	public StockViewerPreferencePage() {
		super(GRID);
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		// if (store.getString(HTTP_URL) == null ||
		// store.getString(HTTP_URL).length() < 1) {
		// store.setDefault(HTTP_URL, "http://61.172.207.163/list=");
		// }
		// if (store.getString(HTTP_PROXY_SERVER) == null ||
		// store.getString(HTTP_PROXY_SERVER).length() < 1) {
		// store.setDefault(HTTP_PROXY_SERVER, "10.10.175.240");
		// }
		// if (store.getString(HTTP_PROXY_PORT) == null ||
		// store.getString(HTTP_PROXY_PORT).length() < 1) {
		// store.setDefault(HTTP_PROXY_PORT, "8888");
		// }
		if (store.getString(MY_STOCKS) == null || store.getString(MY_STOCKS).length() < 1) {
			store.setDefault(MY_STOCKS, "000001,600320");
		}
		setPreferenceStore(store);

		setDescription("Config StockViewer definitions.");
	}

	/**
	 * Creates the field editors. Field editors are abstractions of the common
	 * GUI blocks needed to manipulate various types of preferences. Each field
	 * editor knows how to save and restore itself.
	 */
	public void createFieldEditors() {
		addField(new RadioGroupFieldEditor(EXCHANG_LOCATION, "Exchang &Location:", 1, new String[][] { { "&ShangHai", "sh" }, { "Sheng&Zhen", "s_sz" } },
				getFieldEditorParent(), true));
		addField(new StringFieldEditor(MY_STOCKS, "My &Default Stocks:", getFieldEditorParent()));
		// addField(new StringFieldEditor(HTTP_URL, "Default HTTP stock &URL:",
		// getFieldEditorParent()));
		// addField(new RadioGroupFieldEditor(PROXY_SET, "", 1, new String[][] {
		// { "Direct &connection to the Internet", "0" }, { "&Manual proxy
		// configuration:", "1" } },
		// getFieldEditorParent()));
		StringFieldEditor s = new StringFieldEditor(HTTP_PROXY_SERVER, "&HTTP Proxy Server:", getFieldEditorParent());
		// s.setEnabled(false, getFieldEditorParent());
		addField(s);
		addField(new StringFieldEditor(HTTP_PROXY_PORT, "&Port:", 8, getFieldEditorParent()));

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
	}

	/** 我的列表配置字段名 */
	public static final String MY_STOCKS = "FLD_MY_STOCKS_NAME";

	/** WEB接口URL配置字段名 */
	public static final String HTTP_URL = "FLD_URL_NAME";

	/** 代理服务器配置字段名 */
	public static final String HTTP_PROXY_SERVER = "FLD_HTTP_PROXY_SERVER_NAME";

	/** 代理服务器端口配置字段名 */
	public static final String HTTP_PROXY_PORT = "FLD_HTTP_PROXY_PORT_NAME";

	/** 股市地配置字段名 */
	public static final String EXCHANG_LOCATION = "FLD_EXCHANG_LOCATION_NAME";

	/** 代理配置字段名 */
	public static final String PROXY_SET = "FLD_PROXY_NAME";

}