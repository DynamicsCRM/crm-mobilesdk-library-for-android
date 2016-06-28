package com.microsoft.xrm.sdk;

import java.util.Arrays;

public class ColumnSet {

    private boolean allColumns;
    private final DataCollection<String> Columns;

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
        return Utils.objectToXml(allColumns, "a:AllColumns", true) +
            Utils.objectToXml(Columns.toArray(new String[Columns.size()]), "a:Columns", true);
    }
}
