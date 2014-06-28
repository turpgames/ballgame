package com.turpgames.ballgame.components.hiscore;

import com.turpgames.ballgame.utils.BallGameMode;
import com.turpgames.framework.v0.client.IServiceCallback;
import com.turpgames.framework.v0.client.TurpClient;
import com.turpgames.service.message.GetHiScoresRequest;
import com.turpgames.service.message.GetHiScoresResponse;

public class HiScoreManager {
	public static void getHiScores(final int days, final IServiceCallback<GetHiScoresResponse> callback) {
		GetHiScoresResponse resp = HiScoreCache.getHiScores(days, false);
		if (resp != null) {
			callback.onSuccess(resp);
			return;
		}
		
		TurpClient.getHiScores(days, GetHiScoresRequest.General, BallGameMode.defaultMode, true, 0, 10, new IServiceCallback<GetHiScoresResponse>() {
			@Override
			public void onSuccess(GetHiScoresResponse response) {
				HiScoreCache.putHiScores(days, response);
				callback.onSuccess(response);
			}
			
			@Override
			public void onFail(Throwable t) {
				GetHiScoresResponse resp = HiScoreCache.getHiScores(days, true);
				if (resp == null) {
					callback.onFail(t);
				} else {
					callback.onSuccess(resp);
				}
			}
		});
	}
}
