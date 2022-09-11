package com.example.ecsite.model.form;

import java.io.Serializable;

//Serializableを継承することでそのクラスをByte配列に変換
//他仮想マシンに送信やファイル保存が出来るようになる　　　　←もっと調べる。。
public class LoginForm implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String userName;
	private String password;
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
}
