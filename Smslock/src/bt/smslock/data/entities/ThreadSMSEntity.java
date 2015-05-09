package bt.smslock.data.entities;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by manh on 9/5/2015.
 */
public class ThreadSMSEntity {
	private String _threadId;
	private ArrayList<SMSEntity> _listMessages;

	public ThreadSMSEntity() {
		setListMessages(new ArrayList<SMSEntity>());
	}

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

	public void sortMessage() {
		Collections.sort(_listMessages, SMSEntity.CompareDate);
	}

	public SMSEntity getLatestSMS() {
		if (_listMessages == null || _listMessages.size() == 0) {
			return null;
		}
		return getListMessages().get(getListMessages().size() - 1);

	}
}