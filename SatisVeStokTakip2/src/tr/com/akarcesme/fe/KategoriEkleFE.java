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
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import tr.com.akarcesme.dal.KategoriDal;
import tr.com.akarcesme.interfaces.FeInterfaces;
import tr.com.akarcesme.types.KategoriContract;

public class KategoriEkleFE extends JDialog implements FeInterfaces {

	public KategoriEkleFE() {
		initPencere();
	}

	@Override
	public void initPencere() {
		JPanel panel = initPanel();
		panel.setBorder(BorderFactory.createTitledBorder("Kategori Ekle"));

		add(panel);
		setTitle("Kategori Ekle");
		pack();
		setModalityType(DEFAULT_MODALITY_TYPE);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new GridLayout(3, 2));

		JLabel kategoriAdiLabel = new JLabel("Kategori Adý:", JLabel.RIGHT);
		panel.add(kategoriAdiLabel);
		JTextField adiField = new JTextField(10);
		panel.add(adiField);
		JLabel parentLabel = new JLabel("ParentId:", JLabel.RIGHT);
		panel.add(parentLabel);
		JTextField parentField = new JTextField(10);
		panel.add(parentField);
		JButton kaydetButton = new JButton("Kaydet");
		panel.add(kaydetButton);
		JButton iptalButton = new JButton("Ýptal");
		panel.add(iptalButton);
		kaydetButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				KategoriContract contract = new KategoriContract();

				if (adiField.getText().matches("") || parentField.getText().matches("")) {
					JOptionPane.showMessageDialog(null, "!!!Lütfen gerekli bilgileri giriniz!!!");
				} else {
					contract.setAdi(adiField.getText());
					contract.setParentId(Integer.parseInt(parentField.getText()));

					new KategoriDal().Insert(contract);
					JOptionPane.showMessageDialog(null, contract.getAdi() + " adlý kategori baþarýyla eklenmiþtir.");
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
