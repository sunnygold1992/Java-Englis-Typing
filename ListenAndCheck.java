package com.listen.typing;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import javazoom.jl.decoder.JavaLayerException;

public class ListenAndCheck extends JFrame {

	private JPanel contentPane;
	private JTextArea typing, check;
	private String getStringTyping;
	private JButton btnNext,btnPlayPause;
	private PausablePlayer player = null;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListenAndCheck frame = new ListenAndCheck("Listen And Checking");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	private String getDefaultFolder() {
		 String cwd = System.getProperty("user.dir");
	     System.out.println("Current working directory : " + cwd);
	     File deafult = new File(cwd);
	     String [] allNameFiles = deafult.list();
	     File folderMp3 = null;
	     for(int i = 0; i < allNameFiles.length; i++) {
	    	 System.out.println(allNameFiles[i]);
	    	 if(allNameFiles[i].contains("Mp3")){
	    		 folderMp3 = new File(deafult + "//" + allNameFiles[i]);
	    		 System.out.println(folderMp3.getAbsolutePath());
	    	 }
	     }
	     File mp3 = null;
	     if(folderMp3 != null) {
	    	 mp3 = folderMp3.listFiles()[folderMp3.listFiles().length-1];
	    	 System.out.println(mp3.getAbsolutePath());
	     }
	     return mp3 != null? mp3.getAbsolutePath() : cwd;
	}

	/**
	 * Create the frame.
	 */
	public ListenAndCheck(String titleWindow) {
		this.setTitle(titleWindow);
		int xStart = 10;
		int yStart = 100;
		int widthTextArea = 1200;
		int heightTextArea = 200;
		int bolder = 10;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1400, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		File mp3 = new File(getDefaultFolder());
		FileInputStream inputStreamMp3 = null;
		try {
			inputStreamMp3 = new FileInputStream(mp3);
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			player = new PausablePlayer(inputStreamMp3);
		} catch (JavaLayerException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	
		
		btnPlayPause = new JButton("Play");
		btnPlayPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnPlayPause.getText().equalsIgnoreCase("Play")) {
					btnPlayPause.setText("Pause");
					if(player!= null) {
						try {
							player.play();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}else {
					btnPlayPause.setText("Play");
					if(player != null) {
						player.pause();
					}
				}
			}
		});
		btnPlayPause.setBounds(widthTextArea,bolder*2,100,50);
		contentPane.add(btnPlayPause);
		
		typing = new JTextArea();
		typing.setBounds(xStart, yStart, widthTextArea, heightTextArea);
		typing.setWrapStyleWord(true);
		typing.setLineWrap(true);
		typing.setAutoscrolls(true);
		typing.setEditable(false);
		contentPane.add(typing);
		typing.addKeyListener(new KeyListener() {
			
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					getStringTyping = typing.getText();
					if(check.getText().equalsIgnoreCase("")) {
						check.setText(getStringTyping);
					}else {
						check.setText("");
						typing.selectAll();
						typing.setText("");
						btnNext.doClick();
					}
					btnNext.setFocusable(true);
				}
			}
		});
		

		check = new JTextArea();
		check.setBounds(xStart, (yStart + heightTextArea + bolder), widthTextArea, heightTextArea);
		check.setWrapStyleWord(true);
		check.setLineWrap(true);
		typing.setAutoscrolls(true);
		typing.setEditable(true);
		check.setText("");
		contentPane.add(check);
		
		btnNext = new JButton("Next");
		btnNext.setBounds(widthTextArea,yStart+ heightTextArea*2 + bolder*5,100,50);
		contentPane.add(btnNext);
		
		btnNext.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(btnNext.getText().equalsIgnoreCase("Next")) {
					btnNext.setText("Done");
				}else {
					btnNext.setText("Next");
				}
			}
		});
		getDefaultFolder();
	}
}
