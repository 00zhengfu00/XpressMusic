package com.lq.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lq.activity.R;
import com.lq.util.GlobalConstant;

public class FrameLocalMusicFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.frame_for_nested_fragment,
				container, false);
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		Bundle args = new Bundle();
		args.putInt(GlobalConstant.PARENT, GlobalConstant.START_FROM_LOCAL_MUSIC);

		getChildFragmentManager()
				.beginTransaction()
				.replace(
						R.id.frame_for_nested_fragment,
						Fragment.instantiate(getActivity(),
								TrackBrowserFragment.class.getName(), args))
				.commit();
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {

	}
}