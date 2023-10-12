package br.com.mybudget.usermanager.model.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "TB_USER")
public class UserEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
	private long userId;

    @Column(name = "FIRST_NAME", nullable = false)
    private String userFirstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String userLastName;

    @Column(name = "USERCPF", unique = true, nullable = false)
    private String userCpf;

    @Column(name = "DATEOFBIRTH", nullable = false)
    private Date userDateOfBirth;

    @Column(name = "GENRE", nullable = false)
    private String userGender;

    @Column(name = "PHONE", nullable = false)
    private String userPhoneNumber;

    @Column(name = "EMAIL", nullable = false)
    private String userEmail;

    @Column(name = "STATUS", nullable = false)
    private char userStatus;

    @Column(name = "PASSWORD", nullable = false)
    private String userPassword;
    
    @Column(name = "CREATE_AT", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP", insertable=false, updatable = false)
    private Date userCreateAt;

    @Column(name = "UPDATE_AT", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP", insertable=false, updatable = false)
    private Date userUpdateAt;

	public UserEntity(long userId, String userFirstName, String userLastName, String userCpf, Date userDateOfBirth,
			String userGender, String userPhoneNumber, String userEmail, char userStatus, String userPassword) {
		super();
		this.userId = userId;
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.userCpf = userCpf;
		this.userDateOfBirth = userDateOfBirth;
		this.userGender = userGender;
		this.userPhoneNumber = userPhoneNumber;
		this.userEmail = userEmail;
		this.userStatus = userStatus;
		this.userPassword = userPassword;
	}

}
