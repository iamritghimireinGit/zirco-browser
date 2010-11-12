/*
 * Zirco Browser for Android
 * 
 * Copyright (C) 2010 J. Devauchelle and contributors.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * version 3 as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 */

package org.zirco.model;

import org.zirco.R;
import org.zirco.utils.ApplicationUtils;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

/**
 * Adapter for suggestions.
 */
public class UrlSuggestionCursorAdapter extends SimpleCursorAdapter {

	public static final String URL_SUGGESTION_ID = "_id";
	public static final String URL_SUGGESTION_TITLE = "URL_SUGGESTION_TITLE";
	public static final String URL_SUGGESTION_URL = "URL_SUGGESTION_URL";
	public static final String URL_SUGGESTION_TYPE = "URL_SUGGESTION_TYPE";
	
	/**
	 * Constructor.
	 * @param context The context.
	 * @param layout The layout.
	 * @param c The Cursor. 
	 * @param from Input array.
	 * @param to Output array.
	 */
	public UrlSuggestionCursorAdapter(Context context, int layout, Cursor c, String[] from, int[] to) {		
		super(context, layout, c, from, to);		
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View superView = super.getView(position, convertView, parent);
		
		TextView titleView = (TextView) superView.findViewById(R.id.AutocompleteTitle);
		TextView urlView = (TextView) superView.findViewById(R.id.AutocompleteUrl);
		ImageView iconView = (ImageView) superView.findViewById(R.id.AutocompleteImageView);
		
		String title = titleView.getText().toString();		
		title = ApplicationUtils.getTruncatedString(titleView.getPaint(), title, parent.getMeasuredWidth() - 75);		
		titleView.setText(title);
		
		String url = urlView.getText().toString();		
		url = ApplicationUtils.getTruncatedString(urlView.getPaint(), url, parent.getMeasuredWidth() - 75);		
		urlView.setText(url);

		int resultType;
		try {			
			resultType = Integer.parseInt(getCursor().getString(getCursor().getColumnIndex(URL_SUGGESTION_TYPE)));
		} catch (Exception e) {
			resultType = 0;
		}
		
		switch (resultType) {
		case 1: iconView.setImageResource(R.drawable.ic_tab_history_unselected); break;
		case 2: iconView.setImageResource(R.drawable.ic_tab_bookmarks_unselected); break;
		default: break;
		}
		
		return superView;
	}
	
	

}
