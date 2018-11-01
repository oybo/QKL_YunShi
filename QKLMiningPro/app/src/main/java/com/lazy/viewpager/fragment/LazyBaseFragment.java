package com.lazy.viewpager.fragment;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import com.qkl.online.mining.app.data.event.EventBase;
import com.qkl.online.mining.app.library.ToolBarUtils;
import com.qkl.online.mining.app.ui.view.HeaderView;
import com.qkl.online.mining.app.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Field;

import butterknife.ButterKnife;

public class LazyBaseFragment extends Fragment {

	protected LayoutInflater inflater;
	private View contentView;
	private Context context;
	private ViewGroup container;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = getActivity().getApplicationContext();
	}

	@Override
	public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		this.inflater = inflater;
		this.container = container;
		onCreateView(savedInstanceState);
		if (contentView == null) {
			return super.onCreateView(inflater, container, savedInstanceState);
		}
		EventBus.getDefault().register(this);
		return contentView;
	}

	protected void onCreateView(Bundle savedInstanceState) {

	}

	/**
	 * 来作为EventBus接收处理函数
	 *
	 */
	@Subscribe(threadMode = ThreadMode.MAIN)
	public void onEventMainThread(EventBase eventBase) {
		onEventCallback(eventBase);
	}

	protected void onEventCallback(EventBase eventBase) {

	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		contentView = null;
		container = null;
		inflater = null;
	}

	public Context getApplicationContext() {
		return context;
	}

	public void setContentView(int layoutResID) {
		setContentView((ViewGroup) inflater.inflate(layoutResID, container, false));
	}

	public void setContentView(View view) {
		contentView = view;
		ButterKnife.bind(this, contentView);
	}

	public View getContentView() {
		return contentView;
	}

	public View findViewById(int id) {
		if (contentView != null)
			return contentView.findViewById(id);
		return null;
	}

	protected String getXmlString(int stringId) {
		return getString(stringId);
	}

	protected String getXmlString(int stringId, Object... str) {
		return getString(stringId, str);
	}

	/**
	 * 重新绘制标题栏高度，解决状态栏与顶部重叠问题
	 * Sets title bar.
	 */
	protected void setTitleBar(final View titleBarView) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && titleBarView != null) {
			final ViewGroup.LayoutParams layoutParams = titleBarView.getLayoutParams();
			if (layoutParams.height == ViewGroup.LayoutParams.WRAP_CONTENT ||
					layoutParams.height == ViewGroup.LayoutParams.MATCH_PARENT) {
				titleBarView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
					@Override
					public void onGlobalLayout() {
						titleBarView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
						layoutParams.height = titleBarView.getPaddingTop() + ToolBarUtils.stateBarHeight;
						titleBarView.setPadding(titleBarView.getPaddingLeft(),
								ToolBarUtils.stateBarHeight,
								titleBarView.getPaddingRight(),
								titleBarView.getPaddingBottom());
						titleBarView.setLayoutParams(layoutParams);
					}
				});
			} else {
				layoutParams.height = titleBarView.getPaddingTop() + ToolBarUtils.stateBarHeight;
				titleBarView.setPadding(titleBarView.getPaddingLeft(),
						ToolBarUtils.stateBarHeight,
						titleBarView.getPaddingRight(),
						titleBarView.getPaddingBottom());
				titleBarView.setLayoutParams(layoutParams);
			}
		}
	}

	protected void initTopBarOnlyTitle(int layoutId, String title) {
		HeaderView headerView = (HeaderView) findViewById(layoutId);
		initTopBarOnlyTitle(headerView, title);
	}

	protected void initTopBarOnlyTitle(HeaderView headerView, String title) {
		headerView.setTitile(title);
	}

	// http://stackoverflow.com/questions/15207305/getting-the-error-java-lang-illegalstateexception-activity-has-been-destroyed
	@Override
	public void onDetach() {
		super.onDetach();
		try {
			Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
			childFragmentManager.setAccessible(true);
			childFragmentManager.set(this, null);

		} catch (NoSuchFieldException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		EventBus.getDefault().unregister(this);
	}

}
