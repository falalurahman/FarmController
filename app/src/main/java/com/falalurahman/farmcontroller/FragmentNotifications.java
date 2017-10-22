package com.falalurahman.farmcontroller;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class FragmentNotifications extends Fragment{

    ArrayList<Notifications> allNotifications = null;
    NotificationsAdapter notificationsAdapter = null;
    FirebaseDatabase database;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_notifications, container, false);

        final ListView notificationListView = contentView.findViewById(R.id.notification_listview);
        allNotifications = new ArrayList<>();
        notificationsAdapter = new NotificationsAdapter(getContext(),allNotifications);
        notificationListView.setAdapter(notificationsAdapter);

        database = FirebaseDatabase.getInstance();
        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                Notifications notifications = dataSnapshot.getValue(Notifications.class);
                allNotifications.add(notifications);
                notificationsAdapter.notifyDataSetChanged();

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
        };

        DatabaseReference deviceStatusRef = database.getReference("notifications");
        deviceStatusRef.addChildEventListener(childEventListener);

        return contentView;
    }

    public class NotificationsAdapter extends ArrayAdapter<Notifications>{
        public NotificationsAdapter(@NonNull Context context, @NonNull List<Notifications> objects) {
            super(context, R.layout.row_notifications, objects);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View rowView = convertView;
            if (rowView == null) {
                LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                rowView = inflater.inflate(R.layout.row_notifications, parent, false);
            }
            Notifications notification = getItem(position);

            TextView message = rowView.findViewById(R.id.message);
            TextView timeStamp = rowView.findViewById(R.id.date);

            message.setText(notification.getMessage());
            //timeStamp.setText(DateFormat.format("hh:mm dd MMM yyyy",notification.getTimeStamp()));

            return rowView;
        }

        @Nullable
        @Override
        public Notifications getItem(int position) {
            return super.getItem(getCount() - position - 1);
        }
    }
}
