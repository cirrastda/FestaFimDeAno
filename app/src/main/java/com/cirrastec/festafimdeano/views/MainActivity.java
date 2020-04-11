package com.cirrastec.festafimdeano.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cirrastec.festafimdeano.R;
import com.cirrastec.festafimdeano.constant.FimDeAnoConstants;
import com.cirrastec.festafimdeano.data.SecurityPreferences;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    public ViewHolder mViewHolder = new ViewHolder();
    public ButtonActions mButtonActions = new ButtonActions(this);
    private static SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
    private SecurityPreferences mSecurityPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mSecurityPreferences = new SecurityPreferences(this);

        this.initElements();
        this.setDatas();

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.verifyPresence();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initElements() {
        this.mViewHolder.textToday = findViewById(R.id.text_today);
        this.mViewHolder.textDaysLeft = findViewById(R.id.text_dias_restantes);
        this.mViewHolder.buttonConfirm = findViewById(R.id.button_corfirm);

        this.mViewHolder.buttonConfirm.setOnClickListener(this.mButtonActions);
    }

    private void setDatas() {
        Date today = Calendar.getInstance().getTime();
        this.mViewHolder.textToday.setText(SIMPLE_DATE_FORMAT.format(today));

        this.mViewHolder.textDaysLeft.setText(String.format("%s %s",String.valueOf(this.getDaysLeft()),getString(R.string.dias)));
    }

    int getDaysLeft() {
        Calendar calObject = Calendar.getInstance();
        int iToday = calObject.get(calObject.DAY_OF_YEAR);
        int iMax = calObject.getActualMaximum(calObject.DAY_OF_YEAR);

        return iMax - iToday;
    }

    private void verifyPresence() {
        String presence = this.mSecurityPreferences.readString(FimDeAnoConstants.PRESENCE_KEY);
        if (presence.equals("")) {
            this.mViewHolder.buttonConfirm.setText(R.string.nao_confirmado);
        } else if(presence.equals(FimDeAnoConstants.CONFIRMATION_YES)) {
            this.mViewHolder.buttonConfirm.setText(R.string.sim);
        } else {
            this.mViewHolder.buttonConfirm.setText(R.string.nao);
        }
    }

    public class ButtonActions implements View.OnClickListener{

        private MainActivity mActivity;

        public ButtonActions(MainActivity activity) {
            this.mActivity = activity;
        }

        @Override
        public void onClick(View v) {
            if (v.getId()==R.id.button_corfirm) {
                this.buttonConfirmAction();
            }
        }

        private void buttonConfirmAction() {
            String presence = this.mActivity.mSecurityPreferences.readString(FimDeAnoConstants.PRESENCE_KEY);
            Intent intent = new Intent(this.mActivity, DetailsActivity.class);
            intent.putExtra(FimDeAnoConstants.PRESENCE_KEY, presence);
            startActivity(intent);
        }
    }

    public static class ViewHolder {
        TextView textToday;
        TextView textDaysLeft;
        Button buttonConfirm;
    }


}
