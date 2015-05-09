package bt.smslock.data.entities;

public class UserEntity {
	private int _userId;
	private String _userContact;
	private String _userNumber;

	public int getUserId() {
		return _userId;
	}

	public void setUserId(int _userId) {
		this._userId = _userId;
	}

	public String getUserContact() {
		return _userContact;
	}

	public void setUserContact(String _userContact) {
		this._userContact = _userContact;
	}

	public String getUserNumber() {
		return _userNumber;
	}

	public void setUserNumber(String _userNumber) {
		this._userNumber = _userNumber;
	}

}
