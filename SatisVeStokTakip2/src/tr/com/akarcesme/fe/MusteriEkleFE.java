package tr.com.akarcesme.fe;

import java.awt.BorderLayout;


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

import org.w3c.dom.ls.LSOutput;

import tr.com.akarcesme.dal.MusteriDal;
import tr.com.akarcesme.dal.illerDal;
import tr.com.akarcesme.interfaces.FeInterfaces;
import tr.com.akarcesme.types.MusteriContract;
import tr.com.akarcesme.types.IllerContract;

public class MusteriEkleFE extends JDialog implements FeInterfaces {

	public MusteriEkleFE() {
		initPencere();
	}

	@Override
	public void initPencere() {
		JPanel panel = initPanel();

		add(panel);
		setTitle("Müþteri Ekle");
		pack();
		setLocationRelativeTo(null);
		setModalityType(DEFAULT_MODALITY_TYPE);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createTitledBorder("Müþteri Ekleme Ýþlemleri"));
		JPanel fieldPanel = new JPanel(new GridLayout(4, 2));
		fieldPanel.setBorder(BorderFactory.createTitledBorder("Müþteri Bilgileri"));
		JPanel buttonPanel = new JPanel(new GridLayout(1, 2));

		JLabel adiSoyadiLabel = new JLabel("Müþteri Adý:", JLabel.RIGHT);
		fieldPanel.add(adiSoyadiLabel);
		JTextField adiSoyadiField = new JTextField(15);
		fieldPanel.add(adiSoyadiField);
		JLabel telefonLabel = new JLabel("Telefon:", JLabel.RIGHT);
		fieldPanel.add(telefonLabel);
		JTextField telefonField = new JTextField(15);
		fieldPanel.add(telefonField);
		JLabel sehirSecLabel = new JLabel("Þehir Seç:", JLabel.RIGHT);
		fieldPanel.add(sehirSecLabel);
		JComboBox sehirlerBox = new JComboBox(new illerDal().GetAll().toArray());
		fieldPanel.add(sehirlerBox);
		sehirlerBox.insertItemAt("--Þehir Seçiniz--", 0);
		sehirlerBox.setSelectedIndex(0);
		JLabel adresLabel = new JLabel("Adres:");
		fieldPanel.add(adresLabel);
		JTextArea adresArea = new JTextArea(7, 1);
		JScrollPane pane = new JScrollPane(adresArea);
		pane.setBorder(BorderFactory.createTitledBorder("Adres Bilgisi"));

		JButton kaydetButton = new JButton("Kaydet");
		buttonPanel.add(kaydetButton);
		JButton iptalButton = new JButton("Ýptal");
		buttonPanel.add(iptalButton);
		kaydetButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MusteriContract contract = new MusteriContract();

				if (sehirlerBox.getSelectedIndex() == 0 || adiSoyadiField.getText().matches("")
						|| telefonField.getText().matches("") || adresArea.getText().matches("")) {
					JOptionPane.showMessageDialog(null, "!!!Lütfen tüm bilgileri giriniz!!!");
				} else {
					IllerContract casContract = (IllerContract) sehirlerBox.getSelectedItem();

					contract.setMusteriAdi(adiSoyadiField.getText());
					contract.setTelefon(telefonField.getText());
					contract.setAdres(adresArea.getText());
					contract.setSehirId(casContract.getIlNo());

					new MusteriDal().Insert(contract);
					JOptionPane.showMessageDialog(null,
							contract.getMusteriAdi() + " adlý müþteri baþarýyla eklenmiþtir.");

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

		panel.add(fieldPanel, BorderLayout.NORTH);
		panel.add(pane, BorderLayout.CENTER);
		panel.add(buttonPanel, BorderLayout.SOUTH);
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
