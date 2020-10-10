import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class JBetterSimpleTable extends JTable {
    JBetterSimpleTable(){
        super();
    }

    JBetterSimpleTable(DefaultTableModel model){
        super(model);
    }

    public static JBetterSimpleTable createAndReturn(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Возраст");
        model.addColumn("Количество мальчиков");
        model.addColumn("Количество девочек");
        return new JBetterSimpleTable(model);

    }


    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
