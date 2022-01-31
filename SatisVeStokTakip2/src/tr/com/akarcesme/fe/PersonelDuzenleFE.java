package tr.com.akarcesme.fe;

import java.awt.BorderLayout;


import javax.swing.JDialog;

import tr.com.akarcesme.interfaces.FeInterfaces;
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
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import tr.com.akarcesme.dal.PersonelDal;
import tr.com.akarcesme.types.PersonelContract;



public class PersonelDuzenleFE extends JDialog implements FeInterfaces {

	public PersonelDuzenleFE() {

		initPencere();
	}

	@Override
	public void initPencere() {
		JPanel panel = initPanel();

		add(panel);
		setTitle("Personel Düzenle");
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
		setModalityType(DEFAULT_MODALITY_TYPE);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new BorderLayout());

		panel.setBorder(BorderFactory.createTitledBorder("Düzenleme Ýþlemleri"));
		JPanel ustPanel = new JPanel(new GridLayout(4, 2));
		ustPanel.setBorder(BorderFactory.createTitledBorder("Personel Düzenle"));
		JLabel personelBoxLabel = new JLabel("Personeller:", JLabel.RIGHT);
		ustPanel.add(personelBoxLabel);
		JComboBox personelBox = new JComboBox(new PersonelDal().GetAll().toArray());
		ustPanel.add(personelBox);
		personelBox.insertItemAt("--Personel Seçiniz--", 0);
		personelBox.setSelectedIndex(0);
		JLabel personelLabel = new JLabel("Personel Adý:", JLabel.RIGHT);
		ustPanel.add(personelLabel);
		JTextField personelAdiField = new JTextField(10);
		ustPanel.add(personelAdiField);
		JLabel eMailLabel = new JLabel("Email:", JLabel.RIGHT);
		ustPanel.add(eMailLabel);
		JTextField eMailField = new JTextField(10);
		ustPanel.add(eMailField);

		JButton kaydetButton = new JButton("Kaydet");
		ustPanel.add(kaydetButton);
		JButton ipatalButton = new JButton("Ýptal");
		ustPanel.add(ipatalButton);
		kaydetButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				PersonelContract contract = new PersonelContract();
				
				if (personelBox.getSelectedIndex() == 0 || personelAdiField.getText().matches("")
						|| eMailField.getText().matches("")) {
					JOptionPane.showMessageDialog(null, "!!!Lütfen tüm bilgileri giriniz!!!");
				} else {
					PersonelContract pContract = (PersonelContract) personelBox.getSelectedItem();

					contract.setId(pContract.getId());
					contract.setAdiSoyadi(personelAdiField.getText());
					contract.setEmail(eMailField.getText());

					new PersonelDal().update(contract);
					JOptionPane.showMessageDialog(null,
							pContract.getAdiSoyadi() + " adlý personelin bilgileri baþarýyla güncellenmiþtir.");
				}
			}

		});
		ipatalButton.addActionListener(new ActionListener() {

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
