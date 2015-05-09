package bt.smslock.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import bt.smslock.R;
import bt.smslock.adapters.MessageAdapter;
import bt.smslock.data.entities.ThreadSMSEntity;

public class FragmentListMessages extends Fragment {
	
	private ThreadSMSEntity threadSMSEntity;
	private ListView lvMessage;
	
	public FragmentListMessages(ThreadSMSEntity threadSMSEntity) {
		this.threadSMSEntity = threadSMSEntity;
	}
	private View rootView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_message,
				container, false);
		initComponets();
		MessageAdapter adapter = new MessageAdapter(getActivity()
				.getApplicationContext(), threadSMSEntity.getListMessages());
		lvMessage.setAdapter(adapter);
		return rootView;
	}
	
	public void initComponets() {
		lvMessage = (ListView) rootView.findViewById(R.id.lv_message);
	}
}