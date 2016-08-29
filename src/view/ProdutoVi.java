package view;

import controller.ProdutoCTRL;
import java.awt.Color;
import dao.ProdutoDao;
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
import model.Produto;

public class ProdutoVi extends JFrame {

    //private JTextField tfPesquisar;
    private JButton btNew, btEdit, btDelete;
    private JTable tbProdutos;
    private DefaultTableModel model;
    private JScrollPane scroll;
    private ProdutoDao dao;
    
    public ProdutoVi() {
        setComponents();
        setEvents();
        dao = new ProdutoDao();
    }

    private void setComponents() {
        setLayout(null);
//        setTitle("PRODUTO");
//        setResizable(false);
//        setIconImage(new ImageIcon("images/icons/produtos.png").getImage());
        setBounds(0, 0, 600, 600);
        
//        tfPesquisar = new JTextField();
//        tfPesquisar.setBounds(10, 10, 200, 32);
//        add(tfPesquisar);
        
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
        
        model = new DefaultTableModel(
                new Object[] {
                    "ID", "NOME", "VALOR"
                }, 0
        );
        loadTable();
        tbProdutos = new JTable(model);
        tbProdutos.setRowHeight(30);
        
        DefaultTableCellRenderer alinharDireita = new DefaultTableCellRenderer();
        alinharDireita.setHorizontalAlignment(SwingConstants.RIGHT);
        DefaultTableCellRenderer alinharCentro = new DefaultTableCellRenderer();
        alinharCentro.setHorizontalAlignment(SwingConstants.CENTER);
        
        tbProdutos.getColumnModel().getColumn(0).setPreferredWidth(100);
        tbProdutos.getColumnModel().getColumn(1).setPreferredWidth(350);
        tbProdutos.getColumnModel().getColumn(2).setPreferredWidth(100);
        
        tbProdutos.getColumnModel().getColumn(0).setResizable(false);
        tbProdutos.getColumnModel().getColumn(1).setResizable(false);
        tbProdutos.getColumnModel().getColumn(2).setResizable(false);
        
        tbProdutos.getColumnModel().getColumn(0).setCellRenderer(alinharCentro);
        //tbProdutos.getColumnModel().getColumn(2).setCellRenderer(alinharDireita);
        
        //tbProdutos.getTableHeader().setReorderingAllowed(false);
        
        scroll = new JScrollPane();
        scroll.setViewportView(tbProdutos);
        scroll.setBounds(10, 70, 570, 490);
        add(scroll);
    }

    private void setEvents() {
        btEdit.addActionListener(new ActionListener() {
           
            @Override
            public void actionPerformed(ActionEvent e) {
                int i[] = tbProdutos.getSelectedRows();
                if (i.length == 0) {
                    JOptionPane.showMessageDialog(null, "Selecione um produto!");
                    
                } else if (i.length > 1) {
                    JOptionPane.showMessageDialog(null, "Selecione apenas um produto!");
                } else {
                    Produto produto = new Produto();
                    produto.setPro_id((String) tbProdutos.getValueAt(i[0], 0));
                    produto.setPro_nome((String) tbProdutos.getValueAt(i[0], 1));
                    produto.setPro_valor((Float) tbProdutos.getValueAt(i[0], 2));
                    FormProdutoVi form = new FormProdutoVi(produto, model);
                    form.open();
                }
            }
        });
        btNew.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                FormProdutoVi form = new FormProdutoVi(null, model);
                form.open();
            }
        });
        btDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                int i[] = tbProdutos.getSelectedRows();
                if (i.length == 0) {
                    JOptionPane.showMessageDialog(null, "Selecione um produto!");
                } else if (i.length > 1) {
                    JOptionPane.showMessageDialog(null, "Selecione apenas um produto!");
                } else {
                    Produto produto = new Produto();
                    produto.setPro_id((String) tbProdutos.getValueAt(i[0], 0));
                    produto.setPro_nome((String) tbProdutos.getValueAt(i[0], 1));
                    produto.setPro_valor((Float) tbProdutos.getValueAt(i[0], 2));
                    
                    dao.delete(produto);
                    
                    FormProdutoVi form = new FormProdutoVi(produto,model);
                    int[] linhas = tbProdutos.getSelectedRows();
                    DefaultTableModel dtm = (DefaultTableModel) tbProdutos.getModel();
                    for(int j = (linhas.length-1); j>=0; j--){
                        dtm.removeRow(linhas[j]);
                    }
                }                
            }
        });
    }
    
    private void loadTable(){
        model.setRowCount(0);
        for(Produto produto : new ProdutoCTRL().listar(null)){
          model.addRow(new Object[]{produto.getPro_id(),produto.getPro_nome(),produto.getPro_valor()});
        }
    }
    
    public static void run(){
//    public static void main(String[] args) {
//        ClienteVi frame = new ClienteVi();
//        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
//        Dimension window = Toolkit.getDefaultToolkit().getScreenSize();
//        int x = (window.width - frame.getSize().width)/2;
//        int y = (window.height - frame.getSize().height)/2;
//        frame.setLocation(x,y);
//        frame.setVisible(true);
    }
    
}
