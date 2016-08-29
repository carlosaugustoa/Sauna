package view;

import controller.ProdutoCTRL;
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
import model.Produto;

public class FormProdutoVi extends JFrame {

    private JLabel lbPro_id, lbPro_nome, lbPro_valor;
    private JTextField tfPro_id, tfPro_nome, tfPro_valor;
    private JButton btSalvar;
    private DefaultTableModel model;
    private Produto produto;
    
    public FormProdutoVi(Produto produto, DefaultTableModel model) {
        this.produto = produto;
        this.model = model;
        setComponents();
        setEvents();
    }

    private void setComponents() {
        setLayout(null);
        setResizable(false);
        setBounds(0, 0, 380, 120);
       
        lbPro_id = new JLabel("Pro-id:");
        lbPro_id.setBounds(10, 10, 80, 25);
        add(lbPro_id);
        
        tfPro_id = new JTextField();
        tfPro_id.setBounds(10, 35, 150, 32);
        add(tfPro_id);
        
        lbPro_nome = new JLabel("Nome:");
        lbPro_nome.setBounds(10, 10, 80, 25);
        add(lbPro_nome);
        
        tfPro_nome = new JTextField();
        tfPro_nome.setBounds(10, 65, 150, 32);
        add(tfPro_nome);
        
        lbPro_valor = new JLabel("Valor:");
        lbPro_valor.setBounds(10, 10, 80, 25);
        add(lbPro_valor);
        
        tfPro_valor = new JTextField();
        tfPro_valor.setBounds(10, 95, 150, 32);
        add(tfPro_valor);
        
        
        if (produto == null) {
            setIconImage(new ImageIcon("images/icons/add.png").getImage());
            setTitle("Novo Produto");
        } else {
            setIconImage(new ImageIcon("images/icons/edit.png").getImage());
            setTitle("Editar Produto");
            tfPro_nome.setText(produto.getPro_nome());
            tfPro_valor.setText(String.valueOf(produto.getPro_valor()));
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
                if (tfPro_id.getText().equals("") || tfPro_nome.getText().equals("") || tfPro_valor.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Campos obrigatórios");
                } else {
                    ProdutoCTRL control = new ProdutoCTRL();
                    if (produto == null) { 
                        if (control.adcionar(tfPro_id.getText(), tfPro_nome.getText(), Integer.parseInt(tfPro_valor.getText()) ) ) {
                            loadTable();
                            setVisible(false);
                        } else {
                            JOptionPane.showMessageDialog(null, "Falha ao tentar adcionar");
                        }
                    } else {
                        if (control.atualizar(tfPro_id.getText(), tfPro_nome.getText(), Integer.parseInt(tfPro_valor.getText()) ) ) {
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
        for (Produto produto : new ProdutoCTRL().listar(null)) {
            model.addRow(new Object[] {produto.getPro_id(), produto.getPro_nome(), produto.getPro_valor()});
        }
    }
    
    public void open() {
        FormProdutoVi frame = new FormProdutoVi(produto, model);
        Dimension window = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (window.width - frame.getSize().width) / 2;
        int y = (window.height - frame.getSize().height) / 2;
        frame.setLocation(x, y);
        frame.setVisible(true);
    }

}
