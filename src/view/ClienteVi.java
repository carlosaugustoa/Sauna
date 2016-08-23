package view;

import model.Cliente;
import controller.ClienteCTRL;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class ClienteVi extends JFrame {
    
    private JTextField tfPesquisar;
    private JButton btNew, btEdit, btDelete;
    private JTable tbClientes;
    private DefaultTableModel model;
    private JScrollPane scroll;

    public ClienteVi(){
        setComponents();
        setEvents();
    }
       
    private void setComponents(){
        setLayout(null);
        setTitle("SAUNA");
        setResizable(false);
        setIconImage(new ImageIcon("images/icons/clientes.png").getImage());
        setBounds(0,0,800,800);
        
        tfPesquisar = new JTextField();
        tfPesquisar.setBounds(10,10,100,32);
        add(tfPesquisar);
    
        btNew = new JButton(new ImageIcon("images/icons/add.jpg"));
        btNew.setBounds(400,10,32,32);
        btNew.setBackground(new Color(0,0,0));
        btNew.setBorder(null);
        add(btNew);
        
        btEdit = new JButton(new ImageIcon("images/icons/edit.png"));
        btEdit.setBounds(500,10,32,32);
        //btNew.setBackground(new Color(238,238,238));
        //btNew.setBorder(null);
        add(btEdit);
        
        btDelete = new JButton(new ImageIcon("images/icons/del.png"));
        btDelete.setBounds(600,10,32,32);
        //btNew.setBackground(new Color(238,238,238));
        //btNew.setBorder(null);
        add(btDelete);
        
        model = new DefaultTableModel(
                new Object[] {
                    "ID","SEQ","DATA","NOME","STATUS"
            }, 0    
        );
        loadTable();
        tbClientes = new JTable(model);
        tbClientes.setRowHeight(30);
        
        DefaultTableCellRenderer alinharDireita = new DefaultTableCellRenderer();
        alinharDireita.setHorizontalAlignment(SwingConstants.RIGHT);
        
        tbClientes.getColumnModel().getColumn(0).setPreferredWidth(5);
        tbClientes.getColumnModel().getColumn(1).setPreferredWidth(5);
        tbClientes.getColumnModel().getColumn(2).setPreferredWidth(50); 
        tbClientes.getColumnModel().getColumn(3).setPreferredWidth(350); 
        tbClientes.getColumnModel().getColumn(4).setPreferredWidth(5); 
        
        tbClientes.getColumnModel().getColumn(0).setResizable(false);
        tbClientes.getColumnModel().getColumn(1).setResizable(false);
        tbClientes.getColumnModel().getColumn(2).setResizable(false); 
        tbClientes.getColumnModel().getColumn(3).setResizable(false); 
        tbClientes.getColumnModel().getColumn(4).setResizable(false); 
         
        //tbContatos.getColumnModel().getColumn(2),setCellRenderer(alinharDireita);
        
        // muda de lugar os campos
        tbClientes.getTableHeader().setReorderingAllowed(false);
                        
        scroll = new JScrollPane();
        scroll.setViewportView(tbClientes);
        scroll.setBounds(10,70,375,280);
        add(scroll);
    }

    private void setEvents() {
        btEdit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int i[] = tbClientes.getSelectedRows();
                if (i.length == 0){
                    JOptionPane.showMessageDialog(null, "Selecione um cliente!");
                  
                }else if (i.length > 1){
                    JOptionPane.showMessageDialog(null, "Selecione apenas um cliente!");
                }else {
                    Cliente cliente = new Cliente();
                    cliente.setCli_id((int)tbClientes.getValueAt(i[0], 0));
                    cliente.setCli_seq((int)tbClientes.getValueAt(i[0], 1));
                    cliente.setCli_data((String)tbClientes.getValueAt(i[0], 2));
                    cliente.setCli_nome((String) tbClientes.getValueAt(i[0], 3));
                    cliente.setCli_status((int)tbClientes.getValueAt(i[0], 4));
                    FormClienteVi form = new FormClienteVi(cliente,model);
                    form.open();
                }
            }
        });
        btNew.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                FormClienteVi form = new FormClienteVi(null, model);
                form.open();
            }
        });
    
    }
    
    private void loadTable(){
        model.setRowCount(0);
        for(Cliente cliente : new ClienteCTRL().listar(null)){
          model.addRow(new Object[]{cliente.getCli_id(),cliente.getCli_seq(),cliente.getCli_data(),cliente.getCli_nome(),cliente.getCli_status()});
        }
    }
    
    public static void main(String[]args){
        ClienteVi frame = new ClienteVi();
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        Dimension window = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (window.width - frame.getSize().width)/2;
        int y = (window.height - frame.getSize().height)/2;
        frame.setLocation(x,y);
        frame.setVisible(true);
    }
    
}
