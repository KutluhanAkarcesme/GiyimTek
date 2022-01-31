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

import tr.com.akarcesme.dal.AccountsDal;
import tr.com.akarcesme.dal.KategoriDal;
import tr.com.akarcesme.dal.PersonelDal;
import tr.com.akarcesme.dal.YetkilerDal;
import tr.com.akarcesme.interfaces.FeInterfaces;
import tr.com.akarcesme.types.AccountsContract;
import tr.com.akarcesme.types.PersonelContract;
import tr.com.akarcesme.types.YetkilerContract;

public class YetkiDuzenleFE extends JDialog implements FeInterfaces {

	public YetkiDuzenleFE() {
		initPencere();
	}

	@Override
	public void initPencere() {
		JPanel panel = initPanel();

		add(panel);
		setTitle("Yetki Düzenle");
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
		JPanel ustPanel = new JPanel(new GridLayout(3, 2));
		ustPanel.setBorder(BorderFactory.createTitledBorder("Yetki Düzenle"));
		JLabel yetkiBoxLabel = new JLabel("Yetkiler:", JLabel.RIGHT);
		ustPanel.add(yetkiBoxLabel);
		JComboBox yetkiBox = new JComboBox(new YetkilerDal().GetAll().toArray());
		ustPanel.add(yetkiBox);
		yetkiBox.insertItemAt("--Yetki Seçiniz--", 0);
		yetkiBox.setSelectedIndex(0);
		JLabel yetkiLabel = new JLabel("Yeni Yetki:", JLabel.RIGHT);
		ustPanel.add(yetkiLabel);
		JTextField yetkiField = new JTextField(10);
		ustPanel.add(yetkiField);
		JButton kaydetButton = new JButton("Kaydet");
		ustPanel.add(kaydetButton);
		JButton iptalButton = new JButton("Ýptal");
		ustPanel.add(iptalButton);
		kaydetButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				YetkilerContract contract = new YetkilerContract();

				if (yetkiBox.getSelectedIndex() == 0 || yetkiField.getText().matches("")) {
					JOptionPane.showMessageDialog(null, "!!!Lütfen tüm bilgileri!!!");
				} else {
					YetkilerContract yContract = (YetkilerContract) yetkiBox.getSelectedItem();

					contract.setId(yContract.getId());
					contract.setAdi(yetkiField.getText());

					new YetkilerDal().update(contract);
					JOptionPane.showMessageDialog(null,
							yContract.getAdi() + " adlý yetki " + contract.getAdi() + " olarak deðiþtirilmiþtir.");

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
