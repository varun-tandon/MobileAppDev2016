package com.hhsfbla.launch;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;


public class BrowseFundraisersFragment extends Fragment {

    private View browseFundraisersView;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        browseFundraisersView = inflater.inflate(R.layout.fragment_browse_fundraisers, container, false);

        mRecyclerView = (RecyclerView) browseFundraisersView.findViewById(R.id.browse_recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        final ArrayList<Fundraiser> fundraisers = new ArrayList<Fundraiser>();

        final StorageReference storageRef = FirebaseStorage.getInstance().getReference("/fundraisers");
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("/fundraisers");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                fundraisers.clear();
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    final Fundraiser f = child.getValue(Fundraiser.class);
                    f.setId(child.getKey());
                    storageRef.child(child.getKey() + ".jpg").getBytes(2 * 1024 * 1024).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                        @Override
                        public void onSuccess(byte[] bytes) {
                            f.setImageBitmap(BitmapFactory.decodeByteArray(bytes, 0, bytes.length));
                            fundraisers.add(f);
                            mAdapter = new RecyclerViewAdapter(fundraisers);
                            mRecyclerView.setAdapter(mAdapter);
                        }
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError firebaseError) {

            }
        });

        mAdapter = new RecyclerViewAdapter(fundraisers);
        mRecyclerView.setAdapter(mAdapter);
        return browseFundraisersView;
    }
}
