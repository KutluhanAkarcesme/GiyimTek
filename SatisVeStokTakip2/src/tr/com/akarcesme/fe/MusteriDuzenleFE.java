package tr.com.akarcesme.fe;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import tr.com.akarcesme.dal.KategoriDal;
import tr.com.akarcesme.dal.MusteriDal;
import tr.com.akarcesme.dal.illerDal;
import tr.com.akarcesme.interfaces.FeInterfaces;
import tr.com.akarcesme.types.IllerContract;
import tr.com.akarcesme.types.MusteriContract;

public class MusteriDuzenleFE extends JDialog implements FeInterfaces {

	public MusteriDuzenleFE() {
		initPencere();
	}

	@Override
	public void initPencere() {
		JPanel panel = initPanel();

		add(panel);
		setTitle("Müþteri Düzenle");
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
		JPanel ustPanel = new JPanel(new GridLayout(5, 2));
		ustPanel.setBorder(BorderFactory.createTitledBorder("Müþteri Düzenle"));
		JPanel buttonPanel = new JPanel(new GridLayout(1, 2));

		JLabel musteriBoxLabel = new JLabel("Müþteriler:", JLabel.RIGHT);
		ustPanel.add(musteriBoxLabel);
		JComboBox musteriBox = new JComboBox(new MusteriDal().GetAll().toArray());
		ustPanel.add(musteriBox);
		musteriBox.insertItemAt("--Müþteri Seçiniz--", 0);
		musteriBox.setSelectedIndex(0);
		JLabel musteriAdiLabel = new JLabel("Müþteri Adý:", JLabel.RIGHT);
		ustPanel.add(musteriAdiLabel);
		JTextField musteriAdiField = new JTextField(10);
		ustPanel.add(musteriAdiField);
		JLabel telefonLabel = new JLabel("Telefon:", JLabel.RIGHT);
		ustPanel.add(telefonLabel);
		JTextField telefonField = new JTextField(15);
		ustPanel.add(telefonField);
		JLabel sehirSecLabel = new JLabel("Þehir Seç:", JLabel.RIGHT);
		ustPanel.add(sehirSecLabel);
		JComboBox sehirlerBox = new JComboBox(new illerDal().GetAll().toArray());
		ustPanel.add(sehirlerBox);
		sehirlerBox.insertItemAt("--Þehir Seçiniz--", 0);
		sehirlerBox.setSelectedIndex(0);
		JLabel adresLabel = new JLabel("Adres:");
		ustPanel.add(adresLabel);
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

				if (musteriBox.getSelectedIndex() == 0 || sehirlerBox.getSelectedIndex() == 0
						|| musteriAdiField.getText().matches("") || telefonField.getText().matches("")
						|| adresArea.getText().matches("")) {
					JOptionPane.showMessageDialog(null, "!!!Lütfen tüm bilgileri giriniz!!!");
				} else {
					MusteriContract mContract = (MusteriContract) musteriBox.getSelectedItem();
					IllerContract casContract = (IllerContract) sehirlerBox.getSelectedItem();

					contract.setId(mContract.getId());
					contract.setMusteriAdi(musteriAdiField.getText());
					contract.setTelefon(telefonField.getText());
					contract.setSehirId(casContract.getIlNo());
					contract.setAdres(adresArea.getText());

					new MusteriDal().update(contract);
					JOptionPane.showMessageDialog(null,
							mContract.getMusteriAdi() + " adlý müþterinin bilgileri baþarýyla güncellenmiþtir.");

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
