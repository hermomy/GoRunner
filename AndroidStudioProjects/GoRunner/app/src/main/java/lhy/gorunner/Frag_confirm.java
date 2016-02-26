package lhy.gorunner;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * A simple {@link Fragment} subclass.
 */
public class Frag_confirm extends Fragment implements View.OnClickListener {
    Button myButton;
    public Frag_confirm() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_frag_confirm, container, false);
        myButton = (Button) myView.findViewById(R.id.button3);
        myButton.setOnClickListener(this);
        // Inflate the layout for this fragment
        return myView;

    }

    @Override
    public void onClick(View v) {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity(),
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Posting task...");
        progressDialog.show();

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        // onLoginFailed();
                        Intent intent = new Intent(getContext(), Browse_Activity.class);
                        startActivity(intent);
                        progressDialog.dismiss();
                    }
                }, 3000);
    }


}
