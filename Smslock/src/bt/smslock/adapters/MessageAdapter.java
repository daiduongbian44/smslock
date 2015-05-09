package bt.smslock.adapters;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import bt.smslock.R;
import bt.smslock.data.entities.SMSEntity;

public class MessageAdapter extends BaseAdapter {
	private Context context;
	private TextView chatText;
	private ArrayList<SMSEntity> chatMessageList = new ArrayList<SMSEntity>();
	private LinearLayout singleMessageContainer;
	
	public MessageAdapter(Context context, ArrayList<SMSEntity> chatMessageList) {
		this.context = context;
		this.chatMessageList = chatMessageList;
	}
	
	public int getCount() {
		return this.chatMessageList.size();
	}

	public SMSEntity getItem(int index) {
		return this.chatMessageList.get(index);
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		if (row == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row = inflater.inflate(R.layout.fragment_message, parent,
					false);
		}
		singleMessageContainer = (LinearLayout) row
				.findViewById(R.id.singleMessageContainer);
		SMSEntity chatMessageObj = getItem(position);
		chatText = (TextView) row.findViewById(R.id.message);
		chatText.setText(chatMessageObj.getHashMessage().get(SMSEntity.BODY));
		String type = chatMessageObj.getHashMessage().get(SMSEntity.TYPE);
		if (!type.isEmpty()) {
			int typeInt = Integer.parseInt(type);
			if (typeInt == 1) {
				chatText.setBackgroundResource(R.drawable.bubble_a);
				singleMessageContainer.setGravity(Gravity.LEFT);
			} else if (typeInt == 2) {
				chatText.setBackgroundResource(R.drawable.bubble_b);
				singleMessageContainer.setGravity(Gravity.RIGHT);
			}
		}
		return row;
	}

	public Bitmap decodeToBitmap(byte[] decodedByte) {
		return BitmapFactory
				.decodeByteArray(decodedByte, 0, decodedByte.length);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}
}
