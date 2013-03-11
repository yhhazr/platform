package com.sz7road.web.model.user;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.sz7road.web.model.role.Role;

/**
 * 
 * @author hai.yuan
 * 
 */
@Entity
@Table(name = "user")
public class User implements UserDetails {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String userName;
	private String password;
	private String email;
	private Date createDate;
	
	@Transient
	private String username;
	@Transient
	private Collection<GrantedAuthority> authorities;
	@Transient
	private boolean accountNonExpired;
	@Transient
	private boolean accountNonLocked;
	@Transient
	private boolean credentialsNonExpired;

	public User() {
	};

	

	public User(int id, String userName, String password, String email,
			Date createDate,String username2,
			Collection<GrantedAuthority> authorities, boolean accountNonExpired,
			boolean accountNonLocked, boolean credentialsNonExpired) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.createDate = createDate;
		this.username = userName;
		this.authorities = authorities;
		this.accountNonExpired = accountNonExpired;
		this.accountNonLocked = accountNonLocked;
		this.credentialsNonExpired = credentialsNonExpired;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
