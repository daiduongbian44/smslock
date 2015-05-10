package bt.smslock.interfaces;

import bt.smslock.data.entities.ThreadSMSEntity;

public interface ITranferToContactMessage {
	public void tranferToContactMessage(ThreadSMSEntity entity);
	public void tranferToNewMessage();
}
