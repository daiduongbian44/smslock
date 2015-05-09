package bt.smslock.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import bt.smslock.R;
import bt.smslock.data.entities.ThreadSMSEntity;

public class ThreadAdapter extends BaseAdapter {

	private Context context;
	private ArrayList listThread;
	private static LayoutInflater inflater = null;
	ThreadSMSEntity tempValues=null;
	public Resources res;
	private int i = 0;

	public ThreadAdapter(Context cont, ArrayList list, Resources resLocal) {
		context = cont;
		listThread = list;
		res = resLocal;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}

	@Override
	public int getCount() {
		return 0;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	public static class ViewHolder {
		public ImageView threadImage;
		public TextView threadName;
		public TextView threadPhoneNumber;
		public TextView threadTime;

	}

	/****** Depends upon data size called for each row , Create each ListView row *****/
	public View getView(int position, View convertView, ViewGroup parent) {

		View vi = convertView;
		ViewHolder holder;

		if (convertView == null) {
			vi = inflater.inflate(R.layout.item_thread, null);
			holder = new ViewHolder();
			holder.threadName = (TextView) vi.findViewById(R.id.thread_name);
			holder.threadPhoneNumber = (TextView) vi.findViewById(R.id.thread_phone_number);
			holder.threadImage = (ImageView) vi.findViewById(R.id.thread_icon);

			/************ Set holder with LayoutInflater ************/
			vi.setTag(holder);
		} else
			holder = (ViewHolder) vi.getTag();

		if (listThread.size() <= 0) {
			holder.threadName .setText("No Data");

		} else {
			/***** Get each Model object from Arraylist ********/
			tempValues = null;
			tempValues = (ThreadSMSEntity) listThread
					
					.get(position);

			/************ Set Model values in Holder elements ***********/

			holder.text.setText(tempValues.getCompanyName());
			holder.text1.setText(tempValues.getUrl());
			holder.image.setImageResource(res.getIdentifier(
					"com.androidexample.customlistview:drawable/"
							+ tempValues.getImage(), null, null));

			/******** Set Item Click Listner for LayoutInflater for each row *******/

			vi.setOnClickListener(new OnItemClickListener(position));
		}
		return vi;
	}

	@Override
	public void onClick(View v) {

	}

	private class OnItemClickListener implements OnClickListener {
		private int mPosition;

		OnItemClickListener(int position) {
			mPosition = position;
		}

		@Override
		public void onClick(View arg0) {

			CustomListViewAndroidExample sct = (CustomListViewAndroidExample) activity;

			sct.onItemClick(mPosition);
		}
	}

}
