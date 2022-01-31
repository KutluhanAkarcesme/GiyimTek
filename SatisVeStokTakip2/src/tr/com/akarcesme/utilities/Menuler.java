package tr.com.akarcesme.utilities;

import java.awt.Color;
import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import tr.com.akarcesme.dal.AccountsDal;
import tr.com.akarcesme.fe.KategoriDuzenleFE;
import tr.com.akarcesme.fe.KategoriEkleFE;
import tr.com.akarcesme.fe.LoginFE;
import tr.com.akarcesme.fe.MusteriDuzenleFE;
import tr.com.akarcesme.fe.MusteriEkleFE;
import tr.com.akarcesme.fe.PersonelDuzenleFE;
import tr.com.akarcesme.fe.PersonelEkleFE;
import tr.com.akarcesme.fe.SatisDuzenleFE;
import tr.com.akarcesme.fe.SifreDuzenleFE;
import tr.com.akarcesme.fe.SifreIslemleriFE;
import tr.com.akarcesme.fe.StokDuzenleFE;
import tr.com.akarcesme.fe.UrunDuzenleFE;
import tr.com.akarcesme.fe.YetkiDuzenleFE;
import tr.com.akarcesme.fe.YetkiEkleFE;
import tr.com.akarcesme.fe.PersonelEkleFE;
import tr.com.akarcesme.fe.urunEkleFE;
import tr.com.akarcesme.types.PersonelContract;
import tr.com.akarcesme.fe.PersonelEkleFE;

public class Menuler {
	public static JMenuBar initBar() {

		// Dosya Menü
		JMenuBar bar = new JMenuBar();
		JMenu dosyaMenu = new JMenu("Uygulama Ýþlemleri");
		bar.add(dosyaMenu);
		JMenuItem darkModeItem = new JMenuItem("Dark Mode");
		dosyaMenu.add(darkModeItem);
		darkModeItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				UIManager.put( "control", new Color( 128, 128, 128) );
				  UIManager.put( "info", new Color(128,128,128) );
				  UIManager.put( "nimbusBase", new Color( 18, 30, 49) );
				  UIManager.put( "nimbusAlertYellow", new Color( 248, 187, 0) );
				  UIManager.put( "nimbusDisabledText", new Color( 128, 128, 128) );
				  UIManager.put( "nimbusFocus", new Color(115,164,209) );
				  UIManager.put( "nimbusGreen", new Color(176,179,50) );
				  UIManager.put( "nimbusInfoBlue", new Color( 66, 139, 221) );
				  UIManager.put( "nimbusLightBackground", new Color( 18, 30, 49) );
				  UIManager.put( "nimbusOrange", new Color(191,98,4) );
				  UIManager.put( "nimbusRed", new Color(169,46,34) );
				  UIManager.put( "nimbusSelectedText", new Color( 255, 255, 255) );
				  UIManager.put( "nimbusSelectionBackground", new Color( 104, 93, 156) );
				  UIManager.put( "text", new Color( 230, 230, 230) );
				
			}
		});
		JMenuItem cikisIslemItem = new JMenuItem("Çýkýþ");
		dosyaMenu.add(cikisIslemItem);
		cikisIslemItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						new LoginFE();
					}
					
				});
				
			}
		});

		// Ürünler Menü
		JMenu urunlerMenu = new JMenu("Ürünler");
		bar.add(urunlerMenu);
		JMenuItem urunEkleItem = new JMenuItem("Ürün Ekle");
		urunlerMenu.add(urunEkleItem);
		urunEkleItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						new urunEkleFE();

					}

				});

			}

		});
		JMenuItem kategoriEkleItem = new JMenuItem("Kategori Ekle");
		urunlerMenu.add(kategoriEkleItem);
		kategoriEkleItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new KategoriEkleFE();
			}
		});
		urunlerMenu.addSeparator();
		JMenuItem urunDuzenleItem = new JMenuItem("Ürün Düzenle");
		urunlerMenu.add(urunDuzenleItem);
		urunDuzenleItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						new UrunDuzenleFE();

					}
				});

			}
		});
		JMenuItem kategoriDuzenleItem = new JMenuItem("Kategori Düzenle");
		urunlerMenu.add(kategoriDuzenleItem);
		kategoriDuzenleItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						new KategoriDuzenleFE();

					}
				});

			}
		});

		// Personel Menu
		JMenu personellerMenu = new JMenu("Personel Ýþlemleri");
		bar.add(personellerMenu);
		JMenuItem personelEkleItem = new JMenuItem("Personel Ekle");
		personellerMenu.add(personelEkleItem);
		personelEkleItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						new PersonelEkleFE();

					}

				});
			}

		});
		JMenuItem yetkiEkleItem = new JMenuItem("Yetki Ekle");
		personellerMenu.add(yetkiEkleItem);
		yetkiEkleItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						new YetkiEkleFE();

					}

				});

			}

		});
		JMenuItem sifreBelirleItem = new JMenuItem("Hesap Ekle");
		personellerMenu.add(sifreBelirleItem);
		sifreBelirleItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new SifreIslemleriFE();

			}

		});
		personellerMenu.addSeparator();
		JMenuItem personelDuzenleItem = new JMenuItem("Personel Düzenle");
		personellerMenu.add(personelDuzenleItem);
		personelDuzenleItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						new PersonelDuzenleFE();

					}
				});

			}
		});
		JMenuItem yetkiDuzenleItem = new JMenuItem("Yetki Düzenle");
		personellerMenu.add(yetkiDuzenleItem);
		yetkiDuzenleItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						new YetkiDuzenleFE();

					}
				});

			}
		});
		JMenuItem sifreDuzenleItem = new JMenuItem("Hesap Düzenle");
		personellerMenu.add(sifreDuzenleItem);
		sifreDuzenleItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						new SifreDuzenleFE();

					}
				});

			}
		});

		// Müþteriler Menü
		JMenu musterilerMenu = new JMenu("Müþteriler");
		bar.add(musterilerMenu);
		JMenuItem musteriEkleItem = new JMenuItem("Müþteri Ekle");
		musterilerMenu.add(musteriEkleItem);
		musteriEkleItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {

						new MusteriEkleFE();

					}

				});

			}
		});
		musterilerMenu.addSeparator();
		JMenuItem musteriDuzenleItem = new JMenuItem("Müþteri Düzenle");
		musterilerMenu.add(musteriDuzenleItem);
		musteriDuzenleItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						new MusteriDuzenleFE();

					}
				});

			}
		});
		
		//Stok/Satýþ Menü
		JMenu stokVeSatisItem = new JMenu("Stok ve Satýþ Ýþlemleri");
		bar.add(stokVeSatisItem);
		JMenuItem stokDuzenleItem = new JMenuItem("Stok Düzenle");
		stokVeSatisItem.add(stokDuzenleItem);
		JMenuItem satisDuzenleItem = new JMenuItem("Satýþ Düzenle");
		stokVeSatisItem.add(satisDuzenleItem);
		stokDuzenleItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						new StokDuzenleFE();
						
					}
				});
				
			}
		});
		satisDuzenleItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						new SatisDuzenleFE();
						
					}
				});
				
			}
		});

		// Hesap Yetkilendirme
		PersonelContract contract = (PersonelContract) LoginFE.kullaniciAdiBox.getSelectedItem();

		if (new AccountsDal().GetYetkiId(contract.getId()).getYetkiId() == 2) {
			personellerMenu.hide();
		} else if (new AccountsDal().GetYetkiId(contract.getId()).getYetkiId() == 3) {
			musterilerMenu.hide();
			personellerMenu.hide();
			urunlerMenu.hide();
		}

		return bar;
	}


}
