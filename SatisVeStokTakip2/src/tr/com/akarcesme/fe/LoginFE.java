package tr.com.akarcesme.fe;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;

import tr.com.akarcesme.dal.AccountsDal;
import tr.com.akarcesme.dal.PersonelDal;
import tr.com.akarcesme.interfaces.FeInterfaces;
import tr.com.akarcesme.types.PersonelContract;

public class LoginFE extends JDialog implements FeInterfaces {
	public static JComboBox kullaniciAdiBox;

	public LoginFE() {
		initPencere();

	}

	@Override
	public void initPencere() {
		JPanel panel = initPanel();

		panel.setBorder(BorderFactory.createTitledBorder("Lütfen sisteme giriþ yapmak için bilgilerinizi giriniz."));
		add(panel);
		setTitle("Lütfen giriþ Yapýnýz");
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE); 

	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new GridLayout(3, 2));

		JLabel emailLabel = new JLabel("Kullanýcý Adý:", JLabel.RIGHT);
		panel.add(emailLabel);
		kullaniciAdiBox = new JComboBox(new PersonelDal().GetAll().toArray());
		panel.add(kullaniciAdiBox);
		JLabel passwordLabel = new JLabel("Þifre:", JLabel.RIGHT);
		panel.add(passwordLabel);
		JPasswordField passwordField = new JPasswordField(15);
		panel.add(passwordField);

		JButton loginButton = new JButton("Giriþ Yap");
		panel.add(loginButton);
		JButton iptalButton = new JButton("Ýptal");
		panel.add(iptalButton);

		loginButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				PersonelContract contract = (PersonelContract) kullaniciAdiBox.getSelectedItem();
				String sifre = passwordField.getText();

				if (new AccountsDal().GetPersonelIdVeSifre(contract.getId(), sifre).getId() != 0) {
					new AnaPencereFE();
					initPencere();dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Giriþ Baþarýsýz");
				}
			}
		});
		iptalButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				initPencere();dispose();
				
			}
		});

		return panel;
	}

	@Override
	public JMenuBar initBar() {
		return null;
	}

	@Override
	public JTabbedPane initTabs() {
		return null;
	}

}
