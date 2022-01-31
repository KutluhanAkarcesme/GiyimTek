package tr.com.akarcesme.fe;

import java.awt.BorderLayout;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import CompositePattern.*;
import StrategyPattern.ceket;
import StrategyPattern.pantolon;
import StrategyPattern.strategyContext;
import tr.com.akarcesme.complex.types.SatisContractComplex;
import tr.com.akarcesme.complex.types.SatisContractTotalComplex;
import tr.com.akarcesme.complex.types.SatisContractTotalFiyatComplex;
import tr.com.akarcesme.complex.types.StokContractComplex;
import tr.com.akarcesme.complex.types.StokContractTotalComplex;
import tr.com.akarcesme.dal.MusteriDal;
import tr.com.akarcesme.dal.SatisDal;
import tr.com.akarcesme.dal.StokDal;
import tr.com.akarcesme.dal.UrunlerDal;
import tr.com.akarcesme.interfaces.FeInterfaces;
import tr.com.akarcesme.types.MusteriContract;
import tr.com.akarcesme.types.PersonelContract;
import tr.com.akarcesme.types.SatisContract;
import tr.com.akarcesme.types.StokContract;
import tr.com.akarcesme.types.UrunlerContract;
import tr.com.akarcesme.utilities.Menuler;

public class AnaPencereFE extends JFrame implements FeInterfaces {
	public AnaPencereFE() {
		initPencere();
	}

	@Override
	public void initPencere() {
		JPanel panel = initPanel();
		JMenuBar bar = initBar();

		add(panel);
		setJMenuBar(bar);
		setTitle("Satýþ ve Stok Programý");
		setSize(900, 450);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	@Override
	public JPanel initPanel() {

		JPanel panel = new JPanel(new BorderLayout());
		JTabbedPane pane = initTabs();
		panel.add(pane, BorderLayout.CENTER);
		return panel;
	}

	@Override
	public JMenuBar initBar() {

		JMenuBar bar = Menuler.initBar();

		return bar;
	}

	@Override
	public JTabbedPane initTabs() {
		JTabbedPane pane = new JTabbedPane();
		ImageIcon icon = new ImageIcon("icons/stock.png");
		ImageIcon icon2 = new ImageIcon("icons/stock.icon.png");

		JPanel stokPanel = new JPanel(new BorderLayout());
		JPanel satisPanel = new JPanel(new BorderLayout());

		// Stok Ýþlemleri
		JPanel stokSolPanel = new JPanel(new BorderLayout());
		JPanel stokSolUstPanel = new JPanel(new GridLayout(5, 2));
		JPanel stokSolAltPanel = new JPanel();

		stokSolPanel.setBorder(BorderFactory.createTitledBorder("Stok Ýþlemleri"));
		Object[] stokKolonlar = { "Id", "Ürün Adý", "Personel Adý", "Adet", "Tarihi", "Toplam" };
		DefaultTableModel model = new DefaultTableModel(stokKolonlar, 0);
		JTable table = new JTable(model);
		JScrollPane stokTablePane = new JScrollPane(table);

		for (StokContractComplex contract : new StokDal().GetAllStok()) {
			model.addRow(contract.getVeriler());
		}

		JLabel stokUrunAdiLabel = new JLabel("Ürün Adý:", JLabel.RIGHT);
		stokSolUstPanel.add(stokUrunAdiLabel);
		JComboBox stokUrunAdiBox = new JComboBox(new UrunlerDal().GetAll().toArray());
		stokSolUstPanel.add(stokUrunAdiBox);
		stokUrunAdiBox.insertItemAt("--Ürün Seçiniz--", 0);
		stokUrunAdiBox.setSelectedIndex(0);
		JLabel stokAdetLabel = new JLabel("Adet:", JLabel.RIGHT);
		stokSolUstPanel.add(stokAdetLabel);
		JTextField stokAdetField = new JTextField(15);
		stokSolUstPanel.add(stokAdetField);
		JLabel stokTarihiLabel = new JLabel("Stok Tarihi:", JLabel.RIGHT);
		stokSolUstPanel.add(stokTarihiLabel);
		JDateChooser stokTarihi = new JDateChooser();
		stokSolUstPanel.add(stokTarihi);

		JButton stokEkleButton = new JButton("Stok Ekle");
		stokSolUstPanel.add(stokEkleButton);
		JButton stokYenileButton = new JButton("Stok Yenile");
		stokSolUstPanel.add(stokYenileButton);
		JButton stokTotalButton = new JButton("Stok Toplam Ürün");
		stokSolUstPanel.add(stokTotalButton);

		stokTotalButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int satir = model.getRowCount();
				for (int i = 0; i < satir; i++) {
					model.removeRow(0);
				}
				for (StokContractTotalComplex total : new StokDal().GetTotalStok()) {
					model.addRow(total.getVeriler());
				}

			}
		});
		stokYenileButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int satir = model.getRowCount();
				for (int i = 0; i < satir; i++) {
					model.removeRow(0);
				}

				for (StokContractComplex compContract : new StokDal().GetAllStok()) {
					model.addRow(compContract.getVeriler());
				}
			}
		});

		stokEkleButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				StokContract contract = new StokContract();

				if (stokUrunAdiBox.getSelectedIndex() == 0 || stokAdetField.getText().matches("")) {
					JOptionPane.showMessageDialog(null, "!!!Ürün ve adet bilgileri boþ býrakýlamaz!!!");
				} else {
					UrunlerContract uContract = (UrunlerContract) stokUrunAdiBox.getSelectedItem();
					PersonelContract pContact = (PersonelContract) LoginFE.kullaniciAdiBox.getSelectedItem();
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					String date = format.format(stokTarihi.getDate());

					contract.setPersonelId(pContact.getId());
					contract.setUrunId(uContract.getId());
					contract.setTarih(date.toString());
					contract.setAdet(Integer.parseInt(stokAdetField.getText()));

					new StokDal().Insert(contract);
					JOptionPane.showMessageDialog(null, uContract.getAdi() + " adlý ürün eklenmiþtir.");

					int satir = model.getRowCount();
					for (int i = 0; i < satir; i++) {
						model.removeRow(0);
					}

					for (StokContractComplex compContract : new StokDal().GetAllStok()) {
						model.addRow(compContract.getVeriler());
					}

				}
			}
		});

		// Satýþ Ýþlemleri
		JPanel satisSagPanel = new JPanel(new BorderLayout());
		JPanel satisSagUstPanel = new JPanel(new GridLayout(6, 2));
		JPanel satisSagAltPanel = new JPanel();

		satisSagPanel.setBorder(BorderFactory.createTitledBorder("Satýþ Ýþlemleri"));
		Object[] satisKolonlar = { "Id", "Personel Adý", "Müþteri Adý", "Ürün Adý", "Adet", "Tarihi", "Toplam" };
		DefaultTableModel satisModel = new DefaultTableModel(satisKolonlar, 0);
		JTable satisTable = new JTable(satisModel);
		JScrollPane satisTablePane = new JScrollPane(satisTable);

		JLabel musteriLabel = new JLabel("Müþteri Adý:", JLabel.RIGHT);
		satisSagUstPanel.add(musteriLabel);
		JComboBox musteriAdiBox = new JComboBox(new MusteriDal().GetAll().toArray());
		satisSagUstPanel.add(musteriAdiBox);
		musteriAdiBox.insertItemAt("--Müþteri Seçiniz--", 0);
		musteriAdiBox.setSelectedIndex(0);
		JLabel satisUrunAdiLabel = new JLabel("Ürün Adý:", JLabel.RIGHT);
		satisSagUstPanel.add(satisUrunAdiLabel);
		JComboBox satisUrunAdiBox = new JComboBox(new UrunlerDal().GetAll().toArray());
		satisSagUstPanel.add(satisUrunAdiBox);
		satisUrunAdiBox.insertItemAt("--Ürün Seçiniz--", 0);
		satisUrunAdiBox.setSelectedIndex(0);
		JLabel satisAdetLabel = new JLabel("Adet:", JLabel.RIGHT);
		satisSagUstPanel.add(satisAdetLabel);
		JTextField satisAdetField = new JTextField(15);
		satisSagUstPanel.add(satisAdetField);
		JLabel satisTarihiLabel = new JLabel("Satýþ Tarihi", JLabel.RIGHT);
		satisSagUstPanel.add(satisTarihiLabel);
		JDateChooser satisTarihi = new JDateChooser();
		satisSagUstPanel.add(satisTarihi);

		JButton satisEkleButton = new JButton("Satýþ Yap");
		satisSagUstPanel.add(satisEkleButton);
		JButton satisYenileButton = new JButton("Satýþ Yenile");
		satisSagUstPanel.add(satisYenileButton);
		JButton satisTotalButton = new JButton("Satýþ Adet Toplamý");
		satisSagUstPanel.add(satisTotalButton);
		JButton fiyatHesaplaButton = new JButton("Satýþ Fiyat Toplamý");
		satisSagUstPanel.add(fiyatHesaplaButton);

		for (SatisContractComplex yenileContract : new SatisDal().GetAllSatis()) {
			satisModel.addRow(yenileContract.getVeriler());
		}
		satisEkleButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SatisContract contract = new SatisContract();

				if (musteriAdiBox.getSelectedIndex() == 0 || satisUrunAdiBox.getSelectedIndex() == 0
						|| satisAdetField.getText().matches("")) {
					JOptionPane.showMessageDialog(null, "!!!Müþteri,ürün,adet ve tarih bilgileri boþ býrakýlamaz!!!");
				} else {
					PersonelContract pContract = (PersonelContract) LoginFE.kullaniciAdiBox.getSelectedItem();
					UrunlerContract uContract = (UrunlerContract) satisUrunAdiBox.getSelectedItem();
					MusteriContract mContract = (MusteriContract) musteriAdiBox.getSelectedItem();
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

					String date = format.format(satisTarihi.getDate());

					contract.setMusteriId(mContract.getId());
					contract.setPersonelId(pContract.getId());
					contract.setUrunId(uContract.getId());
					contract.setAdet(Integer.parseInt(satisAdetField.getText()));
					contract.setTarih(date);

					new SatisDal().Insert(contract);

					StokContract stokContract = new StokContract();

					stokContract.setPersonelId(pContract.getId());
					stokContract.setUrunId(uContract.getId());
					stokContract.setAdet(-Integer.parseInt(satisAdetField.getText()));
					stokContract.setTarih(date);

					new StokDal().Insert(stokContract);

					strategyContext sContext = new strategyContext();
					if (uContract.getKategoriId() == 1 || uContract.getKategoriId() == 2
							|| uContract.getKategoriId() == 5 || uContract.getKategoriId() == 6) {
						JOptionPane.showMessageDialog(null,
								mContract.getMusteriAdi() + " adlý müþteriye satýþ yapýlmýþtýr, " + contract.getAdet()
										+ " adet stoktan düþülmüþtür ve "
										+ sContext.getHesapla(new pantolon(), contract.getAdet())
										+ " TL ödeme alýnacaktýr.");

					} else {
						JOptionPane.showMessageDialog(null,
								mContract.getMusteriAdi() + " adlý müþteriye satýþ yapýlmýþtýr, " + contract.getAdet()
										+ " adet stoktan düþülmüþtür ve "
										+ sContext.getHesapla(new ceket(), contract.getAdet())
										+ " TL ödeme alýnacaktýr.");
					}

					int satir = satisModel.getRowCount();
					for (int i = 0; i < satir; i++) {
						satisModel.removeRow(0);
					}
					for (SatisContractComplex yenileContract : new SatisDal().GetAllSatis()) {
						satisModel.addRow(yenileContract.getVeriler());
					}
				}
			}

		});

		satisYenileButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int satir = satisModel.getRowCount();
				for (int i = 0; i < satir; i++) {
					satisModel.removeRow(0);
				}
				for (SatisContractComplex contract : new SatisDal().GetAllSatis()) {
					satisModel.addRow(contract.getVeriler());
				}

			}
		});
		satisTotalButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int satir = satisModel.getRowCount();
				for (int i = 0; i < satir; i++) {
					satisModel.removeRow(0);
				}
				for (SatisContractTotalComplex total : new SatisDal().GetTotalSatis()) {
					satisModel.addRow(total.getVeriler());
				}

			}
		});
		fiyatHesaplaButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SatilanUrunler sUrunler = new SatilanUrunler();
				SatilanUrunler sUrunler1 = new SatilanUrunler();
				SatilanUrunler sUrunler2 = new SatilanUrunler();
				kategoriler cUrunler = new kategoriler();
				kategoriler pUrunler = new kategoriler();

				for (SatisContractTotalFiyatComplex contract : new SatisDal().GetTotalFiyatSatis()) {
					if (contract.getUrunAdi().charAt(1) == 'C') {
						cUrunler.getUrunlerList().add(contract);
					}
					if (contract.getUrunAdi().charAt(1) == 'P') {
						pUrunler.getUrunlerList().add(contract);
					}
				}
				sUrunler.getFiyatlanabilirList().add(cUrunler);
				sUrunler.getFiyatlanabilirList().add(pUrunler);
				sUrunler1.getFiyatlanabilirList().add(cUrunler);
				sUrunler2.getFiyatlanabilirList().add(pUrunler);
				JOptionPane.showMessageDialog(null,
						"Toplam yapýlan satýþ: " + sUrunler.toplamOdenecekTutar() + " TL" + "\n"
								+ "Ceketlik yapýlan satýþ: " + sUrunler1.toplamOdenecekTutar() + " TL" + "\n"
								+ "Pantolonluk yapýlan satýþ: " + sUrunler2.toplamOdenecekTutar() + " TL");

			}
		});

		stokPanel.add(stokSolPanel, BorderLayout.WEST);
		stokPanel.add(stokTablePane, BorderLayout.CENTER);

		satisPanel.add(satisSagPanel, BorderLayout.EAST);
		satisPanel.add(satisTablePane, BorderLayout.CENTER);

		satisSagPanel.add(satisSagUstPanel, BorderLayout.NORTH);
		satisSagPanel.add(satisSagAltPanel, BorderLayout.SOUTH);

		stokSolPanel.add(stokSolUstPanel, BorderLayout.NORTH);
		stokSolPanel.add(stokSolAltPanel, BorderLayout.SOUTH);

		pane.addTab("Stoklar ", icon, stokPanel, "Does nothing");
		pane.addTab("Satýþlar ", icon2, satisPanel, "yazý");
		return pane;
	}

}
