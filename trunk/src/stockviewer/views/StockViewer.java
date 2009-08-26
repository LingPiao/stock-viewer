package stockviewer.views;

import java.util.List;
import java.util.regex.Pattern;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.part.ViewPart;

public class StockViewer extends ViewPart {
	private TableViewer viewer;

	private Action refresh;

	private Action reload;

	private List dataList;

	private String inputNum = "";

	private long lastKeyPressed = 0;

	private String nextStockNum = "";

	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
	public void createPartControl(Composite parent) {

		parent.setLayout(new FillLayout(512));
		viewer = new TableViewer(parent, SWT.BORDER | SWT.FULL_SELECTION);
		Table table = viewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		String[] colNames = new String[] { "编号", "名称", "开盘价", "收盘价", "当前价", "最高价", "最低价", "竞买价", "竞卖价", "成交量", "成交额(万)", "买一量", "买一价", "买二量", "买二价", "买三量",
				"买三价", "买四量", "买四价", "买五量", "买五价", "卖一量", "卖一价", "卖二量", "卖二价", "卖三量", "卖三价", "卖四量", "卖四价", "卖五量", "卖五价", "时间" };

		for (int i = 0; i < colNames.length; i++) {
			// TableColumn tcID = new TableColumn(table, SWT.LEFT);
			TableColumn tcID = new TableColumn(table, SWT.RIGHT);
			if (i == 0) {
				tcID.addSelectionListener(new SelectionAdapter() {
					boolean asc = true;
					public void widgetSelected(SelectionEvent e) {
						viewer.setSorter(asc ? Sorter.STOCK_NO_ASC : Sorter.STOCK_NO_DESC);
						asc = !asc;
					}
				});
			} else if (i == 2) {
				tcID.addSelectionListener(new SelectionAdapter() {
					boolean asc = true;
					public void widgetSelected(SelectionEvent e) {
						viewer.setSorter(asc ? Sorter.OPEN_PRICE_ASC : Sorter.OPEN_PRICE_DESC);
						asc = !asc;
					}
				});
			}
			if (i == colNames.length - 1) {
				tcID.setWidth(130);
			} else {
				tcID.setWidth(60);
			}
			tcID.setText(colNames[i]);
		}
		dataList = StockFactory.getStocks();
		viewer.setContentProvider(new ViewContentProvider());
		viewer.setLabelProvider(new ViewLabelProvider());
		viewer.setInput(dataList);

		viewer.getTable().addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				// showMessage(e.keyCode + "," + e.character);
				addNumKey(String.valueOf(e.character));
			}
		});

		makeActions();
		hookContextMenu();
		contributeToActionBars();
	}

	private void reloadStocks() {
		dataList = StockFactory.getStocks();
		refreshTableViewer();
	}

	private void refreshStocks() {
		dataList = StockFactory.getStocks(viewer.getInput());
		refreshTableViewer();
	}

	private void quickView() {
		dataList = StockFactory.getStock(this.nextStockNum);
		refreshTableViewer();
	}

	private void refreshTableViewer() {
		viewer.setInput(dataList);
		viewer.refresh();
	}

	private void addNumKey(String k) {
		// System.out.println("key is :" + k);
		if (this.inputNum.length() <= 0)
			this.lastKeyPressed = System.currentTimeMillis();
		if (System.currentTimeMillis() - this.lastKeyPressed < 2000) {
			Pattern p_num = Pattern.compile("([0-9]){1}");
			if (p_num.matcher(k).find()) {
				this.lastKeyPressed = System.currentTimeMillis();
				this.inputNum += k;
				if (this.inputNum.length() == 6) {
					this.nextStockNum = this.inputNum;
					this.inputNum = "";
					quickView();
					// System.out.println("nextStockNum :" + nextStockNum +
					// ",inputNum:" + inputNum);
				} else {
					return;
				}
			}
		}
		this.inputNum = "";
	}

	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				StockViewer.this.fillContextMenu(manager);
			}
		});
		Menu menu = menuMgr.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuMgr, viewer);
	}

	private void contributeToActionBars() {
		IActionBars bars = getViewSite().getActionBars();
		fillLocalPullDown(bars.getMenuManager());
		fillLocalToolBar(bars.getToolBarManager());
	}

	private void fillLocalPullDown(IMenuManager manager) {
		manager.add(refresh);
		manager.add(new Separator());
		manager.add(reload);
	}

	private void fillContextMenu(IMenuManager manager) {
		manager.add(refresh);
		manager.add(reload);
		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}

	private void fillLocalToolBar(IToolBarManager manager) {
		manager.add(refresh);
		manager.add(reload);
	}

	private void makeActions() {
		refresh = new Action() {
			public void run() {
				// showMessage("Action 1 executed");
				refreshStocks();
			}
		};
		refresh.setText("Refresh");
		refresh.setToolTipText("更新当前列表中的股票信息");
		// refresh.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
		refresh.setImageDescriptor(ImageDescriptor.createFromFile(getClass(), "/icons/refresh.gif"));
		reload = new Action() {
			public void run() {
				// showMessage("Action 2 executed");
				reloadStocks();
			}
		};
		reload.setText("Reload");
		reload.setToolTipText("重置股票列表");
		// reload.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
		reload.setImageDescriptor(ImageDescriptor.createFromFile(getClass(), "/icons/reload.gif"));
	}

	// private void showMessage(String message) {
	// MessageDialog.openInformation(viewer.getControl().getShell(), "Stock
	// Viewer", message);
	// }

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		viewer.getControl().setFocus();
	}
}