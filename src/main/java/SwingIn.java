
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class SwingIn implements ActionListener {
    JFrame jfrm;
    JButton chooseBut;
    JButton calcBut;
    JButton settings;
    JFileChooser fileChooser;
    JLabel fileLabel;
    JLabel alertLabel;
    File file;
    JBetterSimpleTable resultTable;
    FileFilter filter = new FileNameExtensionFilter("older then 2003 excel files", "xlsx");



    SwingIn(){
        jfrm = new JFrame("Help for mama");
        jfrm.setSize(480, 480);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        chooseBut = new JButton("Choose File...");
        chooseBut.addActionListener(this);
        chooseBut.setActionCommand("chooseFile");

        calcBut = new JButton("Calculate");
        calcBut.addActionListener(this);
        calcBut.setActionCommand("calculate");

        settings = new JButton("Settings");
        calcBut.addActionListener(this);
        calcBut.setActionCommand("settings");

        fileLabel = new JLabel();
        alertLabel = new JLabel();

        fileChooser = new JFileChooser();
        fileChooser.setFileFilter(filter);

        resultTable = JBetterSimpleTable.createAndReturn();
        JScrollPane resTabPane = new JScrollPane(resultTable);

        GroupLayout layout = new GroupLayout(jfrm.getContentPane());
        jfrm.getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                            .addComponent(chooseBut)
                                            .addComponent(calcBut)
                                    )
                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                            .addComponent(fileLabel)
                                            .addComponent(alertLabel)
                                    )
                            )
                            .addComponent(resTabPane)
                        )
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                        .addComponent(chooseBut)
                                        .addComponent(fileLabel)
                                )
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                        .addComponent(calcBut)
                                        .addComponent(alertLabel)
                                )
                        )
                        .addComponent(resTabPane)
        );

        jfrm.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getActionCommand().equals("chooseFile")){
            fileChooser.setDialogTitle("choose file");
            int result = fileChooser.showOpenDialog(jfrm);
            if (result == JFileChooser.APPROVE_OPTION){
                file = fileChooser.getSelectedFile();
                fileLabel.setText(file.getPath());
                alertLabel.setText("");
            }
        } else if (actionEvent.getActionCommand().equals("calculate")){
            if(fileLabel.getText().equals("")){
                alertLabel.setText("Nothing to calculate. Choose exel (.xlsx) file");
            } else {
                alertLabel.setText("");
                try{
                    Handler handler = new Handler();
                    handler.calculate(file, resultTable);
                    alertLabel.setText("Successfully calculated!");
                }
                catch (Exception e) {
                    alertLabel.setText("Excel file is filled in incorrectly");
                }
            }
        } else if(actionEvent.getActionCommand().equals("settings")){

        }
    }
}
