package view;

import model.Consumo;
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

public class ConsumoVi extends JFrame {
    
    private JTextField tfPesquisar;
    private JButton btNew, btEdit, btDelete;
    private JTable tbConsumo;
    private DefaultTableModel model;
    private JScrollPane scroll;

    public ConsumoVi(){
        setComponents();
        setEvents();
    }
       
    private void setComponents(){
        setLayout(null);
        setTitle("CONSUMO");
        setResizable(false);
        setIconImage(new ImageIcon("images/icons/clientes.png").getImage());
        setBounds(0,0,800,900);
        
        tfPesquisar = new JTextField();
        tfPesquisar.setBounds(10,10,300,32);
        add(tfPesquisar);
    
        btNew = new JButton(new ImageIcon("images/icons/add.png"));
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
                    "CLI-ID","CLI-SEQ","DATA","FUNCIONARIO","PRO_ID"
            }, 0    
        );
        loadTable();
        tbConsumo = new JTable(model);
        tbConsumo.setRowHeight(30);
        
        DefaultTableCellRenderer alinharDireita = new DefaultTableCellRenderer();
        alinharDireita.setHorizontalAlignment(SwingConstants.RIGHT);
        
        tbConsumo.getColumnModel().getColumn(0).setPreferredWidth(100);
        tbConsumo.getColumnModel().getColumn(1).setPreferredWidth(100);
        tbConsumo.getColumnModel().getColumn(2).setPreferredWidth(250); 
        tbConsumo.getColumnModel().getColumn(3).setPreferredWidth(700); 
        tbConsumo.getColumnModel().getColumn(4).setPreferredWidth(100); 
        
        tbConsumo.getColumnModel().getColumn(0).setResizable(false);
        tbConsumo.getColumnModel().getColumn(1).setResizable(false);
        tbConsumo.getColumnModel().getColumn(2).setResizable(false); 
        tbConsumo.getColumnModel().getColumn(3).setResizable(false); 
        tbConsumo.getColumnModel().getColumn(4).setResizable(false); 
         
        //tbContatos.getColumnModel().getColumn(2),setCellRenderer(alinharDireita);
        
        // muda de lugar os campos
        tbConsumo.getTableHeader().setReorderingAllowed(false);
                        
        scroll = new JScrollPane();
        scroll.setViewportView(tbConsumo);
        scroll.setBounds(10,70,770,790);
        add(scroll);
    }

    private void setEvents() {
        btEdit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int i[] = tbConsumo.getSelectedRows();
                if (i.length == 0){
                    JOptionPane.showMessageDialog(null, "Selecione um cliente!");
                  
                }else if (i.length > 1){
                    JOptionPane.showMessageDialog(null, "Selecione apenas um cliente!");
                }else {
//                    Consumo consumo = new Consumo();
//                    consumo.setCon_id((int)tbConsumo.getValueAt(i[0], 0));
//                    consumo.setCliente(cliente)tbConsumo.getValueAt(i[0], 1);
//                    consumo.setFuncionario(funcionario)tbConsumo.getValueAt(i[0], 2));
//                    consumo.setProduto(produto)tbConsumo.getValueAt*i[0],3));
//                    consumo.setCon_valor_t((Float) tbConsumo.getValueAt(i[0], 4));
//                    FormConsumoVi form = new FormConsumoVi(consumo,model);
//                    form.open();
                }
            }
        });
        btNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                FormConsumoVi form = new FormConsumoVi(null, model);
//                form.open();
            }
        });
    
    }
    
    private void loadTable(){
        model.setRowCount(0);
//        for(Consumo consumo : new ConsumoCTRL().listar(null)){
//          model.addRow(new Object[]{consumo.getCon_id(),consumo.getCliente(),consumo.getFuncionario(),consumo.getProduto(),consumo.getCon_valor_t()});
//        }
//    }
    }
    public static void main(String[] args) {
        ConsumoVi frame = new ConsumoVi();
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        Dimension window = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (window.width - frame.getSize().width)/2;
        int y = (window.height - frame.getSize().height)/2;
        frame.setLocation(x,y);
        frame.setVisible(true);
    }
//    
}
