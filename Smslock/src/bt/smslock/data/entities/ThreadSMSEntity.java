package bt.smslock.data.entities;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by manh on 9/5/2015.
 */
public class ThreadSMSEntity {
	private String _threadId;
	private ArrayList<SMSEntity> _listMessages;
	private String _contactName;
	private String _address;

	public ThreadSMSEntity() {
		setListMessages(new ArrayList<SMSEntity>());
	}

	/*
	 * Getter and setter
	 */
	public String getThreadId() {
		return _threadId;
	}

	public void setThreadId(String _threadId) {
		this._threadId = _threadId;
	}

	public ArrayList<SMSEntity> getListMessages() {
		return _listMessages;
	}

	private void setListMessages(ArrayList<SMSEntity> _listMessages) {
		this._listMessages = _listMessages;
	}

	public String getContactName() {
		return _contactName;
	}

	public void setContactName(String _contactName) {
		this._contactName = _contactName;
	}
	
	public String getAddress() {
		return _address;
	}

	public void setAddress(String _address) {
		this._address = _address;
	}
	
	public int getCountOfMessages(){
		return _listMessages.size();
	}
	/*
	 * End Getter, Setter
	 */

	public void sortMessage() {
		Collections.sort(_listMessages, SMSEntity.CompareDate);
	}
	
	public void fitAddress(){
		if(_listMessages.size() > 0){
			setAddress(_listMessages.get(0)
					.getHashMessage().get(SMSEntity.ADDRESS));
		}
	}

	public SMSEntity getLatestSMS() {
		if (_listMessages == null || _listMessages.size() == 0) {
			return null;
		}
		return getListMessages().get(getListMessages().size() - 1);
	}
}