package com.example.ecsite.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//@EntityでEntityクラスだと定義することができる
//@TableはDBのどのテーブルEntityかを指定する
@Entity
@Table(name="user_data")
public class User {
	
	//DBの各カラムをフィールドとして定義する
	//@Idでプライマリｷｰであることを指定する
	//@Columnでテーブルのどのカラムとマッピングするかを指定する
	//@GeneratedValueでIDフィールドのふるまい方を指定する。
	//データベースのID列を使用して主キー値を生成する。
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="user_name")
	private String userName;
	
	@Column(name="password")
	private String password;
	
	@Column(name="full_name")
	private String fullName;
	
	@Column(name="is_admin")
	private String isAdmin;
	
	//各フィールドのgetter,setterを作成してUserEntityクラスを完成させる
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
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
	
	public String getFullName() {
		return fullName;
	}
	
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public String getIsAdmin() {
		return isAdmin;
	}
	
	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}
	
}
