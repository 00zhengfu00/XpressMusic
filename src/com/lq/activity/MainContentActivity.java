package com.lq.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.view.View;

import com.lq.fragment.ColorFragment;
import com.lq.fragment.MenuFragment;
import com.slidingmenu.lib.SlidingMenu;

public class MainContentActivity extends FragmentActivity {
	SlidingMenu mSlidingMenu = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_content);

		initSlidingMenu();
		initPopulateFragment();

		getActionBar().setDisplayHomeAsUpEnabled(true);
		View info_frame = findViewById(R.id.bottom_info_frame);
		info_frame.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainContentActivity.this,
						MusicPlayerActivity.class));
			}
		});
	}

	private void initSlidingMenu() {
		// TODO ��ʼ��SlidingMenu
		mSlidingMenu = new SlidingMenu(this);
		// 1.����SlidingMenu������Activity
		mSlidingMenu.attachToActivity(this, SlidingMenu.SLIDING_WINDOW);
		// 2.����SlidingMenu�Ĳ���(ָ��һ��FragmentLayout���Ժ��ٽ����滻ΪFragment)
		mSlidingMenu.setMenu(R.layout.layout_menu);
		// 3.����SlidingMenu�Ժ��ַ�ʽ�ϳ�(ȫ�����϶�����Ե���϶��������϶�)
		mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		// 4.����SlidingMenu����Ļ���ıߵ���
		mSlidingMenu.setMode(SlidingMenu.LEFT);
		// 5.��������SlidingMenu����
		mSlidingMenu.setShadowWidthRes(R.dimen.shadow_width);
		mSlidingMenu.setShadowDrawable(R.drawable.shadow);
		mSlidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		mSlidingMenu.setFadeDegree(0.35f);
	}

	private void initPopulateFragment() {
		// TODO ΪMenu��Contentָ��Fragment
		FragmentTransaction fragmentTransaction = getSupportFragmentManager()
				.beginTransaction();
		fragmentTransaction.replace(R.id.frame_menu, new MenuFragment());
		fragmentTransaction.replace(R.id.frame_content, new ColorFragment(
				android.R.color.background_light));
		fragmentTransaction.commit();

	}

	public SlidingMenu getSlidingMenu() {
		return mSlidingMenu;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			mSlidingMenu.toggle();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void switchContent(Fragment fragment) {
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.frame_content, fragment).addToBackStack(null)
				.commit();
		getSlidingMenu().showContent();
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		if (mSlidingMenu.isShown()) {
			mSlidingMenu.showContent();
		}
	}
}
