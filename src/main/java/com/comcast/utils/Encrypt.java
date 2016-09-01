package com.comcast.utils;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import org.apache.commons.net.util.Base64;

public class Encrypt {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String pwd = JOptionPane.showInputDialog("Enter Your Password");
		if (pwd != null && !pwd.equals(""))
		{
			
			byte[]  bytesEncoded=Base64.encodeBase64(pwd.getBytes());
			JTextArea ta = new JTextArea(10, 10);
			String encpwd = new String(bytesEncoded);
			ta.setText(encpwd);
			ta.setOpaque(false);
	    	ta.setEditable(false);
			JOptionPane.showMessageDialog(null,ta);

		}else
		{
			JOptionPane.showMessageDialog(null,"Password cannot be NULL. Enter a Passord");
		}

	}

}
