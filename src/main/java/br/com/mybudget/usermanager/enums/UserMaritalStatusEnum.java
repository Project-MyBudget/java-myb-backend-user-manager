package br.com.mybudget.usermanager.enums;

public enum UserMaritalStatusEnum {

	SINGLE('S'),
	MARRIED('M'),
	DIVORCED('D'),
	WIDOWER('W');
	
	private char maritinalStatus;

	private UserMaritalStatusEnum(char maritinalStatus) {
		this.maritinalStatus = maritinalStatus;
	}

	public char getMaritinalStatus() {
		return maritinalStatus;
	}

	public void setMaritinalStatus(char maritinalStatus) {
		this.maritinalStatus = maritinalStatus;
	}

}
