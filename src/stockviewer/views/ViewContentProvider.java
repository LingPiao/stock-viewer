/**********************************************************************
 *         File: DefaultContentProvider.java
 *      Creator: Evan.Kong
 *         Date: Mar 20, 2008
 *  Description: 
 *               File Desc.
 *              
 *
 * MODIFICATION DESCRIPTION
 *      
 * Name                 Date                Description 
 * ============         ============        ============
 * Evan.Kong			Mar 20, 2008			Created
 * 
 * *********************************************************************/
package stockviewer.views;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class ViewContentProvider implements IStructuredContentProvider {
    public Object[] getElements(Object element) {
        if (element instanceof List)
            return ((List) element).toArray();
        if (element instanceof Object[])
            return (Object[]) element;
        return new Object[0];
    }
    public void dispose() {}
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {}
}
