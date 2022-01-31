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
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import tr.com.akarcesme.dal.KategoriDal;
import tr.com.akarcesme.interfaces.FeInterfaces;
import tr.com.akarcesme.types.KategoriContract;

public class KategoriDuzenleFE extends JDialog implements FeInterfaces {

	public KategoriDuzenleFE() {

		initPencere();
	}

	@Override
	public void initPencere() {
		JPanel panel = initPanel();

		add(panel);
		setTitle("Kategori Düzenle");
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
		ustPanel.setBorder(BorderFactory.createTitledBorder("Kategori Düzenle"));
		JLabel kategoriBoxLabel = new JLabel("Kategoriler:", JLabel.RIGHT);
		ustPanel.add(kategoriBoxLabel);
		JComboBox kategoriBox = new JComboBox(new KategoriDal().GetAll().toArray());
		ustPanel.add(kategoriBox);
		kategoriBox.insertItemAt("--Kategori Seçiniz--", 0);
		kategoriBox.setSelectedIndex(0);
		JLabel kategoriLabel = new JLabel("Kategori Adý:", JLabel.RIGHT);
		ustPanel.add(kategoriLabel);
		JTextField kategoriAdiField = new JTextField(10);
		ustPanel.add(kategoriAdiField);
		JLabel parentLabel = new JLabel("ParentId:", JLabel.RIGHT);
		ustPanel.add(parentLabel);
		JTextField parentField = new JTextField(10);
		ustPanel.add(parentField);

		JButton kaydetButton = new JButton("Kaydet");
		ustPanel.add(kaydetButton);
		JButton iptalButton = new JButton("Ýptal");
		ustPanel.add(iptalButton);
		kaydetButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				KategoriContract contract = new KategoriContract();
				if (kategoriBox.getSelectedIndex() == 0 || kategoriAdiField.getText().matches("")
						|| parentField.getText().matches("")) {
					JOptionPane.showMessageDialog(null,
							"!!!Kategori,kategori adý ve parentId bilgileri boþ býrakýlamaz!!!");
				} else {
					KategoriContract kContract = (KategoriContract) kategoriBox.getSelectedItem();
					contract.setId(kContract.getId());
					contract.setAdi(kategoriAdiField.getText());
					contract.setParentId(Integer.parseInt(parentField.getText()));

					new KategoriDal().update(contract);
					JOptionPane.showMessageDialog(null,
							kContract.getAdi() + " adlý kategorinin bilgileri baþarýyla güncellenmiþtir.");
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
