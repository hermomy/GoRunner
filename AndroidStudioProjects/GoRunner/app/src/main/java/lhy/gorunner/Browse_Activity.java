package lhy.gorunner;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Browse_Activity extends AppCompatActivity {

    private MenuItem mSearchAction;
    private boolean isSearchOpened = false;
    private EditText edtSeach;
    RecyclerView recyclerView;
    int mutedColor = R.attr.colorPrimary;
    CollapsingToolbarLayout collapsingToolbar;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.browse_task);
        Toolbar toolbar = (Toolbar) findViewById(R.id.anim_toolbar);
        setSupportActionBar(toolbar);

        int imageview_url = getIntent().getIntExtra("EXTRA_IMAGEVIEW_URL",0);
        image = (ImageView) findViewById(R.id.header);
        if (imageview_url == 0){

            image.setImageResource(R.drawable.test);
        }
        else if (imageview_url == 1){
            image.setImageResource(R.drawable.test2);
        }
        else if (imageview_url == 2){
            image.setImageResource(R.drawable.test3);
        }
        else if (imageview_url == 3){
            image.setImageResource(R.drawable.test4);
        }
        else if (imageview_url == 4){
            image.setImageResource(R.drawable.test5_1);
        }

        recyclerView= (RecyclerView) findViewById(R.id.my_recycler_view2);

        RecyclerAdapter2 adapter=new RecyclerAdapter2(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);

        //Layout manager for Recycler view

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
            ImageView header = (ImageView) findViewById(R.id.header);
        collapsingToolbar.setContentScrimColor(getResources().getColor(R.color.primary));

//
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
//                R.drawable.test5_1);
//
//        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
//            @SuppressWarnings("ResourceType")
//            @Override
//            public void onGenerated(Palette palette) {
//
//                mutedColor = palette.getMutedColor(R.color.primary);
//                collapsingToolbar.setContentScrimColor(mutedColor);
//                collapsingToolbar.setStatusBarScrimColor(R.color.black);
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu_search, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.action_home){
            Intent intent = new Intent(this, MainActivity.class);
            finish();
            startActivity(intent);

    }

        if (id == R.id.action_search){
            handleMenuSearch();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        mSearchAction = menu.findItem(R.id.action_search);
        return super.onPrepareOptionsMenu(menu);
    }

    protected void handleMenuSearch(){
        ActionBar action = getSupportActionBar(); //get the actionbar

        if(isSearchOpened){ //test if the search is open

            action.setDisplayShowCustomEnabled(false); //disable a custom view inside the actionbar
            action.setDisplayShowTitleEnabled(true); //show the title in the action bar

            //hides the keyboard
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(edtSeach.getWindowToken(), 0);

            //add the search icon in the action bar
            mSearchAction.setIcon(getResources().getDrawable(R.mipmap.ic_search));

            isSearchOpened = false;
        } else { //open the search entry

            action.setDisplayShowCustomEnabled(true); //enable it to display a
            // custom view in the action bar.
            action.setCustomView(R.layout.search_bar);//add the custom view
            action.setDisplayShowTitleEnabled(false); //hide the title

            edtSeach = (EditText)action.getCustomView().findViewById(R.id.edtSearch); //the text editor

            //this is a listener to do a search when the user clicks on search button
            edtSeach.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                        doSearch();
                        return true;
                    }
                    return false;
                }
            });


            edtSeach.requestFocus();

            //open the keyboard focused in the edtSearch
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(edtSeach, InputMethodManager.SHOW_IMPLICIT);


            //add the close icon
            mSearchAction.setIcon(getResources().getDrawable(R.mipmap.ic_clear));

            isSearchOpened = true;
        }
    }

    @Override
    public void onBackPressed() {
        if(isSearchOpened) {
            handleMenuSearch();
            return;
        }
        super.onBackPressed();
    }

    private void doSearch() {
//
    }
}
