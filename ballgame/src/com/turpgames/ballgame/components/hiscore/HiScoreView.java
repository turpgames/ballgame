package com.turpgames.ballgame.components.hiscore;

import com.turpgames.framework.v0.IView;
import com.turpgames.framework.v0.impl.Text;
import com.turpgames.service.message.GetHiScoresResponse;

public class HiScoreView implements IView {

	private final String id;
	private final Text title;
	private final HiScoreTable table;

	public HiScoreView(String titleText) {
		title = new Text();
		title.setAlignment(Text.HAlignCenter, Text.VAlignTop);
		title.setPadY(200f);
		title.setFontScale(0.5f);
		title.setText(titleText);

		id = titleText;
		
		table = new HiScoreTable();
	}
	
	public void bindData(GetHiScoresResponse data) {
		table.bindData(data);
	}

	@Override
	public void draw() {
		title.draw();
		table.draw();
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void activate() {

	}

	@Override
	public boolean deactivate() {
		return true;
	}
}
