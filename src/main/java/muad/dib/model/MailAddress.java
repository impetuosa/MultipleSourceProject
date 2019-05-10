package muad.dib.model;

public class MailAddress extends Address {
	private String host;
	private String username;

	public void setHost(String host) {
		this.host = host;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public String getHost() {
		return host;
	}

}
