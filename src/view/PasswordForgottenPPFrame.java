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

public class PasswordForgottenPPFrame extends PandaProdFrame implements IJFrame {

	private static final long serialVersionUID = 1691242087514507446L;

	private PandaProdButton pandaProdButtonSendPassword;
    
    private PandaProdLabel pandaProdLabelLogin;
    private PandaProdLabel pandaProdLabelMailAddress;
    
    private PandaProdTextField pandaProdTextFieldMailAddress;
    private PandaProdTextField pandaProdTextFieldLogin;

    
    public PasswordForgottenPPFrame() {
    	
        initFrame();
        
    }

    private void initFrame(){
    	
    	initComponents();
    	placeComponents();
    	
    	putComponents();
    	configFrame();
    	
    	refresh();
    }
    
    private void putComponents(){
    	
    	hsJcomponent.put("pandaProdButtonSendPassword", pandaProdButtonSendPassword);
        hsJcomponent.put("pandaProdLabelLogin", pandaProdLabelLogin);
        hsJcomponent.put("pandaProdLabelMailAddress", pandaProdLabelMailAddress);
        hsJcomponent.put("pandaProdTextFieldMailAddress", pandaProdTextFieldMailAddress);
        hsJcomponent.put("pandaProdTextFieldLogin", pandaProdTextFieldLogin);
        
    }
    
    private void configFrame(){
    	
    	setTitle("Mot de passe oublié");
    	
    	setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        setSize(480, 360);
        setResizable(false);
    	
    }

    private void initComponents() {

        pandaProdTextFieldLogin = new PandaProdTextField();
        pandaProdTextFieldMailAddress = new PandaProdTextField();
        pandaProdLabelLogin = new PandaProdLabel();
        pandaProdLabelMailAddress = new PandaProdLabel();
        pandaProdButtonSendPassword = new PandaProdButton();

        pandaProdLabelLogin.setText("Login");
        pandaProdLabelMailAddress.setText("Adresse mail");
        pandaProdButtonSendPassword.setText("Envoyer mot de passe");

    }
    
    private void placeComponents(){
    	
    	GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(160, 160, 160)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(pandaProdLabelMailAddress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(pandaProdLabelLogin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(pandaProdTextFieldLogin, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pandaProdTextFieldMailAddress, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)
                        )
                    )
                    .addGroup(layout.createSequentialGroup()
                        .addGap(144, 144, 144)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(pandaProdButtonSendPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                            )
                        )
                    )
                )
            )
        );
        
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(pandaProdLabelLogin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pandaProdTextFieldLogin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pandaProdLabelMailAddress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pandaProdTextFieldMailAddress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(pandaProdButtonSendPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                )
            )
        );
        
    }

    private void pandaProdButtonLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pandaProdButtonLoginActionPerformed
        // TODO add your handling code here:
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
                new PasswordForgottenPPFrame().setVisible(true);
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
