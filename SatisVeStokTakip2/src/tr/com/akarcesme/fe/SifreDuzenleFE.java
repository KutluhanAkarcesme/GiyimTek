package tr.com.akarcesme.fe;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import tr.com.akarcesme.dal.AccountsDal;
import tr.com.akarcesme.dal.MusteriDal;
import tr.com.akarcesme.dal.PersonelDal;
import tr.com.akarcesme.dal.YetkilerDal;
import tr.com.akarcesme.interfaces.FeInterfaces;
import tr.com.akarcesme.types.AccountsContract;
import tr.com.akarcesme.types.PersonelContract;
import tr.com.akarcesme.types.YetkilerContract;

public class SifreDuzenleFE extends JDialog implements FeInterfaces {

	public SifreDuzenleFE() {

		initPencere();
	}

	@Override
	public void initPencere() {
		JPanel panel = initPanel();

		add(panel);
		setTitle("Hesap D�zenle");
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
		setModalityType(DEFAULT_MODALITY_TYPE);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new BorderLayout());

		panel.setBorder(BorderFactory.createTitledBorder("D�zenleme ��lemleri"));
		JPanel ustPanel = new JPanel(new GridLayout(5, 2));
		ustPanel.setBorder(BorderFactory.createTitledBorder("Hesap D�zenle"));
		JLabel accountBoxLabel = new JLabel("Hesap Se�:", JLabel.RIGHT);
		ustPanel.add(accountBoxLabel);
		JComboBox accountBox = new JComboBox(new PersonelDal().GetAll().toArray());
		ustPanel.add(accountBox);
		accountBox.insertItemAt("--Hesap Se�iniz--", 0);
		accountBox.setSelectedIndex(0);
		JLabel yetkiBoxLabel = new JLabel("Yetkiler:", JLabel.RIGHT);
		ustPanel.add(yetkiBoxLabel);
		JComboBox yetkiBox = new JComboBox(new YetkilerDal().GetAll().toArray());
		ustPanel.add(yetkiBox);
		yetkiBox.insertItemAt("--Yetki Se�iniz--", 0);
		yetkiBox.setSelectedIndex(0);
		JLabel passwordLabel = new JLabel("Yeni �ifre Belirle:", JLabel.RIGHT);
		ustPanel.add(passwordLabel);
		JPasswordField passField = new JPasswordField(10);
		ustPanel.add(passField);
		JLabel passTekrarLabel = new JLabel("Yeni �ifre Tekrar:", JLabel.RIGHT);
		ustPanel.add(passTekrarLabel);
		JPasswordField passTekrar = new JPasswordField(10);
		ustPanel.add(passTekrar);

		JButton kaydetButton = new JButton("Kaydet");
		ustPanel.add(kaydetButton);
		JButton iptalButton = new JButton("�ptal");
		ustPanel.add(iptalButton);
		kaydetButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AccountsContract contract = new AccountsContract();
				if (accountBox.getSelectedIndex() == 0 || yetkiBox.getSelectedIndex() == 0
						|| passField.getText().matches("") || passTekrar.getText().matches("")) {
					JOptionPane.showMessageDialog(null, "!!!L�tfen t�m bilgileri giriniz!!!");
				} else if (passField.getText().equals(passTekrar.getText())) {
					PersonelContract pContact = (PersonelContract) accountBox.getSelectedItem();
					YetkilerContract yContract = (YetkilerContract) yetkiBox.getSelectedItem();
					contract.setPersonelId(pContact.getId());
					contract.setYetkiId(yContract.getId());
					contract.setSifre(passField.getText());

					new AccountsDal().update(contract);
					JOptionPane.showMessageDialog(null,
							pContact.getAdiSoyadi() + " adl� hesab�n bilgileri ba�ar�yla g�ncellenmi�tir.");
				} else {
					JOptionPane.showMessageDialog(null, "�ifreler uyu�muyor kontrol ediniz");
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

		panel.add(ustPanel, BorderLayout.NORTH);

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
