package bt.smslock.adapters;

import java.util.ArrayList;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import bt.smslock.R;
import bt.smslock.data.entities.SMSEntity;
import bt.smslock.data.entities.ThreadSMSEntity;

@SuppressLint("ViewHolder")
public class ThreadAdapter extends BaseAdapter {

	private ArrayList<ThreadSMSEntity> listThread;
	private Context context;
	private static LayoutInflater inflater = null;

	public ThreadAdapter(Context cont, ArrayList<ThreadSMSEntity> list) {
		listThread = list;
		context = cont;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return listThread.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	private class Holder {
		TextView name;
		TextView latest;
		TextView time;
		ImageView img;
	}

	@SuppressLint("InflateParams")
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		Holder holder = new Holder();
		View rowView;
		rowView = inflater.inflate(R.layout.item_thread, null, false);
		holder.name = (TextView) rowView.findViewById(R.id.thread_name);
		holder.img = (ImageView) rowView.findViewById(R.id.thread_icon);
		holder.latest = (TextView) rowView.findViewById(R.id.thread_latest);
		holder.time = (TextView) rowView.findViewById(R.id.thread_time);
		
		holder.name.setText(listThread.get(position).getContactName());
		holder.latest.setText(listThread.get(position).getLatestSMS()
				.getHashMessage().get(SMSEntity.BODY));
		holder.img.setImageResource(R.drawable.ic_launcher);
		holder.time.setText(listThread.get(position).getLatestSMS().getDateString());
		
		rowView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		});
		return rowView;
	}
}