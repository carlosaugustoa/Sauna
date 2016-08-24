package view;

import controller.FuncionarioCTRL;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.ParseException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import model.Funcionario;

public class FormFuncionarioVi extends JFrame {

    private JLabel lbFun_nome;
    private JTextField tfFun_nome;
    private JButton btSalvar;
    private DefaultTableModel model;
    private Funcionario funcionario;
    
    public FormFuncionarioVi(Funcionario funcionario, DefaultTableModel model) {
        this.funcionario = funcionario;
        this.model = model;
        setComponents();
        setEvents();
    }

    private void setComponents() {
        setLayout(null);
        setResizable(false);
        setBounds(0, 0, 380, 120);
        
        lbFun_nome = new JLabel("Nome:");
        lbFun_nome.setBounds(10, 10, 80, 25);
        add(lbFun_nome);
        
        tfFun_nome = new JTextField();
        tfFun_nome.setBounds(10, 35, 150, 32);
        add(tfFun_nome);
        
            if (funcionario == null) {
                setIconImage(new ImageIcon("images/icons/add.png").getImage());
                setTitle("Novo Funcionario");
            } else {
                setIconImage(new ImageIcon("images/icons/edit.png").getImage());
                setTitle("Editar Funcionario");
                tfFun_nome.setText(funcionario.getFun_nome());
            }
   
        btSalvar = new JButton(new ImageIcon("images/icons/save.png"));
        btSalvar.setBounds(320, 35, 32, 32);
        btSalvar.setBackground(new Color(238, 238, 238));
        btSalvar.setBorder(null);
        add(btSalvar);
        
    }

    private void setEvents() {
        btSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tfFun_nome.getText().equals("") ) {
                    JOptionPane.showMessageDialog(null, "Campos obrigatórios");
                } else {
                    FuncionarioCTRL control = new FuncionarioCTRL();
                    if (funcionario == null) {
                        if (control.adcionar(tfFun_nome.getText()) {
                            loadTable();
                            setVisible(false);
                        } else {
                            JOptionPane.showMessageDialog(null, "Falha ao tentar adcionar");
                        }
                    } else {
                        if (control.atualizar(funcionario.getFun_id(), tfFun_nome.getText())) {
                            loadTable();
                            setVisible(false);
                        } else {
                            JOptionPane.showMessageDialog(null, "Falha ao tentar atualizar");
                        }
                    }
                }
            }  
        });
        
    }
    
    private void loadTable() {
        model.setRowCount(0);
        for (Funcionario funcionario : new FuncionarioCTRL().listar(null)) {
            model.addRow(new Object[] {funcionario.getFun_id(), funcionario.getFun_nome()});
        }
    }
    
    public void open() {
        FormFuncionarioVi frame = new FormFuncionarioVi(funcionario, model);
        Dimension window = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (window.width - frame.getSize().width) / 2;
        int y = (window.height - frame.getSize().height) / 2;
        frame.setLocation(x, y);
        frame.setVisible(true);
    }

}
