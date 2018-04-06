package com.epicodus.androidapp.adapters;


import android.content.Context;
import android.content.Intent;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.View;

import com.epicodus.androidapp.models.Forecast;
import com.epicodus.androidapp.ui.LocalDetailActivity;
import com.epicodus.androidapp.util.ItemTouchHelperAdapter;
import com.epicodus.androidapp.util.OnStartDragListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Collections;

public class FirebaseLocalListAdapter extends FirebaseRecyclerAdapter<Forecast, FirebaseViewHolder> implements ItemTouchHelperAdapter {

    private DatabaseReference mRef;
    private OnStartDragListener mOnStartDragListener;
    private Context mContext;
    private ChildEventListener mChildEventListener;
    private ArrayList<Forecast> mForecasts = new ArrayList<>();


    public FirebaseLocalListAdapter(Class<Forecast> modelClass, int modelLayout,
                                         Class<FirebaseViewHolder> viewHolderClass,
                                         Query ref, OnStartDragListener onStartDragListener, Context context) {

        super(modelClass, modelLayout, viewHolderClass, ref);
        mRef = ref.getRef();
        mOnStartDragListener = onStartDragListener;
        mContext = context;

        mChildEventListener = mRef.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                mForecasts.add(dataSnapshot.getValue(Forecast.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void populateViewHolder(final FirebaseViewHolder viewHolder, Forecast model, int position) {
        viewHolder.bindForecast(model);

        viewHolder.mDate.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mOnStartDragListener.onStartDrag(viewHolder);
                }
                return false;
            }

        });

//        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(mContext, LocalDetailActivity.class);
//                intent.putExtra("position", viewHolder.getAdapterPosition());
//                intent.putExtra("restaurants", Parcels.wrap(mForecasts));
//                mContext.startActivity(intent);
//            }
//        });
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mForecasts, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return false;
    }

    @Override
    public void onItemDismiss(int position) {
        mForecasts.remove(position);
        getRef(position).removeValue();
    }

    private void setIndexInFirebase() {
        for (Forecast forecast : mForecasts) {
            int index = mForecasts.indexOf(forecast);
            DatabaseReference ref = getRef(index);
            forecast.setIndex(Integer.toString(index));
            ref.setValue(forecast);
        }
    }

    @Override
    public void cleanup() {
        super.cleanup();
        setIndexInFirebase();
        mRef.removeEventListener(mChildEventListener);
    }
}