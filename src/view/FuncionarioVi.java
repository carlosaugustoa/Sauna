package view;

import model.Funcionario;
import controller.FuncionarioCTRL;
import dao.FuncionarioDao;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class FuncionarioVi extends JPanel {

    private JButton btNew, btEdit, btDelete;
    private JTable tbFuncionarios;
    private DefaultTableModel model;
    private JScrollPane scroll;
    private FuncionarioDao dao;
    
    public FuncionarioVi() {
        setComponents();
        setEvents();
        dao = new FuncionarioDao();
    }

    private void setComponents() {
        setLayout(null);
        setBounds(0, 0, 400, 400);
        
        btNew = new JButton(new ImageIcon("images/icons/add.png"));
        btNew.setBounds(250, 10, 32, 32);
        btNew.setBackground(new Color(0,0,0));
        btNew.setBorder(null);
        add(btNew);
         
        btEdit = new JButton(new ImageIcon("images/icons/edit.png"));
        btEdit.setBounds(300, 10, 32, 32);
        add(btEdit);
        
        btDelete = new JButton(new ImageIcon("images/icons/del.png"));
        btDelete.setBounds(350, 10, 32, 32);
        add(btDelete);
        
        model = new DefaultTableModel(
                new Object[] {
                    "FUN_ID,NOME"
                }, 0
        );
        loadTable();
        tbFuncionarios = new JTable(model);
        tbFuncionarios.setRowHeight(30);
        
        DefaultTableCellRenderer alinharDireita = new DefaultTableCellRenderer();
        alinharDireita.setHorizontalAlignment(SwingConstants.RIGHT);
        DefaultTableCellRenderer alinharCentro = new DefaultTableCellRenderer();
        alinharCentro.setHorizontalAlignment(SwingConstants.CENTER);
        
        tbFuncionarios.getColumnModel().getColumn(0).setPreferredWidth(5);
        tbFuncionarios.getColumnModel().getColumn(1).setPreferredWidth(150);
        
        
        tbFuncionarios.getColumnModel().getColumn(0).setResizable(false);
        tbFuncionarios.getColumnModel().getColumn(1).setResizable(false);
                
        tbFuncionarios.getTableHeader().setReorderingAllowed(false);
       
        tbFuncionarios.getColumnModel().getColumn(0).setCellRenderer(alinharCentro);
        
        scroll = new JScrollPane();
        scroll.setViewportView(tbFuncionarios);
        scroll.setBounds(10, 70, 375, 280);
        add(scroll);
    }

    private void setEvents() {
        btEdit.addActionListener(new ActionListener() {
           
            @Override
            public void actionPerformed(ActionEvent e) {
                int i[] = tbFuncionarios.getSelectedRows();
                if (i.length == 0) {
                    JOptionPane.showMessageDialog(null, "Selecione um funcionario!");
                    
                } else if (i.length > 1) {
                    JOptionPane.showMessageDialog(null, "Selecione apenas um funcionario!");
                } else {
                    Funcionario funcionario = new Funcionario();
                    funcionario.setFun_id((String) tbFuncionarios.getValueAt(i[0], 0));
                    funcionario.setFun_nome((String) tbFuncionarios.getValueAt(i[0], 1));
                    FormFuncionarioVi form = new FormFuncionarioVi(funcionario, model);
                    form.open();
                }
            }
        });
        btNew.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                FormFuncionarioVi form = new FormFuncionarioVi(null, model);
                form.open();
            }
        });
        btDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                
                int i[] = tbFuncionarios.getSelectedRows();
                if (i.length == 0) {
                    JOptionPane.showMessageDialog(null, "Selecione um funcionario!");
                } else if (i.length > 1) {
                    JOptionPane.showMessageDialog(null, "Selecione apenas um funcionario!");
                } else {
                    Funcionario funcionario = new Funcionario();
                    funcionario.setFun_id((String) tbFuncionarios.getValueAt(i[0], 0));
                    funcionario.setFun_nome((String) tbFuncionarios.getValueAt(i[0], 1));
                    
                    dao.delete(funcionario);
                    
                    FormFuncionarioVi form = new FormFuncionarioVi(funcionario,model);
                    int[] linhas = tbFuncionarios.getSelectedRows();
                    DefaultTableModel dtm = (DefaultTableModel) tbFuncionarios.getModel();
                    for(int j = (linhas.length-1); j>=0; j--){
                        dtm.removeRow(linhas[j]);
                    }
                }                
            }
        });
    }
    
    private void loadTable() {
        model.setRowCount(0);
        for(Funcionario funcionario : new FuncionarioCTRL().listar(null)){
           model.addRow(new Object[] {funcionario.getFun_id(), funcionario.getFun_nome()});
        }
    }
    
//    public static void run(){
    public static void main(String[] args) {
        FuncionarioVi frame = new FuncionarioVi();
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        Dimension window = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (window.width - frame.getSize().width)/2;
        int y = (window.height - frame.getSize().height)/2;
        frame.setLocation(x,y);
        frame.setVisible(true);
    }

    private void setDefaultCloseOperation(int EXIT_ON_CLOSE) {
        throw new UnsupportedOperationException("");
    }
    
}
