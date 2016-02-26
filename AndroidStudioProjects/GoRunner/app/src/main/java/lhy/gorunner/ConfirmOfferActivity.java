package lhy.gorunner;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.InjectView;
import me.drakeet.materialdialog.MaterialDialog;

public class ConfirmOfferActivity extends AppCompatActivity {

    @InjectView(R.id.submit_offer_btn) Button _submitOfferButton;
    @InjectView(R.id.cancel_submit_offer_btn) Button _cancelsubmitOfferButton;
    MaterialDialog mMaterialDialog = new MaterialDialog(this);;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_offer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        ButterKnife.inject(this);

        _submitOfferButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final ProgressDialog progressDialog = new ProgressDialog(ConfirmOfferActivity.this,
                        R.style.AppTheme_Dark_Dialog);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Verifying account...");
                progressDialog.show();

                new android.os.Handler().postDelayed(
                        new Runnable() {
                            public void run() {
                                // On complete call either onLoginSuccess or onLoginFailed
                                // onLoginFailed();
                                Intent intent = new Intent(getApplicationContext(), MyTaskActivity.class);
                                startActivity(intent);
                                progressDialog.dismiss();
                            }
                        }, 3000);
            }


        });

        _cancelsubmitOfferButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mMaterialDialog.setTitle("Logout");
                mMaterialDialog.setMessage("Are you sure want to cancel offer?");
                mMaterialDialog.setPositiveButton("YES", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(ConfirmOfferActivity.this, Browse_Activity.class);
                        intent.putExtra("finish", true); // if you are checking for this in your other Activities
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();

                    }
                });
                mMaterialDialog.setNegativeButton("CANCEL",new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mMaterialDialog.dismiss();

                    }
                });
                mMaterialDialog.show();

            }

        });
    }

}
