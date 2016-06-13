package Snake.Statistic;

import Snake.Entity.Notation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;


public class SortDialog {

    JDialog dialog = null;
    JTable table = null;

    public SortDialog(String name, Notation info[], String time){
        dialog = new JDialog();
        DefaultTableModel model = new DefaultTableModel(new Object[] {"X", "Y", "Direction"}, 0);
        table = new JTable(model);
        for(Notation notation : info) {
            model.addRow(new Object[] {(notation.getHeadX()),notation.getHeadY(),Notation.getDirectionName(notation.getDirection())});
        }
        dialog.setLayout(new BorderLayout());
        dialog.add(new JLabel("Time: " + time), BorderLayout.NORTH);
        dialog.add(new JScrollPane(table), BorderLayout.CENTER);

        dialog.setTitle(name+" Sort");
        dialog.pack();
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);

    }

}
