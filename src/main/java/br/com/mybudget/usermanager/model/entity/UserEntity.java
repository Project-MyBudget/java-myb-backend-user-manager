
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
    @Column(name = "ID_USER")
	private long idUser;

    @Column(name = "FIRST_NAME", nullable = false, columnDefinition = "VARCHAR(20) NOT NULL")
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false, columnDefinition = "VARCHAR(20) NOT NULL")
    private String lastName;

    @Column(name = "DATE_OF_BIRTH", nullable = false, columnDefinition = "DATE NOT NULL")
    private Date dateOfBirth;

    @Column(name = "GENRE", nullable = false, columnDefinition = "VARCHAR(10) NOT NULL")
    private String gender;

    @Column(name = "CHILDREN_NUMBER", nullable = false, columnDefinition = "INT NOT NULL DEFAULT 0")
    private int childrenNumber;
    
    @Column(name = "PHONE", nullable = false, columnDefinition = "VARCHAR(20) NOT NULL")
    private String phoneNumber;

    @Column(name = "EMAIL", nullable = false, columnDefinition = "VARCHAR(60) NOT NULL")
    private String email;

    @Column(name = "STATUS", nullable = false, columnDefinition = "CHAR(1) NOT NULL")
    private char status;

    @Column(name = "PASSWORD", nullable = false, columnDefinition = "VARCHAR(255) NOT NULL")
    private String password;
    
    @Column(name = "CIVIL_STATUS", nullable = false, columnDefinition = "CHAR(1) NOT NULL DEFAULT 'S'")
    private char civilStatus;
    
    @Column(name = "CREATE_AT", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP", insertable=false, updatable = false)
    private Date createAt;

    @Column(name = "UPDATE_AT", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP", insertable=false, updatable = false)
    private Date updateAt;
    
}
