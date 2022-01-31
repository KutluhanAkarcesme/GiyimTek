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

import tr.com.akarcesme.complex.types.SatisContractComplex;
import tr.com.akarcesme.complex.types.StokContractComplex;
import tr.com.akarcesme.dal.MusteriDal;
import tr.com.akarcesme.dal.PersonelDal;
import tr.com.akarcesme.dal.SatisDal;
import tr.com.akarcesme.dal.StokDal;
import tr.com.akarcesme.dal.UrunlerDal;
import tr.com.akarcesme.interfaces.FeInterfaces;
import tr.com.akarcesme.types.MusteriContract;
import tr.com.akarcesme.types.PersonelContract;
import tr.com.akarcesme.types.SatisContract;
import tr.com.akarcesme.types.StokContract;
import tr.com.akarcesme.types.UrunlerContract;

public class SatisDuzenleFE extends JDialog implements FeInterfaces {

	public SatisDuzenleFE() {
		initPencere();
	}

	@Override
	public void initPencere() {
		JPanel panel = initPanel();

		add(panel);
		setTitle("Satýþ Düzenle");
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
		JPanel ustPanel = new JPanel(new GridLayout(8, 2));
		ustPanel.setBorder(BorderFactory.createTitledBorder("Satýþ Düzenle"));

		JLabel satisBoxLabel = new JLabel("Satýþ Kayýtlarý:", JLabel.RIGHT);
		ustPanel.add(satisBoxLabel);
		JComboBox satisBox = new JComboBox(new SatisDal().GetAllSatis().toArray());
		ustPanel.add(satisBox);
		satisBox.insertItemAt("--Kayýt Seçiniz--", 0);
		satisBox.setSelectedIndex(0);
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
		JLabel musteriBoxLabel = new JLabel("Müþteriler:", JLabel.RIGHT);
		ustPanel.add(musteriBoxLabel);
		JComboBox musteriBox = new JComboBox(new MusteriDal().GetAll().toArray());
		ustPanel.add(musteriBox);
		musteriBox.insertItemAt("--Müþteri Seçiniz--", 0);
		musteriBox.setSelectedIndex(0);
		JLabel urunBoxLabel = new JLabel("Ürünler:", JLabel.RIGHT);
		ustPanel.add(urunBoxLabel);
		JComboBox satisUrunAdiBox = new JComboBox(new UrunlerDal().GetAll().toArray());
		ustPanel.add(satisUrunAdiBox);
		satisUrunAdiBox.insertItemAt("--Ürün Seçiniz--", 0);
		satisUrunAdiBox.setSelectedIndex(0);
		JLabel adetLabel = new JLabel("Satýþ Adedi:", JLabel.RIGHT);
		ustPanel.add(adetLabel);
		JTextField satisAdetField = new JTextField(10);
		ustPanel.add(satisAdetField);
		JLabel satisTarihiLabel = new JLabel("Satýþ Tarihi:", JLabel.RIGHT);
		ustPanel.add(satisTarihiLabel);
		JDateChooser satisTarihi = new JDateChooser();
		ustPanel.add(satisTarihi);

		JButton kaydetButton = new JButton("Kaydet");
		ustPanel.add(kaydetButton);
		JButton iptalbutton = new JButton("Ýptal");
		ustPanel.add(iptalbutton);
		kaydetButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SatisContract contract = new SatisContract();
				StokContract stokContract = new StokContract();
				if (satisBox.getSelectedIndex() == 0 || personelBox.getSelectedIndex() == 0
						|| musteriBox.getSelectedIndex() == 0 || satisUrunAdiBox.getSelectedIndex() == 0
						|| stokBox.getSelectedIndex() ==0 || satisAdetField.getText().matches("")) {
					JOptionPane.showMessageDialog(null, "!!!Lütfen tüm bilgileri giriniz!!!");
				} else {

					SatisContractComplex sContract = (SatisContractComplex) satisBox.getSelectedItem();
					MusteriContract mContract = (MusteriContract) musteriBox.getSelectedItem();
					UrunlerContract uContract = (UrunlerContract) satisUrunAdiBox.getSelectedItem();
					PersonelContract pContract = (PersonelContract) personelBox.getSelectedItem();
					StokContractComplex stokContractComplex = (StokContractComplex) stokBox.getSelectedItem();

					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					String date = format.format(satisTarihi.getDate());

					stokContract.setId(stokContractComplex.getId());
					stokContract.setPersonelId(pContract.getId());
					stokContract.setUrunId(uContract.getId());
					stokContract.setAdet(-Integer.parseInt(satisAdetField.getText()));
					stokContract.setTarih(date);

					new StokDal().update(stokContract);

					contract.setId(sContract.getId());
					contract.setMusteriId(mContract.getId());
					contract.setPersonelId(pContract.getId());
					contract.setUrunId(uContract.getId());
					contract.setTarih(date);
					contract.setAdet(Integer.parseInt(satisAdetField.getText()));

					new SatisDal().update(contract);

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
