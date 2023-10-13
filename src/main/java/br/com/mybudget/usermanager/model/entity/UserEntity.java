package br.com.mybudget.usermanager.model.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

}
