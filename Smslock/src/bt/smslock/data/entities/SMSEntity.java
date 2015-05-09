package bt.smslock.data.entities;

import android.text.format.DateFormat;

import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

/**
 * Created by manh on 9/5/2015.
 */
public class SMSEntity {
	public static String ID = "_id";
	public static String THREAD_ID = "thread_id";
	public static String ADDRESS = "address";
	public static String PERSON = "person";
	public static String DATE = "date";
	public static String DATE_SENT = "date_sent";
	public static String PROTOCOL = "protocol";
	public static String READ = "read";
	public static String STATUS = "status";
	public static String TYPE = "type";
	public static String REPLY_PATH_PRESENT = "reply_path_present";
	public static String SUBJECT = "subject";
	public static String BODY = "body";
	public static String SERVICE_CENTER = "service_center";
	public static String LOCKED = "locked";
	public static String PHONE_ID = "phone_id";
	public static String ERROR_CODE = "error_code";
	public static String CREATOR = "creator";
	public static String SEEN = "seen";
	public static String PRIORITY = "priority";

	private HashMap<String, String> mHashMessage;

	public HashMap<String, String> getHashMessage() {
		return mHashMessage;
	}

	private void setHashMessage(HashMap<String, String> mHashMessage) {
		this.mHashMessage = mHashMessage;
	}

	public SMSEntity() {
		setHashMessage(new HashMap<String, String>());
	}

	public String getDateString() {
		long time = 0;
		try {
			time = Long.parseLong(getHashMessage().get(DATE));
			Calendar cal = Calendar.getInstance(Locale.ENGLISH);
			cal.setTimeInMillis(time);
			String date = DateFormat.format("dd-MM-yyyy", cal).toString();
			return date;
		} catch (Exception e) {
			return "";
		}
	}

	private Date getDate() {
		return new Date(Long.parseLong(getHashMessage().get(DATE)));
	}

	public static Comparator<SMSEntity> CompareThreadId = new Comparator<SMSEntity>() {
		@Override
		public int compare(SMSEntity lhs, SMSEntity rhs) {
			return lhs.getHashMessage().get(THREAD_ID)
					.compareTo(rhs.getHashMessage().get(THREAD_ID));
		}
	};

	public static Comparator<SMSEntity> CompareDate = new Comparator<SMSEntity>() {
		@Override
		public int compare(SMSEntity lhs, SMSEntity rhs) {
			return lhs.getDate().compareTo(rhs.getDate());
		}
	};
}
