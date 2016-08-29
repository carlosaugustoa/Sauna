package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class MenuPrincipal extends JFrame{
    private Container contentPane;
    private JMenuBar mnBarra;
    private JMenu mnArquivo, mnExemplos;
    private JMenuItem miSair, miCliente, miFuncionario, miProduto, miConsumo;
     
public MenuPrincipal(){
    inicializarComponentes();
    definirEventos();       
    }    
   
    private void inicializarComponentes(){
        setTitle("S A U N A");
        setBounds(0,0,800,500);
        contentPane = getContentPane();
        setResizable(false);
        
        mnBarra = new JMenuBar();
        mnArquivo = new JMenu("S A I R");
        mnExemplos = new JMenu("MENU PRINCIPAL");
        miSair = new JMenuItem("SAIR");
        miCliente = new JMenuItem("CLIENTES");
        miFuncionario = new JMenuItem("FUNCIONÁRIOS");
        miProduto = new JMenuItem("PRODUTOS");
        miConsumo = new JMenuItem("LISTA");
               
        mnArquivo.setMnemonic('A');
        mnExemplos.setMnemonic('E');
       
        mnArquivo.add(miSair);
        mnExemplos.add(miCliente);
        mnExemplos.add(miFuncionario);
        mnExemplos.add(miProduto);
        mnExemplos.add(miConsumo);
        mnBarra.add(mnArquivo);
        mnBarra.add(mnExemplos);
        setJMenuBar(mnBarra);
    }
    
   private void definirEventos(){
        miSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
       
        miCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClienteVi cliente = new ClienteVi();
                contentPane.removeAll();
                contentPane.add(cliente);
                contentPane.validate();
            }
        });
              
        miProduto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProdutoVi produto = new ProdutoVi();
                contentPane.removeAll();
                contentPane.add(produto);
                contentPane.validate();
            }
        });
       
        miConsumo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConsumoVi consumo = new ConsumoVi();
                contentPane.removeAll();
                contentPane.add(consumo);
                contentPane.validate();
            }
        });
             
        miFuncionario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FuncionarioVi funcionario = new FuncionarioVi();
                contentPane.removeAll();
                contentPane.add(funcionario); 
                contentPane.validate();
            }
        });
    }
      
 public static void run(){
    MenuPrincipal frame = new MenuPrincipal();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (tela.width - frame.getSize().width)/2;
    int y = (tela.height - frame.getSize().height)/2;
    frame.setLocation(x,y);
    frame.setVisible(true);
    }  
}