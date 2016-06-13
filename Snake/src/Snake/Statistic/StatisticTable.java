package Snake.Statistic;

import Snake.Entity.Notation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;


public class StatisticTable {

    JDialog dialog = null;
    JTable table = null;

    public StatisticTable(String name, int[] answer, int max){
        dialog = new JDialog();
        dialog.setTitle(name);
        DefaultTableModel model = new DefaultTableModel
                (new Object[] {"State", "Count"}, 0);
        table = new JTable(model);

        for(int i = 0; i < 4; i++){
            model.addRow(new Object[] {Notation.getDirectionName(i + 1), answer[i]});
        }

        model.addRow(new Object[] {"Most common: " + Notation.getDirectionName(max+1), answer[max]});

        dialog.add(new JScrollPane(table));
        dialog.setSize(new Dimension(300, 200));
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);
    }

}
