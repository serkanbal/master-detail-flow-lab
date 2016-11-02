package ly.generalassemb.drewmahrt.shoppinglistdetailview.detail;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import ly.generalassemb.drewmahrt.shoppinglistdetailview.R;

public class DetailActivity extends AppCompatActivity{

    public static final String ITEM_ID_KEY = "itemIdKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Get ID of selected item
        int selectedItemId = getIntent().getIntExtra(ITEM_ID_KEY, -1);
        if (selectedItemId == -1) {
            finish();
        }

        //Setup the detail fragment
        DetailFragment detailFragment = DetailFragment.newInstance(selectedItemId);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.detail_fragment_container,detailFragment);
        fragmentTransaction.commit();

    }

}
