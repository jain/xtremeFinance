package com.orange.xtreme.presenters;

import android.content.Context;
import android.view.View;

public interface ReportsView {
	void onClickGenerate(View v);
	void onClickReturn(View v);
	Context getBaseContext();
}
