/*
 * Copyright 2014 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.uberfire.ext.widgets.common.client.tables;

import java.lang.Object;
import java.lang.Override;
import java.util.ArrayList;

import com.github.gwtbootstrap.client.ui.DataGrid;
import com.github.gwtbootstrap.client.ui.constants.Device;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.Header;
import com.google.gwtmockito.GwtMock;
import com.google.gwtmockito.GwtMockitoTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.uberfire.ext.widgets.common.client.tables.ColumnMeta;
import org.uberfire.ext.widgets.common.client.tables.ColumnPicker;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(GwtMockitoTestRunner.class)
public class ColumnPickerTest {

    ColumnPicker columnPicker;
    private Column col1;
    private Column col2;
    private Column col3;
    private Column col4;

    private ColumnMeta colMeta1;
    private ColumnMeta colMeta2;
    private ColumnMeta colMeta3;
    private ColumnMeta colMeta4;

    @GwtMock
    DataGrid dataGrid;


    @Before
    public void setUp() throws Exception {
        col1 = mock(Column.class);
        col2 = mock(Column.class);
        col3 = mock(Column.class);
        col4 = mock(Column.class);

        colMeta1 = new ColumnMeta(col1,"caption1", "id1");
        colMeta2 = new ColumnMeta(col2,"caption2");
        colMeta3 = new ColumnMeta(col3,"caption3",true);
        colMeta4 = new ColumnMeta(col4,"caption3",true, 4);

        columnPicker = new ColumnPicker(dataGrid);

    }

    @Test
    public void basicColMetaTest() throws Exception {
        ArrayList<ColumnMeta> colMetas = new ArrayList();
        colMetas.add(colMeta1);
        colMetas.add(colMeta2);
        colMetas.add(colMeta3);

        colMeta1.setCaption("caption");
        colMeta1.setColumn_id("id1");
        colMeta1.setPosition(-1);
        colMeta1.setVisible(false);

        assertEquals(colMeta1.getCaption(), "caption");
        assertEquals(colMeta1.getColumn_id(), "id1");
        assertEquals(colMeta1.getPosition(), -1);
        assertEquals(colMeta1.isVisible(), false);

        colMeta1.setPosition(0);
        colMeta2.setPosition(-1);
        assertEquals(colMeta1.equals(colMeta2), false);

        colMeta1.setPosition(-1);
        colMeta2.setPosition(-1);
        assertEquals(colMeta1.equals(colMeta2), false);

        columnPicker.addColumns(colMetas);



    }


}
