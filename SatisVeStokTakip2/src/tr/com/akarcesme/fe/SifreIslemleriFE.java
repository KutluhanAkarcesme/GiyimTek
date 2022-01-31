package tr.com.akarcesme.fe;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
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
import PrototypePattern.prototype;
import tr.com.akarcesme.dal.AccountsDal;
import tr.com.akarcesme.dal.PersonelDal;
import tr.com.akarcesme.dal.YetkilerDal;
import tr.com.akarcesme.interfaces.FeInterfaces;
import tr.com.akarcesme.types.AccountsContract;
import tr.com.akarcesme.types.PersonelContract;
import tr.com.akarcesme.types.YetkilerContract;

public class SifreIslemleriFE extends JDialog implements FeInterfaces {

	public SifreIslemleriFE() {
		initPencere();
	}

	@Override
	public void initPencere() {
		JPanel panel = initPanel();
		panel.setBorder(BorderFactory.createTitledBorder("Hesap Ýþlemleri"));

		add(panel);
		setTitle("Hesap Kayýt Ýþlemleri");
		pack();
		setModalityType(DEFAULT_MODALITY_TYPE);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE); 

	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new GridLayout(5, 2));
		JLabel personelLabel = new JLabel("Personel Seç:", JLabel.RIGHT);
		panel.add(personelLabel);
		JComboBox personelBox = new JComboBox(new PersonelDal().GetAll().toArray());
		panel.add(personelBox);
		JLabel yetkiLabel = new JLabel("Yetki Seç:", JLabel.RIGHT);
		panel.add(yetkiLabel);
		JComboBox yetkiBox = new JComboBox(new YetkilerDal().GetAll().toArray());
		panel.add(yetkiBox);
		yetkiBox.insertItemAt("--Yetki Seçiniz--", 0);
		yetkiBox.setSelectedIndex(0);
		JLabel passwordLabel = new JLabel("Þifre Belirle:", JLabel.RIGHT);
		panel.add(passwordLabel);
		JPasswordField passField = new JPasswordField(10);
		panel.add(passField);
		JLabel passTekrarLabel = new JLabel("Þifre Tekrar:", JLabel.RIGHT);
		panel.add(passTekrarLabel);
		JPasswordField passTekrar = new JPasswordField(10);
		panel.add(passTekrar);

		JButton kaydetButton = new JButton("Kaydet");
		panel.add(kaydetButton);
		JButton iptalButton = new JButton("Ýptal");
		panel.add(iptalButton);

		kaydetButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				PersonelContract xContract = (PersonelContract) personelBox.getSelectedItem();
				boolean control = true;
				for (AccountsContract compContract : new AccountsDal().GetPersonelId()) {
					if (compContract.getPersonelId() == xContract.getId()) {
						control = false;

					}

				}
				if (control == true) {
					AccountsContract contract = new AccountsContract();
					if (yetkiBox.getSelectedIndex() == 0
							|| passField.getText().matches("") || passTekrar.getText().matches("")) {
						JOptionPane.showMessageDialog(null, "!!!Lütfen tüm bilgileri giriniz");
					} else if (passField.getText().equals(passTekrar.getText())) {
						PersonelContract pContract = (PersonelContract) personelBox.getSelectedItem();
						YetkilerContract yContract = (YetkilerContract) yetkiBox.getSelectedItem();

						contract.setPersonelId(pContract.getId());
						contract.setYetkiId(yContract.getId());
						contract.setSifre(passField.getText());

						new AccountsDal().Insert(contract);
						JOptionPane.showMessageDialog(null, pContract.getAdiSoyadi() + " adlý kiþiye "
								+ yContract.getAdi() + " yetkisi baþarýyla atanmýþtýr.");
						
					} else {
						JOptionPane.showMessageDialog(null, "Þifreler uyuþmuyor kontrol ediniz");
					}

				} else {
					JOptionPane.showMessageDialog(null,
							"Seçtiðiniz personele ait bir hesap zaten var.Yeni personel seçiniz");
				}
			}

		});
		iptalButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				initPencere();
				dispose();
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
