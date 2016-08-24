package view;

import controller.FuncionarioCTRL;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import model.Funcionario;

public class FuncionarioVi extends JFrame {

    private JTextField tfPesquisar;
    private JButton btNew, btEdit, btDelete;
    private JTable tbFuncionarios;
    private DefaultTableModel model;
    private JScrollPane scroll;
    
    public FuncionarioVi() {
        setComponents();
        setEvents();
    }

    private void setComponents() {
        setLayout(null);
        setTitle("Sauna");
        setResizable(false);
        setIconImage(new ImageIcon("images/icons/contatos.png").getImage());
        setBounds(0, 0, 400, 400);
        
        tfPesquisar = new JTextField();
        tfPesquisar.setBounds(10, 10, 200, 32);
        add(tfPesquisar);
        
        btNew = new JButton(new ImageIcon("images/icons/add.png"));
        btNew.setBounds(250, 10, 32, 32);
        //btNew.setBackground(new Color(238, 238, 238));
        btNew.setBackground(new Color(0,0,0));
        btNew.setBorder(null);
        add(btNew);
         
        btEdit = new JButton(new ImageIcon("images/icons/edit.png"));
        btEdit.setBounds(300, 10, 32, 32);
        //btEdit.setBackground(new Color(238, 238, 238));
        //btEdit.setBorder(null);
        add(btEdit);
        
        btDelete = new JButton(new ImageIcon("images/icons/del.png"));
        btDelete.setBounds(350, 10, 32, 32);
        //btDelete.setBackground(new Color(238, 238, 238));
        //btDelete.setBorder(null);
        add(btDelete);
        
        model = new DefaultTableModel(new Object[] {"ID", "NOME"}, 0) {public boolean isCellEditable(int row, int col) {return false;}};
        loadTable();
        tbFuncionarios = new JTable(model);
        tbFuncionarios.setRowHeight(30);
        
        DefaultTableCellRenderer alinharDireita = new DefaultTableCellRenderer();
        alinharDireita.setHorizontalAlignment(SwingConstants.RIGHT);
        DefaultTableCellRenderer alinharCentro = new DefaultTableCellRenderer();
        alinharCentro.setHorizontalAlignment(SwingConstants.CENTER);
        
        tbFuncionarios.getColumnModel().getColumn(0).setPreferredWidth(5);
        tbFuncionarios.getColumnModel().getColumn(1).setPreferredWidth(150);
        tbFuncionarios.getColumnModel().getColumn(2).setPreferredWidth(150);
        
        tbFuncionarios.getColumnModel().getColumn(0).setResizable(false);
        tbFuncionarios.getColumnModel().getColumn(1).setResizable(false);
        tbFuncionarios.getColumnModel().getColumn(2).setResizable(false);
        
        tbFuncionarios.getColumnModel().getColumn(0).setCellRenderer(alinharCentro);
        tbFuncionarios.getColumnModel().getColumn(2).setCellRenderer(alinharDireita);
        
        tbFuncionarios.getTableHeader().setReorderingAllowed(false);
        
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
                    funcionario.setFun_id((int) tbFuncionarios.getValueAt(i[0], 0));
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
            @Override
            public void actionPerformed(ActionEvent e) {
                int i[] = tbFuncionarios.getSelectedRows();
                if (i.length == 0) {
                    JOptionPane.showMessageDialog(null, "Selecione um funcionario!");
                } else if (i.length > 1) {
                    JOptionPane.showMessageDialog(null, "Selecione apenas um funcionario!");
                } else {
                    Funcionario funcionario = new Funcionario();
                    funcionario.setFun_id((int) tbFuncionarios.getValueAt(i[0], 0));
                    funcionario.setFun_nome((String) tbFuncionarios.getValueAt(i[0], 1));
                    int resposta = JOptionPane.showConfirmDialog(null, "Deseja excluir " + funcionario.getFun_nome() + "?");
                    if (resposta == 0) {
                        if (new FuncionarioCTRL().remover(funcionario.getFun_id())) {
                            JOptionPane.showMessageDialog(null, funcionario.getFun_nome() + " excluído com sucesso!");
                            loadTable();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Falha ao tentar excluir");
                    }
                }
            }
        });
        tfPesquisar.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {
                loadTable();
            }
        });
    }
    
    private void loadTable() {
        model.setRowCount(0);
        for (Funcionario funcionario : new FuncionarioCTRL().listar(tfPesquisar.getText().equals("") ? null : tfPesquisar.getText())) {
            model.addRow(new Object[] {funcionario.getFun_id(), funcionario.getFun_nome()});
        }
    }
    
    public static void main(String[] args) {
        FuncionarioVi frame = new FuncionarioVi();
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        Dimension window = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (window.width - frame.getSize().width) / 2;
        int y = (window.height - frame.getSize().height) / 2;
        frame.setLocation(x, y);
        frame.setVisible(true);
    }
    
}
