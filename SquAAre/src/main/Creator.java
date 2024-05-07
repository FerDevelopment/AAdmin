package main;

public class Creator extends Person {
	private String creatorCode = "";
	private String dmca = "";

	public Creator(String code, String dmca) {
		this.creatorCode = code;
		this.dmca = dmca;
	}

	public String cosas() {
		return creatorCode + dmca;
	}
}
