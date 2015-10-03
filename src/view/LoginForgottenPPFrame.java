package view;

import interfaces.IJFrame;

import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import view.component.PandaProdButton;
import view.component.PandaProdFrame;
import view.component.PandaProdLabel;
import view.component.PandaProdTextField;

public class LoginForgottenPPFrame extends PandaProdFrame implements IJFrame {

    private static final long serialVersionUID = 4729753654116728888L;
    private PandaProdButton pandaProdButtonSendLogin;
    private PandaProdLabel pandaProdLabelMailAddress;
    private PandaProdTextField pandaProdTextFieldMailAddress;

    public LoginForgottenPPFrame() {

        initFrame();
    }
    
    private void initFrame(){
    	
    	initComponents();
    	placeComponents();
    	
    	putComponents();
    	configFrame();
    	
    	refresh();
    	
    }
    
    private void configFrame(){
    	
    	setTitle("Login oublié");
    	
    	setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        setSize(360, 240);
        setResizable(false);
        
    }
    
    private void putComponents(){
    	
    	hsJcomponent.put("pandaProdButtonSendLogin", pandaProdButtonSendLogin);
        hsJcomponent.put("pandaProdLabelLogin", pandaProdLabelMailAddress);
        hsJcomponent.put("pandaProdTextFieldMailAddress", pandaProdTextFieldMailAddress);
        
    }

    private void initComponents() {

        pandaProdTextFieldMailAddress = new view.component.PandaProdTextField();
        pandaProdLabelMailAddress = new view.component.PandaProdLabel();
        pandaProdButtonSendLogin = new view.component.PandaProdButton();

        pandaProdLabelMailAddress.setText("Adresse mail");
        pandaProdButtonSendLogin.setText("Envoyer login");

    }
    
    private void placeComponents(){
    	
    	GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(pandaProdLabelMailAddress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(pandaProdTextFieldMailAddress, GroupLayout.PREFERRED_SIZE, 154, Short.MAX_VALUE)
                        )
                    )
                    .addGroup(layout.createSequentialGroup()
                        .addGap(127, 127, 127)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(pandaProdButtonSendLogin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            )
                        )
                    )
                )
            )
        );
        
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(pandaProdLabelMailAddress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pandaProdTextFieldMailAddress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(pandaProdButtonSendLogin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                )
            )
        );
        
    }

    private void pandaProdButtonLoginActionPerformed(java.awt.event.ActionEvent evt) {
    }

    public static void main(String args[]) {
    	
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoginPPFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginPPFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginPPFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginPPFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginForgottenPPFrame().setVisible(true);
            }
        });
        
    }

    @Override
    public void refresh() {
    	
    	validate();
        repaint();
        revalidate();
        
    }
    
}