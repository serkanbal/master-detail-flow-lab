package ly.generalassemb.drewmahrt.shoppinglistdetailview.list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ly.generalassemb.drewmahrt.shoppinglistdetailview.data.ShoppingItem;

/**
 * Created by drewmahrt on 10/21/16.
 */

public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingItemViewHolder> {
    private List<ShoppingItem> mShoppingItems;
    private OnItemSelectedListener mOnItemSelectedListener;

    public interface OnItemSelectedListener {
        void onItemSelected(int itemId);
    }

    public ShoppingListAdapter(List<ShoppingItem> shoppingItems, OnItemSelectedListener listener) {
        mShoppingItems = shoppingItems;
        mOnItemSelectedListener = listener;
    }

    @Override
    public ShoppingItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ShoppingItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1,parent,false));
    }

    @Override
    public void onBindViewHolder(final ShoppingItemViewHolder holder, final int position) {

        final ShoppingItem currentItem = mShoppingItems.get(position);

        holder.mNameTextView.setText(currentItem.getName());

        // Add an OnClickListener that launches DetailActivity and passes it the item's ID
        holder.mNameTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemSelectedListener.onItemSelected(currentItem.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mShoppingItems.size();
    }
}
