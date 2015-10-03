package view;

import interfaces.IJFrame;
import java.awt.Dimension;
import java.awt.Toolkit;

import java.io.File;

import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import view.component.PandaProdButton;
import view.component.PandaProdButtonAttach;
import view.component.PandaProdColor;
import view.component.PandaProdFrame;
import view.component.PandaProdLabel;
import view.component.PandaProdPanel;
import view.component.PandaProdPasswordField;
import view.component.PandaProdTextField;

public class LoginPPFrame extends PandaProdFrame implements IJFrame {

	private static final long serialVersionUID = -1283545032999719731L;

	private PandaProdPanel logoPanel;
    
    private PandaProdButton pandaProdButtonInscription;
    private PandaProdButton pandaProdButtonLogin;
    
    private PandaProdButtonAttach pandaProdButtonSendLogin; //pour le style souligné du bouton
    private PandaProdButtonAttach pandaProdButtonSendPassword;
    
    private PandaProdLabel pandaProdLabelLogin;
    private PandaProdLabel pandaProdLabelPassword;
    
    private PandaProdPasswordField pandaProdPasswordFieldPassword;
    
    private PandaProdTextField pandaProdTextFieldLogin;
    
    
    public LoginPPFrame() {
    	
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
    	
    	hsJcomponent.put("pandaProdButtonLogin", pandaProdButtonLogin);
        hsJcomponent.put("pandaProdButtonInscription", pandaProdButtonInscription);
        hsJcomponent.put("pandaProdButtonSendLogin", pandaProdButtonSendLogin);
        hsJcomponent.put("pandaProdButtonSendPassword", pandaProdButtonSendPassword);
        hsJcomponent.put("pandaProdLabelLogin", pandaProdLabelLogin);
        hsJcomponent.put("pandaProdLabelPassword", pandaProdLabelPassword);
        hsJcomponent.put("pandaProdPasswordFieldPassword", pandaProdPasswordFieldPassword);
        hsJcomponent.put("pandaProdTextFieldLogin", pandaProdTextFieldLogin);
        
    }
    
    private void configFrame(){
    	
    	setTitle("Login");
    	
    	setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        setSize(720, 480);
        setResizable(false);
        
        pandaProdButtonLogin.setPreferredSize(pandaProdButtonInscription.getSize());
	
	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	setLocation((dim.width / 2) - (getSize().width/2), (dim.height / 2) - (getSize().height / 2));
        
        pandaProdPasswordFieldPassword.setPreferredSize(new Dimension(pandaProdTextFieldLogin.getSize().width + 45, pandaProdPasswordFieldPassword.getSize().height));
        pandaProdPasswordFieldPassword.setSize(new Dimension(pandaProdTextFieldLogin.getSize().width + 45, pandaProdTextFieldLogin.getSize().height));
        pandaProdTextFieldLogin.setPreferredSize(new Dimension(pandaProdTextFieldLogin.getSize().width + 45, pandaProdTextFieldLogin.getSize().height));
                
    }

    private void initComponents() {

        pandaProdTextFieldLogin = new view.component.PandaProdTextField();
        pandaProdPasswordFieldPassword = new view.component.PandaProdPasswordField();
        pandaProdLabelLogin = new view.component.PandaProdLabel();
        pandaProdLabelPassword = new view.component.PandaProdLabel();
        pandaProdButtonSendLogin = new view.component.PandaProdButtonAttach();
        pandaProdButtonSendPassword = new view.component.PandaProdButtonAttach();
        pandaProdButtonInscription = new view.component.PandaProdButton();
        pandaProdButtonLogin = new view.component.PandaProdButton();

        
       	logoPanel = new PandaProdPanel(new File("cookieSwipe.png"));
       	logoPanel.setBackground(PandaProdColor.BACKGROUND_FRAME);
       	
        pandaProdLabelLogin.setText("Login");

        pandaProdLabelPassword.setText("Password");

        pandaProdButtonLogin.setText("Connexion");
        pandaProdButtonLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pandaProdButtonLoginActionPerformed(evt);
            }
        });

        pandaProdButtonSendPassword.setText("<html><u>Mot de passe oublié</u></html>");

        pandaProdButtonSendLogin.setText("<html><u>Identifiant oublié</u></html>");

        pandaProdButtonInscription.setText("Inscription");

    }
    
    private void placeComponents(){
    	
    	GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            		.addGroup(layout.createSequentialGroup()
        				.addGap(220, 220, 220)
        				.addComponent(logoPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
    				)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(258, 258, 258)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(pandaProdLabelPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(pandaProdLabelLogin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(pandaProdTextFieldLogin, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                            .addComponent(pandaProdPasswordFieldPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
                        )
                    )
                    .addGroup(layout.createSequentialGroup()
                        .addGap(258, 258, 258)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(pandaProdButtonLogin,  GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addGap(20, 20, 20)
                                .addComponent(pandaProdButtonInscription, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            )
                        )
                    )
                    
                    .addGroup(layout.createSequentialGroup()
                		.addGap(480, 480, 480)
                		.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            				.addGroup(layout.createSequentialGroup()
        						.addComponent(pandaProdButtonSendLogin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addGap(20, 20, 20)
        						.addComponent(pandaProdButtonSendPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
    						)
                        )
                    )
                )
            )
        );
        
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
            	.addGap(55, 55, 55)
        		.addComponent(logoPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(pandaProdLabelLogin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pandaProdTextFieldLogin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pandaProdLabelPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pandaProdPasswordFieldPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(pandaProdButtonLogin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(pandaProdButtonInscription, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                )
                .addGap(85, 85, 85)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	        		.addComponent(pandaProdButtonSendLogin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	                .addComponent(pandaProdButtonSendPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                )
            )
        );
        
    }

    private void pandaProdButtonLoginActionPerformed(java.awt.event.ActionEvent evt) {
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
                new LoginPPFrame().setVisible(true);
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
