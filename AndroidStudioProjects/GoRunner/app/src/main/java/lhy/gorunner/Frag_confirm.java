package lhy.gorunner;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * A simple {@link Fragment} subclass.
 */
public class Frag_confirm extends Fragment{
    Button myButton;
    TextView textView1,textView2,textView3;
    String details,title,address;
    public Frag_confirm() {
        // Required empty public constructor
    }

    public Frag_confirm(String details,String title,String address){
        this.details = details;
        this.title = title;
        this.address = address;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_frag_confirm, container, false);
//        myButton = (Button) myView.findViewById(R.id.button3);
//        myButton.setOnClickListener(this);

        textView1 = (TextView) myView.findViewById(R.id.textView1);
        textView2 = (TextView) myView.findViewById(R.id.textView2);
        textView3 = (TextView) myView.findViewById(R.id.textView3);

        textView1.setText(title);
        textView2.setText(details);
        textView3.setText(address);
        // Inflate the layout for this fragment
        return myView;

    }

//    @Override
//    public void onClick(View v) {
//        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
//        progressDialog.setIndeterminate(true);
//        progressDialog.setMessage("Posting task...");
//        progressDialog.show();
//       // final Intent intent = new Intent(getActivity(), Browse_Activity.class);
//        new android.os.Handler().postDelayed(
//                new Runnable() {
//                    public void run() {
//                        // On complete call either onLoginSuccess or onLoginFailed
//                        // onLoginFailed();
//
//                        //startActivity(intent);
//                        progressDialog.dismiss();
//                    }
//                }, 2000);
//    }


}
