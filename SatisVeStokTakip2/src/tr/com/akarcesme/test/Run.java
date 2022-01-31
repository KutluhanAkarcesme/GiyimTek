package tr.com.akarcesme.test;

import java.awt.Color;

import javax.swing.SwingUtilities;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import tr.com.akarcesme.dal.AccountsDal;
import tr.com.akarcesme.fe.AnaPencereFE;
import tr.com.akarcesme.fe.LoginFE;

public class Run {

	public static void main(String[] args) {
		  try {
		    for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
		      if ("Nimbus".equals(info.getName())) {
		          javax.swing.UIManager.setLookAndFeel(info.getClassName());
		          break;
		      }
		    }
		  } catch (ClassNotFoundException e) {
		    e.printStackTrace();
		  } catch (InstantiationException e) {
		    e.printStackTrace();
		  } catch (IllegalAccessException e) {
		    e.printStackTrace();
		  } catch (javax.swing.UnsupportedLookAndFeelException e) {
		    e.printStackTrace(); 
		  } catch (Exception e) {
		    e.printStackTrace();
		  }

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {

				new LoginFE();

			}

		});
		;

	}

}
