package com.ultrainstinct.android.visapay.AdvancedRecyclerViews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.h6ah4i.android.widget.advrecyclerview.expandable.RecyclerViewExpandableItemManager;
import com.ultrainstinct.android.visapay.Fragments.ExpandableDraggableSwipeableExampleChangeBarcodeFragment;
import com.ultrainstinct.android.visapay.Fragments.ExpandableDraggableSwipeableExampleSalesFragment;
import com.ultrainstinct.android.visapay.Models.Barcode;
import com.ultrainstinct.android.visapay.R;
import com.ultrainstinct.android.visapay.common.data.AbstractExpandableDataProvider;
import com.ultrainstinct.android.visapay.common.data.ExampleExpandableChangeBarcodeDataProvider;
import com.ultrainstinct.android.visapay.common.data.ExampleExpandableDataProvider;
import com.ultrainstinct.android.visapay.common.data.ExampleExpandableSalesDataProvider;
import com.ultrainstinct.android.visapay.common.fragment.ExampleExpandableChangeBarcodeDataProviderFragment;
import com.ultrainstinct.android.visapay.common.fragment.ExampleExpandableDataProviderFragment;
import com.ultrainstinct.android.visapay.common.fragment.ExampleExpandableSalesDataProviderFragment;
import com.ultrainstinct.android.visapay.common.fragment.ExpandableItemPinnedMessageDialogFragment;

import java.util.ArrayList;

public class ExpandableDraggableSwipeableExampleChangeBarcodeActivity extends AppCompatActivity implements ExpandableItemPinnedMessageDialogFragment.EventListener {
    private static final String FRAGMENT_TAG_DATA_PROVIDER = "data provider";
    private static final String FRAGMENT_LIST_VIEW = "list view";
    private static final String FRAGMENT_TAG_ITEM_PINNED_DIALOG = "item pinned dialog";
    ArrayList<Integer> itemsRemoved = new ArrayList<Integer>();
    Barcode lastDeleted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(new ExampleExpandableChangeBarcodeDataProviderFragment(), FRAGMENT_TAG_DATA_PROVIDER)
                    .commit();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new ExpandableDraggableSwipeableExampleChangeBarcodeFragment(), FRAGMENT_LIST_VIEW)
                    .commit();
        }
    }

    /**
     * This method will be called when a group item is removed
     *
     * @param groupPosition The position of the group item within data set
     */
    public void onGroupItemRemoved(int groupPosition) {
        Snackbar snackbar = Snackbar.make(
                findViewById(R.id.container),
                R.string.snack_bar_text_group_item_removed,
                Snackbar.LENGTH_LONG);

//        itemsRemoved.add(groupPosition);

        lastDeleted = ExampleExpandableChangeBarcodeDataProvider.changeBarcodeContents.get(groupPosition);
        snackbar.setAction(R.string.snack_bar_action_undo, v -> onItemUndoActionClicked());
        snackbar.setActionTextColor(ContextCompat.getColor(this, R.color.snackbar_action_color_done));
        snackbar.show();

        DatabaseReference mChangeBarcode = FirebaseDatabase.getInstance().getReference("Barcode");
        DatabaseReference databaseReference = mChangeBarcode.child(ExampleExpandableChangeBarcodeDataProvider.changeBarcodeContents.get(groupPosition).getKey());
        ExampleExpandableChangeBarcodeDataProvider.changeBarcodeContents.remove(groupPosition);
        databaseReference.removeValue();
    }

    /**
     * This method will be called when a child item is removed
     *
     * @param groupPosition The group position of the child item within data set
     * @param childPosition The position of the child item within the group
     */
    public void onChildItemRemoved(int groupPosition, int childPosition) {
        Snackbar snackbar = Snackbar.make(
                findViewById(R.id.container),
                R.string.snack_bar_text_child_item_removed,
                Snackbar.LENGTH_LONG);

        snackbar.setAction(R.string.snack_bar_action_undo, v -> onItemUndoActionClicked());
        snackbar.setActionTextColor(ContextCompat.getColor(this, R.color.snackbar_action_color_done));
        snackbar.show();
    }

    /**
     * This method will be called when a group item is pinned
     *
     * @param groupPosition The position of the group item within data set
     */
    public void onGroupItemPinned(int groupPosition) {
        final DialogFragment dialog = ExpandableItemPinnedMessageDialogFragment.newInstance(groupPosition, RecyclerView.NO_POSITION);

        getSupportFragmentManager()
                .beginTransaction()
                .add(dialog, FRAGMENT_TAG_ITEM_PINNED_DIALOG)
                .commit();
    }

    /**
     * This method will be called when a child item is pinned
     *
     * @param groupPosition The group position of the child item within data set
     * @param childPosition The position of the child item within the group
     */
    public void onChildItemPinned(int groupPosition, int childPosition) {
        final DialogFragment dialog = ExpandableItemPinnedMessageDialogFragment.newInstance(groupPosition, childPosition);

        getSupportFragmentManager()
                .beginTransaction()
                .add(dialog, FRAGMENT_TAG_ITEM_PINNED_DIALOG)
                .commit();
    }

    public void onGroupItemClicked(int groupPosition) {
        final Fragment fragment = getSupportFragmentManager().findFragmentByTag(FRAGMENT_LIST_VIEW);
        AbstractExpandableDataProvider.GroupData data = getDataProvider().getGroupItem(groupPosition);

        if (data.isPinned()) {
            // unpin if tapped the pinned item
            data.setPinned(false);
            ((ExpandableDraggableSwipeableExampleChangeBarcodeFragment) fragment).notifyGroupItemChanged(groupPosition);
        }
    }

    public void onChildItemClicked(int groupPosition, int childPosition) {
        final Fragment fragment = getSupportFragmentManager().findFragmentByTag(FRAGMENT_LIST_VIEW);
        AbstractExpandableDataProvider.ChildData data = getDataProvider().getChildItem(groupPosition, childPosition);

        if (data.isPinned()) {
            // unpin if tapped the pinned item
            data.setPinned(false);
            ((ExpandableDraggableSwipeableExampleChangeBarcodeFragment) fragment).notifyChildItemChanged(groupPosition, childPosition);
        }
    }

    private void onItemUndoActionClicked() {
        final Fragment fragment = getSupportFragmentManager().findFragmentByTag(FRAGMENT_LIST_VIEW);
        final long result = getDataProvider().undoLastRemoval();

        if (result == RecyclerViewExpandableItemManager.NO_EXPANDABLE_POSITION) {
            return;
        }

        final int groupPosition = RecyclerViewExpandableItemManager.getPackedPositionGroup(result);
        final int childPosition = RecyclerViewExpandableItemManager.getPackedPositionChild(result);

        if (childPosition == RecyclerView.NO_POSITION) {
            // group item
//            itemsRemoved.remove(groupPosition);

            ExampleExpandableChangeBarcodeDataProvider.changeBarcodeContents.add(groupPosition,lastDeleted);
            DatabaseReference mRef = FirebaseDatabase.getInstance().getReference("Barcode");
            mRef.child(lastDeleted.getKey()).setValue(lastDeleted);

            ((ExpandableDraggableSwipeableExampleChangeBarcodeFragment) fragment).notifyGroupItemRestored(groupPosition);
        } else {
            // child item
            ((ExpandableDraggableSwipeableExampleChangeBarcodeFragment) fragment).notifyChildItemRestored(groupPosition, childPosition);
        }
    }

    // implements ExpandableItemPinnedMessageDialogFragment.EventListener
    @Override
    public void onNotifyExpandableItemPinnedDialogDismissed(int groupPosition, int childPosition, boolean ok) {
        final Fragment fragment = getSupportFragmentManager().findFragmentByTag(FRAGMENT_LIST_VIEW);

        if (childPosition == RecyclerView.NO_POSITION) {
            // group item
            getDataProvider().getGroupItem(groupPosition).setPinned(ok);
            ((ExpandableDraggableSwipeableExampleChangeBarcodeFragment) fragment).notifyGroupItemChanged(groupPosition);
        } else {
            // child item
            getDataProvider().getChildItem(groupPosition, childPosition).setPinned(ok);
            ((ExpandableDraggableSwipeableExampleChangeBarcodeFragment) fragment).notifyChildItemChanged(groupPosition, childPosition);
        }
    }

    public AbstractExpandableDataProvider getDataProvider() {
        final Fragment fragment = getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG_DATA_PROVIDER);
        return ((ExampleExpandableChangeBarcodeDataProviderFragment) fragment).getDataProvider();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

//        StringBuilder t = new StringBuilder();
//        t.append("ITEMS TO BE DELETED");
//
//        for(int c : itemsRemoved){
//            t.append('\n');
//            t.append(ExampleExpandableChangeBarcodeDataProvider.changeBarcodeContents.get(c).getCode());
//        }
//        Toast.makeText(this,t.toString(), Toast.LENGTH_SHORT).show();
    }

}
