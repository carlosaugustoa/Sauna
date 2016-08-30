package view;

import controller.ClienteCTRL;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.Cliente;

public class FormClienteVi extends JFrame {

    private JLabel lbCli_id, lbCli_seq, lbCli_data, lbCli_nome, lbCli_status;
    private JTextField tfCli_id, tfCli_seq, tfCli_data, tfCli_nome, tfCli_status;
    private JButton btSalvar;
    private DefaultTableModel model;
    private Cliente cliente;
    
    public FormClienteVi(Cliente cliente, DefaultTableModel model) {
        this.cliente = cliente;
        this.model = model;
        setComponents();
        setEvents();
    }

    private void setComponents() {
        setLayout(null);
        setResizable(false);
        setBounds(0, 0, 500, 320);
        
        lbCli_id = new JLabel("NOME:");
        lbCli_id.setBounds(10, 25, 80, 25);
        add(lbCli_id);
        
        tfCli_id = new JTextField();
        tfCli_id.setBounds(10, 50, 40, 25);
        add(tfCli_id);
        
        lbCli_seq = new JLabel("CLI-SEQ:");
        lbCli_seq.setBounds(10, 75, 80, 25);
        add(lbCli_seq);
        
        tfCli_seq = new JTextField();
        tfCli_seq.setBounds(10, 100, 20, 25);
        add(tfCli_seq);
        
        lbCli_data = new JLabel("DATA:");
        lbCli_data.setBounds(10, 125, 80, 25);
        add(lbCli_data);
        
        tfCli_data = new JTextField();
        tfCli_data.setBounds(10, 150, 150, 25);
        add(tfCli_data);
        
        lbCli_nome = new JLabel("nome:");
        lbCli_id.setBounds(10, 175, 80, 25);
        add(lbCli_nome);
        
        tfCli_nome = new JTextField();
        tfCli_nome.setBounds(10, 200, 400, 25);
        add(tfCli_nome);
        
        lbCli_status = new JLabel("STATUS:");
        lbCli_status.setBounds(10, 225, 80, 25);
        add(lbCli_status);
        
        tfCli_status = new JTextField();
        tfCli_status.setBounds(10, 250, 20, 25);
        add(tfCli_status);
        
        if (cliente == null) {
            setIconImage(new ImageIcon("images/icons/add.png").getImage());
            setTitle("NOVO CLIENTE");
        } else {
            setIconImage(new ImageIcon("images/icons/edit.png").getImage());
            setTitle("EDITAR CLIENTE");
            tfCli_id.setText(String.valueOf(cliente.getCli_id()));
            tfCli_seq.setText(String.valueOf(cliente.getCli_seq()));
            tfCli_data.setText(cliente.getCli_data());
            tfCli_nome.setText(cliente.getCli_nome());
            tfCli_status.setText(String.valueOf(cliente.getCli_status()));
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
                if (tfCli_id.getText().equals("") || tfCli_seq.getText().equals("") || tfCli_data.getText().equals("") || tfCli_nome.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Campos obrigatórios");
                } else {
                    ClienteCTRL control = new ClienteCTRL();
                    if (cliente == null) {
                        if (control.adcionar(Integer.parseInt(tfCli_id.getText()), Integer.parseInt(tfCli_seq.getText()), tfCli_data.getText(), tfCli_nome.getText(), Integer.parseInt(tfCli_status.getText()))) {
                            loadTable();
                            setVisible(false);
                        } else {
                            JOptionPane.showMessageDialog(null, "Falha ao tentar adcionar");
                        }
                    } else {
                        if (control.atualizar(Integer.parseInt(tfCli_id.getText()), Integer.parseInt(tfCli_seq.getText()), tfCli_data.getText(), tfCli_nome.getText(), Integer.parseInt(tfCli_status.getText()))) {
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
        for (Cliente cliente : new ClienteCTRL().listar(null)) {
            model.addRow(new Object[] {cliente.getCli_id(), cliente.getCli_seq(), cliente.getCli_data(), cliente.getCli_nome(), cliente.getCli_status()});
        }
    }
    
    public void open() {
        FormClienteVi frame = new FormClienteVi(cliente, model);
        Dimension window = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (window.width - frame.getSize().width) / 2;
        int y = (window.height - frame.getSize().height) / 2;
        frame.setLocation(x, y);
        frame.setVisible(true);
    }
}