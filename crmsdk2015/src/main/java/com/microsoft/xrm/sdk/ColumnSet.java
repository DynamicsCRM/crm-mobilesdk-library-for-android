package com.microsoft.xrm.sdk;

import java.util.Arrays;

/**
 * Created on 3/5/2015.
 */
public class ColumnSet {

    private boolean allColumns;
    private DataCollection<String> Columns;

    public ColumnSet() {
        this.Columns = new DataCollection<>();
    }

    public ColumnSet(String... columns) {
        this();
        this.Columns.addAll(Arrays.asList(columns));
    }

    public ColumnSet(boolean allColumns) {
        this();
        this.allColumns = allColumns;
    }

    public void AddColumn(String column) {
        Columns.add(column);
    }

    public void AddColumns(String[] columns) {
        Columns.addAll(Arrays.asList(columns));
    }

    public String toValueXml()
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Utils.objectToXml(allColumns, "a:AllColumns", true));
        stringBuilder.append(Utils.objectToXml(Columns.toArray(new String[Columns.size()]), "a:Columns", true));
        return stringBuilder.toString();
    }
}
