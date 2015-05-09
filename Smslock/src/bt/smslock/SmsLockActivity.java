package bt.smslock;

import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;
import bt.smslock.data.daos.DBHelper;
import bt.smslock.data.dataloaders.ThreadSMSLoader;
import bt.smslock.fragments.FragmentListThreadMessage;

public class SmsLockActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sms_lock);
		
		// kiểm tra và tạo db
		try {
			DBHelper.InitContext(getApplicationContext());
			DBHelper.getInstance().createDataBase();
		} catch (IOException e) {
			Toast.makeText(getApplicationContext(),
					"Having at least an error in dbsystem", Toast.LENGTH_LONG)
					.show();
			e.printStackTrace();
		}
		
		// load tin nhắn
		ThreadSMSLoader threadSMSLoader = new ThreadSMSLoader(this);
        threadSMSLoader.loadAllThreadSMS();
        
        Toast.makeText(getApplicationContext(), "Count: " + 
        threadSMSLoader.getListThreadMessages().size(),Toast.LENGTH_LONG).show();
		
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new FragmentListThreadMessage(
							threadSMSLoader.getListThreadMessages())).commit();
		}
	}
	
}
