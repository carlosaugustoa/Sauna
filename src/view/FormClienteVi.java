package view;

import controller.ClienteCTRL;
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
import model.Cliente;

public class FormClienteVi extends JFrame {

    private JLabel lbCli_id, lbCli_seq, lbCli_data, lbCli_nome, lbCli_status;
    private JTextField tfCli_id, tfCli_seq, tfCli_data, tfCli_nome, tfCli_status;
    private JFormattedTextField ftCli_id, ftCli_seq, ftCli_data, ftCli_nome, ftCli_status;
    private MaskFormatter mask;
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
        setBounds(0, 0, 380, 120);
        
        lbCli_id = new JLabel("Cli-id:");
        lbCli_id.setBounds(10, 10, 80, 25);
        add(lbCli_id);
        
        tfCli_id = new JTextField();
        tfCli_id.setBounds(10, 35, 150, 32);
        add(tfCli_id);
        
        
        
        
        
        
        
        try {
            if (cliente == null) {
                setIconImage(new ImageIcon("images/icons/add.png").getImage());
                setTitle("Novo Cliente");
            } else {
                setIconImage(new ImageIcon("images/icons/edit.png").getImage());
                setTitle("Editar Cliente");
                ftCli_id.setText(cliente.getCli_id());
                ftCli_seq.setText(cliente.getCli_seq());
                ftCli_data.setText(cliente.getCli_data());
                ftCli_nome.setTest(cliente.getCli_nome());
                ftFone.setText(contato.getFone());
            }
        } catch (ParseException error) {
            System.out.println("ERRO: " + error.toString());
        } finally {
            ftFone.setBounds(170, 35, 130, 32);
            add(ftFone);
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
                if (tfNome.getText().equals("") || FoneHelper.clear(ftFone.getText()).equals("")) {
                    JOptionPane.showMessageDialog(null, "Campos obrigatórios");
                } else {
                    ContatoCTRL control = new ContatoCTRL();
                    if (contato == null) {
                        if (control.adcionar(tfNome.getText(), FoneHelper.clear(ftFone.getText()))) {
                            loadTable();
                            setVisible(false);
                        } else {
                            JOptionPane.showMessageDialog(null, "Falha ao tentar adcionar");
                        }
                    } else {
                        if (control.atualizar(contato.getId(), tfNome.getText(), FoneHelper.clear(ftFone.getText()))) {
                            loadTable();
                            setVisible(false);
                        } else {
                            JOptionPane.showMessageDialog(null, "Falha ao tentar atualizar");
                        }
                    }
                }
            }  
        });
        ftFone.addKeyListener(new KeyListener() {
            @Override
            public void keyReleased(KeyEvent e) {
                try {
                    String fone = FoneHelper.clear(ftFone.getText());
                    if (FoneHelper.clear(ftFone.getText()).substring(2, 3).equals("9")) {
                        mask = new MaskFormatter("(##) #####-####");
                        ftFone.setFormatterFactory(new DefaultFormatterFactory(mask));
                        ftFone.setText(fone);
                    } else {
                        mask = new MaskFormatter("(##) ####-####");
                        ftFone.setFormatterFactory(new DefaultFormatterFactory(mask));
                        ftFone.setText(fone);
                    }
                } catch (StringIndexOutOfBoundsException | ParseException error) {}
            }
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {}
        });
    }
    
    private void loadTable() {
        model.setRowCount(0);
        for (Contato contato : new ContatoCTRL().listar(null)) {
            model.addRow(new Object[] {contato.getId(), contato.getNome(), FoneHelper.format(contato.getFone())});
        }
    }
    
    public void open() {
        FormContatoUI frame = new FormContatoUI(contato, model);
        Dimension window = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (window.width - frame.getSize().width) / 2;
        int y = (window.height - frame.getSize().height) / 2;
        frame.setLocation(x, y);
        frame.setVisible(true);
    }
