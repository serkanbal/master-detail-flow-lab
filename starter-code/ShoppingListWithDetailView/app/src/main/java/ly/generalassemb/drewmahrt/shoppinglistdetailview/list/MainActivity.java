package ly.generalassemb.drewmahrt.shoppinglistdetailview.list;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import ly.generalassemb.drewmahrt.shoppinglistdetailview.R;
import ly.generalassemb.drewmahrt.shoppinglistdetailview.data.ShoppingItem;
import ly.generalassemb.drewmahrt.shoppinglistdetailview.data.ShoppingSQLiteOpenHelper;
import ly.generalassemb.drewmahrt.shoppinglistdetailview.detail.DetailActivity;
import ly.generalassemb.drewmahrt.shoppinglistdetailview.detail.DetailFragment;
import ly.generalassemb.drewmahrt.shoppinglistdetailview.setup.DBAssetHelper;

public class MainActivity extends AppCompatActivity implements ShoppingListAdapter.OnItemSelectedListener {
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * WE FIND OUT WHICH XML ARE WE USING HERE!
         */
        //TODO - check if two pane or not
        if (findViewById(R.id.detail_fragment_container) != null) {
            mTwoPane = true;
        } else {
            mTwoPane = false;
        }



        //Ignore the two lines below, they are for setup
        DBAssetHelper dbSetup = new DBAssetHelper(MainActivity.this);
        dbSetup.getReadableDatabase();

        //Setup the RecyclerView
        RecyclerView shoppingListRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        ShoppingSQLiteOpenHelper db = ShoppingSQLiteOpenHelper.getInstance(this);
        List<ShoppingItem> shoppingList = db.getShoppingList();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);

        shoppingListRecyclerView.setLayoutManager(linearLayoutManager);
        shoppingListRecyclerView.setAdapter(new ShoppingListAdapter(shoppingList, this));

    }

    @Override
    public void onItemSelected(int itemId) {
        if (mTwoPane) {
            //Setup the detail fragment
            DetailFragment detailFragment = DetailFragment.newInstance(itemId);
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.detail_fragment_container,detailFragment);
            fragmentTransaction.commit();
        } else {
            // Create the intent
            Intent intent = new Intent(this, DetailActivity.class);
            // Add the ID as an extra
            intent.putExtra(DetailActivity.ITEM_ID_KEY, itemId);
            // Start the detail activity
            startActivity(intent);
        }
    }
}


