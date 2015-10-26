/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lusadi.utils.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItem;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;

/**
 *
 * @author andresfelipegarciaduran
 */
public class AbstractConvertJsf {

    private static AbstractConvertJsf UtilConvertJsf;

    public static AbstractConvertJsf getUtilConvertJsf() {
        if (UtilConvertJsf == null) {
            UtilConvertJsf = new AbstractConvertJsf();
        }
        return UtilConvertJsf;
    }

    public List<SelectItem> createSelectItems(UIComponent component) {
        List<SelectItem> items = new ArrayList<SelectItem>();
        Iterator<UIComponent> children = component.getChildren().iterator();

        while (children.hasNext()) {
            UIComponent child = children.next();

            if (child instanceof UISelectItem) {
                UISelectItem selectItem = (UISelectItem) child;

                items.add(new SelectItem(selectItem.getItemValue(), selectItem.getItemLabel()));
            } else if (child instanceof UISelectItems) {
                Object selectItems = ((UISelectItems) child).getValue();

                if (selectItems instanceof SelectItem[]) {
                    SelectItem[] itemsArray = (SelectItem[]) selectItems;

                    for (SelectItem item : itemsArray) {
                        items.add(new SelectItem(item.getValue(), item.getLabel()));
                    }

                } else if (selectItems instanceof Collection) {
                    Collection<SelectItem> collection = (Collection<SelectItem>) selectItems;

                    for (SelectItem item : collection) {
                        items.add(new SelectItem(item.getValue(), item.getLabel()));
                    }
                }
            }
        }

        return items;
    }

    public Object findValueByStringConversion(FacesContext context, UIComponent component, Iterator<SelectItem> items, String value, Converter converter) {
        while (items.hasNext()) {
            SelectItem item = items.next();
            if (item instanceof SelectItemGroup) {
                SelectItem subitems[] = ((SelectItemGroup) item).getSelectItems();
                if (!isEmpty(subitems)) {
                    Object object = findValueByStringConversion(context, component, new ArrayIterator(subitems), value, converter);
                    if (object != null) {
                        return object;
                    }
                }
            } else if (!item.isNoSelectionOption() && value.equals(converter.getAsString(context, component, item.getValue()))) {
                return item.getValue();
            }
        }
        return null;
    }

    public boolean isEmpty(Object[] array) {
        return array == null || array.length == 0;
    }

    static class ArrayIterator implements Iterator<SelectItem> {

        public ArrayIterator(SelectItem items[]) {
            this.items = items;
        }

        private SelectItem items[];
        private int index = 0;

        public boolean hasNext() {
            return (index < items.length);
        }

        public SelectItem next() {
            try {
                return (items[index++]);
            } catch (IndexOutOfBoundsException e) {
                throw new NoSuchElementException();
            }
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

}
