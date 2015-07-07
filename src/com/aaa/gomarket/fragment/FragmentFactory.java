package com.aaa.gomarket.fragment;

import java.util.HashMap;
import java.util.Map;

public class FragmentFactory {
	// 存储全部fragment
	private static Map<Integer, BaseFragment> fs = new HashMap<Integer, BaseFragment>();

	public static BaseFragment createFragment(int position) {
		BaseFragment fragment = fs.get(position);
		if (fragment != null) {
			return fragment;
		} else {
			switch (position) {
			case 0:
				fragment = new HomeFragment();
				break;
			case 1:
				fragment = new AppFragment();
				break;
			case 2:
				fragment = new SubjectFragment();
				break;
			case 3:
				fragment = new RecommondFragment();
				break;

			default:
				break;
			}
			fs.put(position, fragment);
		}
	
		return fragment;
	}
}
