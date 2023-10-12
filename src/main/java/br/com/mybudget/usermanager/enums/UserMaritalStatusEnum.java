package br.com.mybudget.usermanager.enums;

public enum UserMaritalStatusEnum {

	SOLTEIRO('S'),
	CASADO('C'),
	DIVORCIADO('D'),
	VIUVO('V');
	
	private char estadoCivil;

	private UserMaritalStatusEnum(char estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public char getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(char estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

}
