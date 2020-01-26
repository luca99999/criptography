/*
 * Copyright (c) 2020 - Luca Bernardini
 * 
 *  This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.
    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.
    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.security.NoSuchAlgorithmException;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class DigitalSignature {

	private JFrame frmFirmaDigitale;
	private JFileChooser fC = new JFileChooser();
	private JTextArea textArea = null;
	private JTextArea txtRsa = null;
	private JTextField txtDigest;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DigitalSignature window = new DigitalSignature();
					window.frmFirmaDigitale.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DigitalSignature() {
		initialize();
		Rsa.RsaKey();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmFirmaDigitale = new JFrame();
		frmFirmaDigitale.setTitle("Firma Digitale");
		frmFirmaDigitale.setBounds(100, 100, 1093, 631);
		frmFirmaDigitale.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmFirmaDigitale.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("File          ");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmApri = new JMenuItem("Apri         ");
		mntmApri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int stato = fC.showOpenDialog(null);
				
				if(stato ==JFileChooser.APPROVE_OPTION)
				{
					try 
					{
						File f = fC.getSelectedFile();
						Reader fin = new FileReader(f);
						textArea.read(fin, null);
						frmFirmaDigitale.setTitle(f.getName());
						
					}
					catch(Exception ecc2) {
						JOptionPane.showInternalMessageDialog(null, "Errore Apertura");
					}
				}
				
				
			}
		});
		mnNewMenu.add(mntmApri);
		
		JMenuItem mntmSalva = new JMenuItem("Salva");
		mntmSalva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int stato = fC.showSaveDialog(null);
				
				if(stato ==JFileChooser.APPROVE_OPTION)
				{
					try 
					{
						File f = fC.getSelectedFile();
						Writer fout = new FileWriter(f);
						textArea.write(fout);
						frmFirmaDigitale.setTitle(f.getName());
					}
					catch(Exception ecc2) {
						JOptionPane.showInternalMessageDialog(null, "Errore Salvataggio");
					}
				}
				
			}
		});
		mnNewMenu.add(mntmSalva);
		
		JMenuItem mntmEsci = new JMenuItem("Esci");
		mntmEsci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmFirmaDigitale.setVisible(false); 
				frmFirmaDigitale.dispose(); 
			}
		});
		mnNewMenu.add(mntmEsci);
		frmFirmaDigitale.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 800, 344);
		frmFirmaDigitale.getContentPane().add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setWrapStyleWord(true);
		scrollPane.setViewportView(textArea);
		
		JButton btnNewButton = new JButton("Digest");
		btnNewButton.setBounds(920, 62, 108, 30);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String messaggio = textArea.getText();
				try {
					txtDigest.setText(SHA.encryptSHA(messaggio, "SHA-256"));
				} catch (NoSuchAlgorithmException e1) {
					
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frmFirmaDigitale.getContentPane().add(btnNewButton);
		
		txtDigest = new JTextField();
		txtDigest.setBounds(80, 366, 948, 30);
		frmFirmaDigitale.getContentPane().add(txtDigest);
		txtDigest.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Digest");
		lblNewLabel.setBounds(10, 366, 60, 20);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frmFirmaDigitale.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton_1 = new JButton("Firma");
		btnNewButton_1.setBounds(920, 140, 108, 30);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String teststring = txtDigest.getText();
				// it uses the private key to sign 
				 byte[] encrypted = Rsa.decrypt(teststring.getBytes());
			        			        
			     txtRsa.setText(Rsa.toHex(encrypted));
				
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frmFirmaDigitale.getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("Firma");
		lblNewLabel_1.setBounds(10, 417, 60, 17);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frmFirmaDigitale.getContentPane().add(lblNewLabel_1);
		
		txtRsa = new JTextArea();
		txtRsa.setBounds(80, 418, 948, 117);
		txtRsa.setLineWrap(true);
		frmFirmaDigitale.getContentPane().add(txtRsa);
	}
		
}



