package com.epam.engx.cleancode.complextask.task1;



import com.epam.engx.cleancode.complextask.task1.thirdpartyjar.Command;
import com.epam.engx.cleancode.complextask.task1.thirdpartyjar.DataSet;
import com.epam.engx.cleancode.complextask.task1.thirdpartyjar.View;
import com.epam.engx.cleancode.complextask.task1.thirdpartyjar.DatabaseManager;

import java.util.List;


public class Print implements Command {

    private View view;
    private DatabaseManager manager;
    private String tableName;

    public Print(View view, DatabaseManager manager) {
        this.view = view;
        this.manager = manager;
    }

    public boolean canProcess(String command) {
        return command.startsWith("print ");
    }

    public void process(String input) {
        String[] command = input.split(" ");
        validateCommands(command);
        tableName = command[1];
        List<DataSet> data = manager.getTableData(tableName);
        view.write(getTableString(data));
    }

    private void validateCommands(String[] command) {
        if (command.length != 2) {
            throw new IllegalArgumentException("incorrect number of parameters. Expected 1, but is " + (command.length - 1));
        }
    }

    private String getTableString(List<DataSet> data) {
        int maxColumnSize;
        maxColumnSize = getMaxColumnSize(data);
        if (maxColumnSize == 0) {
            return getEmptyTable(tableName);
        } else {
            return getHeaderOfTheTable(data) + getStringTableData(data);
        }
    }

    private String getEmptyTable(String tableName) {
        String textEmptyTable = "║ Table '" + tableName + "' is empty or does not exist ║";
        int emptyLength = textEmptyTable.length() - 2;
        String result = printHeaderFirstString(emptyLength, 0);
        result += textEmptyTable + "\n";
        result += printLastRowString(emptyLength, 0);
        return result;
    }

    private int getMaxColumnSize(List<DataSet> dataSets) {
        int maxLength = 0;
        if (dataSets.size() > 0) {
            List<String> columnNames = dataSets.get(0).getColumnNames();
            maxLength = getMaxSize(columnNames, maxLength);
            for (DataSet dataSet : dataSets) {
                List<Object> values = dataSet.getValues();
                maxLength = getMaxSize(values, maxLength);
            }
            if (maxLength % 2 == 0) {
                maxLength += 2;
            } else {
                maxLength += 3;
            }
        }
        return maxLength;
    }

    private int getMaxSize(List values, int maxLength) {
        for (Object value : values) {
            int columnLength = String.valueOf(value).length();
            if (columnLength > maxLength) {
                maxLength = columnLength;
            }
        }
        return maxLength;
    }

    private String getStringTableData(List<DataSet> dataSets) {
        String result = "";
        int maxColumnSize = getMaxColumnSize(dataSets);
        int columnCount = getColumnCount(dataSets);
        result += printTableData(dataSets, maxColumnSize, columnCount);
        result += printLastRowString(maxColumnSize, columnCount);
        return result;
    }

    private String printTableData(List<DataSet> dataSets, int maxColumnSize, int columnCount) {
        String result = "";
        int rowsCount = dataSets.size();
        for (int row = 0; row < rowsCount; row++) {
            List<Object> values = dataSets.get(row).getValues();
            result += printRowData(values, maxColumnSize, columnCount);
            if (row < rowsCount - 1) {
                result += printRowString(maxColumnSize, columnCount);
            }
        }
        return result;
    }

    private String getHeaderOfTheTable(List<DataSet> dataSets) {
        String result = "";
        int maxColumnSize = getMaxColumnSize(dataSets);
        int columnCount = getColumnCount(dataSets);
        result += printHeaderFirstString(maxColumnSize, columnCount);
        List<String> columnNames = dataSets.get(0).getColumnNames();
        result += printRowData(columnNames, maxColumnSize, columnCount);

        //last string of the header
        result += printHeaderLastString(dataSets, maxColumnSize, columnCount);
        return result;
    }

    private int getColumnCount(List<DataSet> dataSets) {
        int result = 0;
        if (dataSets.size() > 0) {
            return dataSets.get(0).getColumnNames().size();
        }
        return result;
    }

    private String printHeaderFirstString(int maxColumnSize, int columnCount) {
        String result = "╔";
        for (int j = 1; j < columnCount; j++) {
            result += printIdenticalChars(maxColumnSize, "═");
            result += "╦";
        }
        result += printIdenticalChars(maxColumnSize, "═");
        result += "╗\n";
        return result;
    }

    private String printHeaderLastString(List<DataSet> dataSets, int maxColumnSize, int columnCount) {
        String result = "";
        if (dataSets.size() > 0) {
            result += printRowString(maxColumnSize, columnCount);
        } else {
            result += printLastRowString(maxColumnSize, columnCount);
        }
        return result;
    }

    private String printRowString(int maxColumnSize, int columnCount) {
        String result = "╠";
        for (int j = 1; j < columnCount; j++) {
            result += printIdenticalChars(maxColumnSize, "═");
            result += "╬";
        }
        result += printIdenticalChars(maxColumnSize, "═");
        result += "╣\n";
        return result;
    }

    private String printLastRowString(int maxColumnSize, int columnCount) {
        String result = "╚";
        for (int j = 1; j < columnCount; j++) {
            result += printIdenticalChars(maxColumnSize, "═");
            result += "╩";
        }
        result += printIdenticalChars(maxColumnSize, "═");
        result += "╝\n";
        return result;
    }

    private String printRowData(List values, int maxColumnSize, int columnCount) {
        String result = "║";
        for (int column = 0; column < columnCount; column++) {
            String value = String.valueOf(values.get(column));
            int valuesLength = value.length();
            int charsLength = (maxColumnSize - valuesLength) / 2;
            result += printCellData(valuesLength, charsLength, value);
            result += "║";
        }
        result += "\n";
        return result;
    }

    private String printCellData(int valuesLength, int charsLength, String value) {
        String result = "";
        if (valuesLength % 2 == 0) {
            result += printIdenticalChars(charsLength, " ");
            result += value;
            result += printIdenticalChars(charsLength, " ");
        } else {
            result += printIdenticalChars(charsLength, " ");
            result += value;
            result += printIdenticalChars(charsLength + 1, " ");
        }
        return result;
    }

    private String printIdenticalChars (int length, String character) {
        String result = "";
        for (int i = 0; i < length; i++) {
            result += character;
        }
        return result;
    }
}