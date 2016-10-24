package com.shankes.turing;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.shankes.turing.adapter.TuringInfoAdapter;
import com.shankes.turing.domain.MessageType;
import com.shankes.turing.domain.TuringBaseInfo;
import com.shankes.turing.util.TuringHandler;
import com.shankes.turing.util.TuringUtil;

public class MainActivity extends Activity implements OnClickListener {

	public ListView mChatMiddleListView;
	public TuringInfoAdapter mTuringInfoAdapter;
	public List<TuringBaseInfo> mData;

	// ��Activity�������Layout
	private View rootView;
	private EditText mChatBottomSendEditText;
	private Button mChatBottomSendButton;

	private View chatMiddleRootView;

	private static MainActivity instance;

	public static MainActivity getInstance() {
		if (instance == null) {
			instance = new MainActivity();
		}
		return instance;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);// ȥ��������
		setContentView(R.layout.activity_chat_main);
		instance = this;
		String infoValue = "���ʲô����";
		String locValue = null;
		String useridValue = "shankes";

		TuringUtil.turingPost(TuringHandler.getInstance(this).handler, infoValue, locValue, useridValue);

		initView();
		initData();
		initEventClick();
		initRootView();
	}

	private void initRootView() {
		// ����layout���ü����������䲼�ַ����仯�¼�
		rootView.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
			@Override
			public void onGlobalLayout() {
				// �Ƚ�Activity�������뵱ǰ���ֵĴ�С
				int heightDiff = rootView.getRootView().getHeight() - rootView.getHeight();
				if (heightDiff > 100) {
					// ��С����100ʱ��һ��Ϊ��ʾ��������¼�
					mTuringInfoAdapter.notifyDataSetChanged();
					chatMiddleRootView.setBackgroundResource(R.drawable.bg_flower_half);
				} else {
					// ��СС��100ʱ��Ϊ����ʾ������̻������������
					chatMiddleRootView.setBackgroundResource(R.drawable.bg_flower);
				}
			}
		});
	}

	private void initView() {
		chatMiddleRootView = (View) findViewById(R.id.chat_middle_root);
		rootView = (View) findViewById(R.id.chat_main_root);
		mChatMiddleListView = (ListView) findViewById(R.id.chat_middle_listview);
		mChatBottomSendEditText = (EditText) findViewById(R.id.chat_bottom_send_edittext);
		mChatBottomSendButton = (Button) findViewById(R.id.chat_bottom_send_button);
	}

	private void initEventClick() {
		mChatBottomSendButton.setOnClickListener(this);
	}

	private void initData() {
		mData = new ArrayList<TuringBaseInfo>();
		mTuringInfoAdapter = new TuringInfoAdapter(this, mData, mChatMiddleListView);
		mChatMiddleListView.setAdapter(mTuringInfoAdapter);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.chat_bottom_send_button:
			String infoValue = mChatBottomSendEditText.getText().toString().trim();

			TuringBaseInfo turingBaseInfo = new TuringBaseInfo();
			turingBaseInfo.setMessageType(MessageType.TO);
			turingBaseInfo.setText(infoValue);
			turingBaseInfo.setTime(new Date());
			if (!TextUtils.isEmpty(infoValue)) {
				mData.add(turingBaseInfo);
				mTuringInfoAdapter.notifyDataSetChanged();
			}

			String locValue = null;
			String useridValue = "shankes";
			TuringUtil.turingPost(TuringHandler.getInstance(this).handler, infoValue, locValue, useridValue);

			mChatBottomSendEditText.setText("");
			break;

		default:
			break;
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
