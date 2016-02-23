package lhy.gorunner;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by HAUYANG on 23/02/2016.
 */
public class Frag_payment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_frag_payment, container, false);

        return myView;
    }

//    @Override
//    public void onClick(View v) {
//        final ProgressDialog progressDialog = new ProgressDialog(getActivity(),
//                R.style.AppTheme_Dark_Dialog);
//        progressDialog.setIndeterminate(true);
//        progressDialog.setMessage("Adding payment to myPay...");
//        progressDialog.show();
//
//        new android.os.Handler().postDelayed(
//                new Runnable() {
//                    public void run() {
//                        // On complete call either onLoginSuccess or onLoginFailed
//                        // onLoginFailed();
//
//                        progressDialog.dismiss();
//                    }
//                }, 3000);
//    }
}
