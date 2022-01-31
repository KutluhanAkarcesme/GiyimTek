package tr.com.akarcesme.fe;

import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;

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

import PrototypePattern.prototype;
import tr.com.akarcesme.dal.KategoriDal;
import tr.com.akarcesme.dal.UrunlerDal;
import tr.com.akarcesme.interfaces.FeInterfaces;
import tr.com.akarcesme.types.KategoriContract;
import tr.com.akarcesme.types.UrunlerContract;

public class urunEkleFE extends JDialog implements FeInterfaces{

	public urunEkleFE() {
		initPencere();
	}

	@Override
	public void initPencere() {
		JPanel panel = initPanel();

		panel.setBorder(BorderFactory.createTitledBorder("Ürün Kayýt Alaný"));
		add(panel);
		setTitle("Ürün Ekleyin");
		pack();
		setModalityType(DEFAULT_MODALITY_TYPE);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new GridLayout(5, 2));
		JLabel adiLabel = new JLabel("Ürün Adý:", JLabel.RIGHT);
		panel.add(adiLabel);
		JTextField adiField = new JTextField(15);
		panel.add(adiField);
		JLabel kategoriLabel = new JLabel("Kategori Seç:", JLabel.RIGHT);
		panel.add(kategoriLabel);
		JComboBox kategoriBox = new JComboBox(new KategoriDal().GetAll().toArray());
		panel.add(kategoriBox);
		kategoriBox.insertItemAt("--Kategori Seçiniz--", 0);
		kategoriBox.setSelectedIndex(0);
		JLabel tarihLabel = new JLabel("Tarih Seç:", JLabel.RIGHT);
		panel.add(tarihLabel);
		JDateChooser tarihDate = new JDateChooser();
		panel.add(tarihDate);
		JLabel fiyatLabel = new JLabel("Fiyat Gir:", JLabel.RIGHT);
		panel.add(fiyatLabel);
		JTextField fiyatField = new JTextField(15);
		panel.add(fiyatField);

		JButton kaydetButton = new JButton("Kaydet");
		panel.add(kaydetButton);
		JButton iptalButton = new JButton("Ýptal");
		panel.add(iptalButton);
		kaydetButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				UrunlerContract contract = new UrunlerContract();

				if (kategoriBox.getSelectedIndex() == 0 || adiField.getText().matches("") 
						|| fiyatField.getText().matches("")) {

					JOptionPane.showMessageDialog(null, "!!!Lütfen tüm bilgileri giriniz!!!");
				} else {
					KategoriContract casContract = (KategoriContract) kategoriBox.getSelectedItem();
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

					String date = format.format(tarihDate.getDate());

					contract.setAdi(adiField.getText());
					contract.setKategoriId(casContract.getId());
					contract.setTarih(date);
					contract.setFiyat(Float.parseFloat(fiyatField.getText())); 

					new UrunlerDal().Insert(contract);

					prototype urunler = new prototype();
					urunler.AddUrunler(contract.getAdi());

					try {
						prototype urunler1 = (prototype) urunler.clone();
						List<Object> ceketlikUrunList = urunler1.getUrunlerListesi();
						if(adiField.getText().charAt(1) == 'P') {
							ceketlikUrunList.remove(contract.getAdi());
						}
						prototype urunler2 = (prototype) urunler.clone();
						List<Object> pantolonlukUrunList = urunler2.getUrunlerListesi();
						if(adiField.getText().charAt(1) == 'C') {
							pantolonlukUrunList.remove(contract.getAdi());
						}
						JOptionPane.showMessageDialog(null, "Eklenen Ürün: " + urunler.getUrunlerListesi() + "\n"
						+ " Ceketlik Ürün: " + ceketlikUrunList + "\n" + " Pantolonluk Ürün: " + pantolonlukUrunList);

					} catch (CloneNotSupportedException e1) {
						e1.printStackTrace();
					}
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
