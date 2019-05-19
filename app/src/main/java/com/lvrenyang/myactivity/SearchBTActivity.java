package com.lvrenyang.myactivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.lvrenyang.io.BTPrinting;
import com.lvrenyang.io.Canvas;
import com.lvrenyang.io.IOCallBack;
import com.lvrenyang.sample3.R;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SearchBTActivity extends AppCompatActivity implements OnClickListener,
		IOCallBack {

	private LinearLayout linearlayoutdevices;
	private ProgressBar progressBarSearchStatus;

	private BroadcastReceiver broadcastReceiver = null;
	private IntentFilter intentFilter = null;

	final int PICK_IMAGE_REQUEST = 1;
	final int BUTTON_COUNT = 8;

	Button btnSearch, btnDisconnect;
	SearchBTActivity mActivity;

	ExecutorService es = Executors.newScheduledThreadPool(30);
	Canvas mCanvas = new Canvas();
	BTPrinting mBt = new BTPrinting();
	Button[] btnPrint = new Button[BUTTON_COUNT];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_searchbt);

		mActivity = this;

		progressBarSearchStatus = findViewById(R.id.progressBarSearchStatus);
		linearlayoutdevices = findViewById(R.id.linearlayoutdevices);

		btnSearch = findViewById(R.id.buttonSearch);
		btnDisconnect = findViewById(R.id.buttonDisconnect);
		for (int i = 0; i < BUTTON_COUNT; i++) {
			btnPrint[i] = findViewById(getResources().getIdentifier(String.format("buttonPrint%d", i+1), "id", getPackageName()));
			btnPrint[i].setOnClickListener(this);
			btnPrint[i].setEnabled(false);
		}
		btnSearch.setOnClickListener(this);
		btnDisconnect.setOnClickListener(this);
		btnSearch.setEnabled(true);
		btnDisconnect.setEnabled(false);

		mCanvas.Set(mBt);
		mBt.SetCallBack(this);

		initBroadcast();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		uninitBroadcast();
		btnDisconnect.performClick();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

			Uri uri = data.getData();

			try {
				Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
				// Log.d(TAG, String.valueOf(bitmap));

				//ImageView imageView = (ImageView) findViewById(R.id.imageView);
				//imageView.setImageBitmap(bitmap);
				Prints.PrintBitmap(getApplicationContext(), mCanvas, AppStart.nPrintWidth, AppStart.nPrintHeight, bitmap);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
			case R.id.buttonSearch: {
				BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
				if (null == adapter) {
					finish();
					break;
				}

				if (!adapter.isEnabled()) {
					if (adapter.enable()) {
						while (!adapter.isEnabled())
							;
					} else {
						finish();
						break;
					}
				}

				adapter.cancelDiscovery();
				linearlayoutdevices.removeAllViews();
				adapter.startDiscovery();
				break;
			}

			case R.id.buttonDisconnect:
				es.submit(new TaskClose(mBt));
				break;

			case R.id.buttonPrint1:
				btnPrint[0].setEnabled(false);
				es.submit(new TaskPrint(mCanvas));
				break;

			case R.id.buttonPrint2:
				/*
				btnPrint2.setEnabled(false);
				es.submit(new TaskPrint2(mCanvas));
				*/
				Intent intent = new Intent();
				// Show only images, no videos or anything else
				intent.setType("image/*");
				intent.setAction(Intent.ACTION_GET_CONTENT);
				// Always show the chooser (if there are multiple options available)
				startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);

				break;
		}
	}

	private void setButtonsEnable(boolean enable) {
		for (int i = 0; i < BUTTON_COUNT; i++) {
			btnPrint[i].setEnabled(enable);
		}
	}
	private void initBroadcast() {
		broadcastReceiver = new BroadcastReceiver() {

			@Override
			public void onReceive(Context context, Intent intent) {
				// TODO Auto-generated method stub
				String action = intent.getAction();
				BluetoothDevice device = intent
						.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

				if (BluetoothDevice.ACTION_FOUND.equals(action)) {
					if (device == null)
						return;
					final String address = device.getAddress();
					String name = device.getName();
					if (name == null)
						name = "BT";
					else if (name.equals(address))
						name = "BT";
					Button button = new Button(context);
					button.setText(name + ": " + address);

					for (int i = 0; i < linearlayoutdevices.getChildCount(); ++i) {
						Button btn = (Button) linearlayoutdevices.getChildAt(i);
						if (btn.getText().equals(button.getText())) {
							return;
						}
					}

					button.setGravity(android.view.Gravity.CENTER_VERTICAL
							| Gravity.LEFT);
					button.setOnClickListener(new OnClickListener() {

						public void onClick(View arg0) {
							// TODO Auto-generated method stub
							Toast.makeText(mActivity, "Connecting...",
									Toast.LENGTH_SHORT).show();
							btnSearch.setEnabled(false);
							linearlayoutdevices.setEnabled(false);
							for (int i = 0; i < linearlayoutdevices
									.getChildCount(); ++i) {
								Button btn = (Button) linearlayoutdevices
										.getChildAt(i);
								btn.setEnabled(false);
							}
							btnDisconnect.setEnabled(false);
							setButtonsEnable(false);
							es.submit(new TaskOpen(mBt, address, mActivity));
						}
					});
					button.getBackground().setAlpha(100);
					linearlayoutdevices.addView(button);
				} else if (BluetoothAdapter.ACTION_DISCOVERY_STARTED
						.equals(action)) {
					progressBarSearchStatus.setIndeterminate(true);
				} else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED
						.equals(action)) {
					progressBarSearchStatus.setIndeterminate(false);
				}

			}

		};
		intentFilter = new IntentFilter();
		intentFilter.addAction(BluetoothDevice.ACTION_FOUND);
		intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
		intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
		registerReceiver(broadcastReceiver, intentFilter);
	}

	private void uninitBroadcast() {
		if (broadcastReceiver != null)
			unregisterReceiver(broadcastReceiver);
	}

	public class TaskOpen implements Runnable {
		BTPrinting bt = null;
		String address = null;
		Context context = null;

		public TaskOpen(BTPrinting bt, String address, Context context) {
			this.bt = bt;
			this.address = address;
			this.context = context;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			bt.Open(address, context);
		}
	}

	public class TaskPrint implements Runnable {
		Canvas canvas = null;

		public TaskPrint(Canvas canvas) {
			this.canvas = canvas;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub

			final boolean bPrintResult = Prints.PrintTicket(getApplicationContext(), canvas, AppStart.nPrintWidth, AppStart.nPrintHeight);
			final boolean bIsOpened = canvas.GetIO().IsOpened();

			mActivity.runOnUiThread(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					Toast.makeText(
							mActivity.getApplicationContext(),
							bPrintResult ? getResources().getString(
									R.string.printsuccess) : getResources()
									.getString(R.string.printfailed),
							Toast.LENGTH_SHORT).show();
					//mActivity.btnPrint.setEnabled(bIsOpened);
					mActivity.setButtonsEnable(bIsOpened);
				}
			});

		}
	}

	public class TaskPrint2 implements Runnable {
		Canvas canvas = null;

		public TaskPrint2(Canvas canvas) {
			this.canvas = canvas;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub

			final boolean bPrintResult = Prints.PrintTicket2(getApplicationContext(), canvas, AppStart.nPrintWidth, AppStart.nPrintHeight);
			final boolean bIsOpened = canvas.GetIO().IsOpened();

			mActivity.runOnUiThread(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					Toast.makeText(
							mActivity.getApplicationContext(),
							bPrintResult ? getResources().getString(
									R.string.printsuccess) : getResources()
									.getString(R.string.printfailed),
							Toast.LENGTH_SHORT).show();
					//mActivity.btnPrint2.setEnabled(bIsOpened);
					mActivity.setButtonsEnable(bIsOpened);
				}
			});

		}
	}

	public class TaskClose implements Runnable {
		BTPrinting bt = null;

		public TaskClose(BTPrinting bt) {
			this.bt = bt;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			bt.Close();
		}

	}

	@Override
	public void OnOpen() {
		// TODO Auto-generated method stub
		this.runOnUiThread(new Runnable() {

			@Override
			public void run() {
				btnDisconnect.setEnabled(true);
				setButtonsEnable(true);
				//btnPrint.setEnabled(true);
				//btnPrint2.setEnabled(true);
				btnSearch.setEnabled(false);
				linearlayoutdevices.setEnabled(false);
				for (int i = 0; i < linearlayoutdevices.getChildCount(); ++i) {
					Button btn = (Button) linearlayoutdevices.getChildAt(i);
					btn.setEnabled(false);
				}
				Toast.makeText(mActivity, "Connected", Toast.LENGTH_SHORT)
						.show();
			}
		});
	}

	@Override
	public void OnOpenFailed() {
		// TODO Auto-generated method stub
		this.runOnUiThread(new Runnable() {

			@Override
			public void run() {
				btnDisconnect.setEnabled(false);
				setButtonsEnable(false);
				//btnPrint.setEnabled(false);
				//btnPrint2.setEnabled(false);
				btnSearch.setEnabled(true);
				linearlayoutdevices.setEnabled(true);
				for (int i = 0; i < linearlayoutdevices.getChildCount(); ++i) {
					Button btn = (Button) linearlayoutdevices.getChildAt(i);
					btn.setEnabled(true);
				}
				Toast.makeText(mActivity, "Failed", Toast.LENGTH_SHORT).show();
			}
		});
	}

	@Override
	public void OnClose() {
		// TODO Auto-generated method stub
		this.runOnUiThread(new Runnable() {

			@Override
			public void run() {
				btnDisconnect.setEnabled(false);
				setButtonsEnable(false);
				//btnPrint.setEnabled(false);
				//btnPrint2.setEnabled(false);
				btnSearch.setEnabled(true);
				linearlayoutdevices.setEnabled(true);
				for (int i = 0; i < linearlayoutdevices.getChildCount(); ++i) {
					Button btn = (Button) linearlayoutdevices.getChildAt(i);
					btn.setEnabled(true);
				}
			}
		});
	}

}
