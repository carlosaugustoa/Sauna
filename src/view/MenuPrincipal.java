package view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class MenuPrincipal extends JFrame{
  
    private static MenuPrincipal frame;
    private JLabel lbLogin,lbSenha;
    private JTextField tfLogin;
    private JPasswordField pfSenha;
    private JButton btLogar,btCancelar;
       
    public MenuPrincipal(){
        inicializarComponentes();
        definirEventos();  
    }
    
    private void inicializarComponentes(){
        setTitle ("Login no sistema");
        setBounds(0, 0, 250, 200);
        setLayout(null);
        
        lbLogin = new JLabel ("Login");
        lbSenha = new JLabel ("Senha");
        tfLogin = new JTextField (5);
        pfSenha = new JPasswordField(5);
        btLogar = new JButton ("Logar");
        btCancelar = new JButton ("Cancelar");
        
        lbLogin.setBounds(30,30,80,25);
        lbSenha.setBounds(30,75,80,25);
        tfLogin.setBounds(100,30,120,25);
        pfSenha.setBounds(100,75,120,25);
        btLogar.setBounds(20,120,100,25);
        btCancelar.setBounds(125, 120, 100, 25);
        
            add(lbLogin);
            add(lbSenha);
            add(tfLogin);
            add(pfSenha);
            add(btLogar);
            add(btCancelar);
             
    }
    
    private void definirEventos(){
        btLogar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = tfLogin.getText();
                String senha = String.valueOf(pfSenha.getPassword());
                if (login.equals("") && senha.equals("")){
                    frame.setVisible(false);
                    GuiMenuPrincipal.run();
                }else {
                    JOptionPane.showMessageDialog(null, "Login ou senha incorretos!");
                }
            }
        });  
              
        btCancelar .addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
           
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() {
            frame = new GuiLogin();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
              
            Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
            int x = (tela.width - frame.getSize().width)/2;
            int y = (tela.height - frame.getSize().height)/2;
            frame.setLocation(x,y);
            frame.setVisible(true);
        }
      });
            
    }
}