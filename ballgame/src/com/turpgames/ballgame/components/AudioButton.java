package com.turpgames.ballgame.components;

import com.turpgames.ballgame.utils.StatActions;
import com.turpgames.framework.v0.client.TurpClient;
import com.turpgames.framework.v0.component.ToggleButton;
import com.turpgames.framework.v0.impl.Settings;
import com.turpgames.framework.v0.util.Color;

public class AudioButton extends ToggleButton {
	public AudioButton() {
		super(Toolbar.menuButtonSizeToScreen, Toolbar.menuButtonSizeToScreen,
				"sound", "tb_sound_on", "tb_sound_off", Color.white(), Color.white());
	}

	@Override
	protected boolean onTap() {
		super.onTap();
		Settings.putBoolean("music", isOn);

		TurpClient.sendStat(isOn ? StatActions.SoundOn : StatActions.SoundOff);

		return true;
	}
}
