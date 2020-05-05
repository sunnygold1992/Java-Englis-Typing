import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ABC extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ABC frame = new ABC();
					frame.setVisible(true);
					File a = new File("202001ETS.txt");
//					File a = new File("202001.txt");
					if(!a.exists()) a.createNewFile();
					for(int i = 1; i <= 40; i++) {
						String head = "202001";
						if(i/10 <1) {
							head = head + "0";
						}
						String str = head+i+ " 00:00:00 00:00:00.0\n";
						Files.write(a.toPath(), str.getBytes(), StandardOpenOption.APPEND);
					}
					for(int i = 1; i <= 400; i++) {
						String head = "202002";
						if(i/10 <1) {
							head = head + "0";
						}
						String str = head+i+ " 00:00:00 00:00:00.0\n";
						Files.write(a.toPath(), str.getBytes(), StandardOpenOption.APPEND);
					}
					for(int i = 1; i <= 1200; i++) {
						String head = "202003";
						if(i/10 <1) {
							head = head + "0";
						}
						String str = head+i+ " 00:00:00 00:00:00.0\n";
						Files.write(a.toPath(), str.getBytes(), StandardOpenOption.APPEND);
					}
					for(int i = 1; i <= 1200; i++) {
						String head = "202004";
						if(i/10 <1) {
							head = head + "0";
						}
						String str = head+i+ " 00:00:00 00:00:00.0\n";
						Files.write(a.toPath(), str.getBytes(), StandardOpenOption.APPEND);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ABC() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
