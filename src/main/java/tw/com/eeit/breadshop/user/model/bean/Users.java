package tw.com.eeit.breadshop.user.model.bean;

import java.io.Serializable;

public class Users implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String account;
	private String password;
	private String photo;

	public Users() {
	}

	public Users(String account, String password) {
		super();
		this.account = account;
		this.password = password;
	}

	public Users(String id, String account, String password, String photo) {
		super();
		this.id = id;
		this.account = account;
		this.password = password;
		this.photo = photo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

}
