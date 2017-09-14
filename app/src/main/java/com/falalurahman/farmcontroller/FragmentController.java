package com.falalurahman.farmcontroller;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FragmentController extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_controller, container, false);

        TextView lightToggle = (TextView) contentView.findViewById(R.id.light_toggle);
        TextView waterToggle = (TextView) contentView.findViewById(R.id.water_toggle);
        TextView fodderToggle = (TextView) contentView.findViewById(R.id.fodder_toggle);

        lightToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("message");

                myRef.setValue("Hello, World!");
            }
        });

        return contentView;
    }
}
