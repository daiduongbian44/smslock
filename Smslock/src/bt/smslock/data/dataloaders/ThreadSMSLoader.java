package bt.smslock.data.dataloaders;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import bt.smslock.data.entities.SMSEntity;
import bt.smslock.data.entities.ThreadSMSEntity;

/**
 * Created by manh on 9/5/2015.
 */
public class ThreadSMSLoader {
	private Activity mActivity;
	private ArrayList<SMSEntity> mListMessages;
	private ArrayList<ThreadSMSEntity> mListThreadMessages;

	public ThreadSMSLoader(Activity mActivity) {
		this.mActivity = mActivity;
		mListMessages = new ArrayList<>();
		mListThreadMessages = new ArrayList<>();
	}

	public void loadMessageInbox() {
		String INBOX = "content://sms/inbox";
		// read inbox

		Cursor cursorInbox = mActivity.getContentResolver().query(
				Uri.parse(INBOX), null, null, null, null);

		if (cursorInbox.moveToFirst()) {
			do {
				SMSEntity entity = new SMSEntity();
				for (int idx = 0; idx < cursorInbox.getColumnCount(); idx++) {
					entity.getHashMessage();
					entity.getHashMessage().put(cursorInbox.getColumnName(idx),
							cursorInbox.getString(idx));
				}
				mListMessages.add(entity);
			} while (cursorInbox.moveToNext());
		}
		cursorInbox.close();
	}

	public void loadMessageSent() {
		String SENT = "content://sms/sent";

		// read sent
		Cursor cursorSent = mActivity.getContentResolver().query(
				Uri.parse(SENT), null, null, null, null);

		if (cursorSent.moveToFirst()) {
			do {
				SMSEntity entity = new SMSEntity();
				for (int idx = 0; idx < cursorSent.getColumnCount(); idx++) {
					entity.getHashMessage();
					entity.getHashMessage().put(cursorSent.getColumnName(idx),
							cursorSent.getString(idx));
				}
				mListMessages.add(entity);
			} while (cursorSent.moveToNext());
		}
		cursorSent.close();
	}

	public void loadAllThreadSMS() {
		mListMessages.clear();
		mListThreadMessages.clear();

		// load messages
		loadMessageInbox();
		loadMessageSent();

		// load thread sms
		Collections.sort(mListMessages, SMSEntity.CompareThreadId);

		HashMap<String, ThreadSMSEntity> hashCountThread = new HashMap<>();
		for (int i = 0; i < mListMessages.size(); ++i) {
			String key = mListMessages.get(i).getHashMessage()
					.get(SMSEntity.THREAD_ID);
			if (!hashCountThread.containsKey(key)) {
				hashCountThread.put(key, new ThreadSMSEntity());
			}

			ThreadSMSEntity threadMessage = hashCountThread.get(key);
			threadMessage.setThreadId(key);
			threadMessage.getListMessages().add(mListMessages.get(i));
		}

		// assign message thread
		Object[] listKeys = hashCountThread.keySet().toArray();
		for (int i = 0; i < listKeys.length; ++i) {
			mListThreadMessages.add(hashCountThread.get(listKeys[i]));
		}
		for (int i = 0; i < mListThreadMessages.size(); ++i) {
			mListThreadMessages.get(i).sortMessage();
		}
	}

	public ArrayList<SMSEntity> getListMessages() {
		return mListMessages;
	}

	public ArrayList<ThreadSMSEntity> getListThreadMessages() {
		return mListThreadMessages;
	}
}