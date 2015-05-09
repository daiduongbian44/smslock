package bt.smslock;

import android.app.Activity;
import android.os.Bundle;
import bt.smslock.fragments.FragmentListThreadMessage;

public class SmsLockActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sms_lock);
		
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new FragmentListThreadMessage()).commit();
		}
	}
	
}
