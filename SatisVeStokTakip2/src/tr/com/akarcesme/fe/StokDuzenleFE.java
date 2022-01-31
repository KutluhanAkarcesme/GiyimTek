package tr.com.akarcesme.fe;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

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

import com.toedter.calendar.JDateChooser;

import tr.com.akarcesme.complex.types.StokContractComplex;
import tr.com.akarcesme.dal.PersonelDal;
import tr.com.akarcesme.dal.StokDal;
import tr.com.akarcesme.dal.UrunlerDal;
import tr.com.akarcesme.interfaces.FeInterfaces;
import tr.com.akarcesme.types.PersonelContract;
import tr.com.akarcesme.types.StokContract;
import tr.com.akarcesme.types.UrunlerContract;

public class StokDuzenleFE extends JDialog implements FeInterfaces {

	public StokDuzenleFE() {
		initPencere();
	}

	@Override
	public void initPencere() {
		JPanel panel = initPanel();

		add(panel);
		setTitle("Stok Düzenle");
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
		JPanel ustPanel = new JPanel(new GridLayout(6, 2));
		ustPanel.setBorder(BorderFactory.createTitledBorder("Stok Düzenle"));

		JLabel stokBoxLabel = new JLabel("Stok Kayýtlarý:", JLabel.RIGHT);
		ustPanel.add(stokBoxLabel);
		JComboBox stokBox = new JComboBox(new StokDal().GetAllStok().toArray());
		ustPanel.add(stokBox);
		stokBox.insertItemAt("--Kayýt Seçiniz--", 0);
		stokBox.setSelectedIndex(0);
		JLabel personelBoxLabel = new JLabel("Personel:", JLabel.RIGHT);
		ustPanel.add(personelBoxLabel);
		JComboBox personelBox = new JComboBox(new PersonelDal().GetAll().toArray());
		ustPanel.add(personelBox);
		personelBox.insertItemAt("--Personel Seçiniz--", 0);
		personelBox.setSelectedIndex(0);
		JLabel urunBoxLabel = new JLabel("Ürünler:", JLabel.RIGHT);
		ustPanel.add(urunBoxLabel);
		JComboBox stokUrunAdiBox = new JComboBox(new UrunlerDal().GetAll().toArray());
		ustPanel.add(stokUrunAdiBox);
		stokUrunAdiBox.insertItemAt("--Ürün Seçiniz--", 0);
		stokUrunAdiBox.setSelectedIndex(0);
		JLabel adetLabel = new JLabel("Stok Adedi:", JLabel.RIGHT);
		ustPanel.add(adetLabel);
		JTextField stokAdetField = new JTextField(10);
		ustPanel.add(stokAdetField);
		JLabel stokTarihiLabel = new JLabel("Stok Tarihi:", JLabel.RIGHT);
		ustPanel.add(stokTarihiLabel);
		JDateChooser stokTarihi = new JDateChooser();
		ustPanel.add(stokTarihi);

		JButton kaydetButton = new JButton("Kaydet");
		ustPanel.add(kaydetButton);
		JButton iptalbutton = new JButton("Ýptal");
		ustPanel.add(iptalbutton);
		kaydetButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				StokContract contract = new StokContract();
				if (stokBox.getSelectedIndex() == 0 || personelBox.getSelectedIndex() == 0
						|| stokUrunAdiBox.getSelectedIndex() == 0 || stokAdetField.getText().matches("")) {
					JOptionPane.showMessageDialog(null, "!!!Lütfen tüm bilgileri giriniz!!!");
				} else {
					StokContractComplex sContract = (StokContractComplex) stokBox.getSelectedItem();
					UrunlerContract uContract = (UrunlerContract) stokUrunAdiBox.getSelectedItem();
					PersonelContract pContract = (PersonelContract) personelBox.getSelectedItem();

					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					String date = format.format(stokTarihi.getDate());

					contract.setId(sContract.getId());
					contract.setPersonelId(pContract.getId());
					contract.setUrunId(uContract.getId());
					contract.setAdet(Integer.parseInt(stokAdetField.getText()));
					contract.setTarih(date);

					new StokDal().update(contract);
					JOptionPane.showMessageDialog(null, "Seçilen kayýt baþarýyla deðiþtirilmiþtir.");
				}
			}

		});
		iptalbutton.addActionListener(new ActionListener() {

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
