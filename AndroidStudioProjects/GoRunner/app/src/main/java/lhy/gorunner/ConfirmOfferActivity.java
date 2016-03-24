package lhy.gorunner;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import me.drakeet.materialdialog.MaterialDialog;

public class ConfirmOfferActivity extends AppCompatActivity {

    @InjectView(R.id.submit_offer_btn) Button _submitOfferButton;
    @InjectView(R.id.cancel_submit_offer_btn) Button _cancelsubmitOfferButton;
    MaterialDialog mMaterialDialog = new MaterialDialog(this);;
    String user_id;
    String price,receive,title,tempID,comment;
    LoginDataBaseAdapter loginDataBaseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_offer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        ButterKnife.inject(this);

        loginDataBaseAdapter=new LoginDataBaseAdapter(this);

        loginDataBaseAdapter=loginDataBaseAdapter.open();
        price = getIntent().getExtras().getString("price");
        title = getIntent().getExtras().getString("title");
        receive = getIntent().getExtras().getString("receive");
        tempID = getIntent().getExtras().getString("task_user_id");
        user_id = getIntent().getExtras().getString("userID");
        comment = getIntent().getExtras().getString("task_comment");

        String task_user_pic = loginDataBaseAdapter.getUserPic(tempID);
        String user_pic = loginDataBaseAdapter.getUserPic(user_id);
        TextView confirm_title = (TextView)findViewById(R.id.confirm_title);
        TextView confirm_price = (TextView)findViewById(R.id.confirm_price);
        TextView confirm_receive = (TextView)findViewById(R.id.confirm_receive);
        de.hdodenhof.circleimageview.CircleImageView profile_img1 = (de.hdodenhof.circleimageview.CircleImageView)findViewById(R.id.profile_image1);
        de.hdodenhof.circleimageview.CircleImageView profile_img2 = (de.hdodenhof.circleimageview.CircleImageView)findViewById(R.id.profile_image2);
        int id1 = getResources().getIdentifier(user_pic , "drawable", getPackageName());
        int id2 = getResources().getIdentifier(task_user_pic , "drawable",getPackageName());

        profile_img1.setImageResource(id1);
        profile_img2.setImageResource(id2);
        confirm_title.setText(title);
        confirm_price.setText("RM " + price);
        confirm_receive.setText(receive);

        _submitOfferButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final ProgressDialog progressDialog = new ProgressDialog(ConfirmOfferActivity.this);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Verifying account...");
                progressDialog.show();

                new android.os.Handler().postDelayed(
                        new Runnable() {
                            public void run() {
                                // On complete call either onLoginSuccess or onLoginFailed
                                // onLoginFailed();
                                loginDataBaseAdapter.insertNewOffer(user_id,tempID,comment);
                                progressDialog.setMessage("Your offer has been send");
                                Intent intent = new Intent(getApplicationContext(), MyTaskActivity.class);
                                intent.putExtra("userID", user_id);
                                startActivity(intent);
                                progressDialog.dismiss();
                            }
                        }, 3000);
            }


        });

        _cancelsubmitOfferButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mMaterialDialog.setTitle("Cancel");
                mMaterialDialog.setMessage("Are you sure want to cancel offer?");
                mMaterialDialog.setPositiveButton("YES", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(ConfirmOfferActivity.this, Browse_Activity.class);
                        intent.putExtra("finish", true); // if you are checking for this in your other Activities
                        intent.putExtra("userID",user_id);
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
