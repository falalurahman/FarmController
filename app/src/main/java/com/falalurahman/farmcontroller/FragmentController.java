package com.falalurahman.farmcontroller;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FragmentController extends Fragment{

    FirebaseDatabase database;
    DeviceStatus deviceStatus = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_controller, container, false);

        database = FirebaseDatabase.getInstance();

        final TextView lightToggle = contentView.findViewById(R.id.light_toggle);
        final TextView waterToggle = contentView.findViewById(R.id.water_toggle);
        final TextView fodderToggle = contentView.findViewById(R.id.fodder_toggle);

        ValueEventListener deviceStatusValueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                deviceStatus = dataSnapshot.getValue(DeviceStatus.class);
                if(deviceStatus.getLight()){
                    lightToggle.setText("Turn Off Lights");
                }else {
                    lightToggle.setText("Turn On Lights");
                }
                if(deviceStatus.getWater_motor()){
                    waterToggle.setText("Turn Off Water Motor");
                }else {
                    waterToggle.setText("Turn On Water Motor");
                }
                if(deviceStatus.getFood_motor()){
                    fodderToggle.setText("Turn Off Fodder Motor");
                }else {
                    fodderToggle.setText("Turn On Fodder Motor");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getContext(),"No Internet Connection!!!",Toast.LENGTH_LONG).show();
            }
        };

        DatabaseReference deviceStatusRef = database.getReference("device_status");
        deviceStatusRef.addValueEventListener(deviceStatusValueEventListener);

        lightToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference myRef = database.getReference("device_status/light");
                if(deviceStatus.getLight()){
                    myRef.setValue(false);
                }else {
                    myRef.setValue(true);
                }
            }
        });

        waterToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference myRef = database.getReference("device_status/water_motor");
                if(deviceStatus.getLight()){
                    myRef.setValue(false);
                }else {
                    myRef.setValue(true);
                }
            }
        });

        fodderToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference myRef = database.getReference("device_status/food_motor");
                if(deviceStatus.getLight()){
                    myRef.setValue(false);
                }else {
                    myRef.setValue(true);
                }
            }
        });

        return contentView;
    }
}
