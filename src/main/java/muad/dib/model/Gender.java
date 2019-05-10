package muad.dib.model;

import java.io.Serializable;


public enum Gender implements Serializable {
	Male("Male"), Female("Female"), Trans("Trans"), Pan("Pan"), Queer("Queer");

	private String value;

	private Gender(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public String getMsg() {
		return this.value;
	}

	public static Gender fromValue(String code) {
		for (Gender status : Gender.values()) {
			if (status.getValue().equals(code)) {
				return status;
			}
		}
		throw new UnsupportedOperationException("The code " + code + " is not supported!");
	}
}
