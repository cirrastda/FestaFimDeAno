package com.cirrastec.festafimdeano.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import com.cirrastec.festafimdeano.R;
import com.cirrastec.festafimdeano.constant.FimDeAnoConstants;
import com.cirrastec.festafimdeano.data.SecurityPreferences;

public class DetailsActivity extends AppCompatActivity {

    public ViewHolder mViewHolder = new ViewHolder();
    public ClickListener mClickListener = new ClickListener(this);
    private SecurityPreferences mSecurityPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        this.mSecurityPreferences = new SecurityPreferences(this);

        this.mViewHolder.checkParticipate = findViewById(R.id.check_participar);
        this.mViewHolder.checkParticipate.setOnClickListener(this.mClickListener);
        this.loadDataFromActivity();
    }

    private void checkParticipate() {
        if (this.mViewHolder.checkParticipate.isChecked()) {
            this.mSecurityPreferences.storeString(FimDeAnoConstants.PRESENCE_KEY,FimDeAnoConstants.CONFIRMATION_YES);
        } else {
            this.mSecurityPreferences.storeString(FimDeAnoConstants.PRESENCE_KEY,FimDeAnoConstants.CONFIRMATION_NO);
        }
    }

    private void loadDataFromActivity() {
        Bundle params = getIntent().getExtras();
        if (params != null) {
            String presence = params.getString(FimDeAnoConstants.PRESENCE_KEY);
            this.loadParticipate(presence);
        }
    }
    private void loadParticipate(String presence) {
        //String presence = this.mSecurityPreferences.readString(FimDeAnoConstants.PRESENCE_KEY);
        if(presence != null && presence.equals(FimDeAnoConstants.CONFIRMATION_YES)) {
            this.mViewHolder.checkParticipate.setChecked(true);
        } else {
            this.mViewHolder.checkParticipate.setChecked(false);
        }
    }

    public class ClickListener implements View.OnClickListener{

        private DetailsActivity mActivity;

        public ClickListener(DetailsActivity activity) {
            this.mActivity = activity;
        }

        @Override
        public void onClick(View v) {
            if (v.getId()==R.id.check_participar) {
                this.mActivity.checkParticipate();
            }
        }

    }

    public static class ViewHolder {
        CheckBox checkParticipate;
    }

}
