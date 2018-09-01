package com.example.tecomca.mylogin_seccion05.Fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tecomca.mylogin_seccion05.R;
import com.example.tecomca.mylogin_seccion05.Utils.Util;
//import com.google.firebase.database.ChildEventListener;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class InforFragment extends Fragment {

    private SharedPreferences prefs;

    //private DatabaseReference mDatabase;
    private TextView textView;

//    private ArrayList<String> mUsername = new ArrayList<>();
//    private ArrayList<String> mKeys = new ArrayList<>();

    public InforFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_infor, container, false);
        prefs = getContext().getSharedPreferences("Preferences", MODE_PRIVATE);
        //mDatabase = FirebaseDatabase.getInstance().getReference().child("List");
        textView = (TextView) view.findViewById(R.id.textViewSql);
//        String nameFromIntent = getActivity().getIntent().getStringExtra("EMAIL");
        textView.setText("Welcome ".concat(Util.getSessionName(prefs)));

    //        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1, mUsername);
//
//        mUserList.setAdapter(arrayAdapter);
//
//        // documentacion writr list data
//        mDatabase.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                String value = dataSnapshot.getValue(String.class);
//                mUsername.add(value);
//
//                String key = dataSnapshot.getKey();
//                mKeys.add(key);
//
//                arrayAdapter.notifyDataSetChanged();
//
//            }
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//                String value = dataSnapshot.getValue(String.class);
//                String key = dataSnapshot.getKey();
//
//                int index = mKeys.indexOf(key);
//
//                mUsername.set(index, value);
//
//                arrayAdapter.notifyDataSetChanged();
//
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

        // Inflate the layout for this fragment
        return view;
    }

}
