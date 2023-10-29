package com.example.recycleview_drawer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DetailFragment extends Fragment {



    private RecyclerView rcvDetail;
    private RecyclerView rcvLed;
    private DeviceAdapter deviceAdapter;
    private LedAdapter ledAdapter;
    public List<Device> list = new ArrayList<>();
    int count = 3;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        // Retrieve room name from arguments
        Bundle args = getArguments();

        if (args != null) {
            String roomName = args.getString("roomName");
            Log.d("roomName", "roomName:" + roomName);

            TextView txtNameRoom = view.findViewById(R.id.txtNameRoom);
            txtNameRoom.setText(roomName);
        }
        int index = getArguments().getInt("index");
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL);

        switch (index){

            default:

                rcvLed = view.findViewById(R.id.rcv_detail_led);
                rcvLed.setLayoutManager(new LinearLayoutManager(getActivity()));
                rcvLed.addItemDecoration(itemDecoration);

                // Assuming you want 10 items in the RecyclerView.
                ledAdapter = new LedAdapter(3);

                rcvLed.setAdapter(ledAdapter);
                break;

        }
        // tao OnClickListener cho button
        ImageButton btnAddRoom = view.findViewById(R.id.btnBack);
        btnAddRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create new instance of HomeFragment
                RoomFragment roomFragment = new RoomFragment();

                // Navigate to HomeFragment
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, roomFragment)
                        .commit();
            }
        });

        Button btnAddDevice = view.findViewById(R.id.btnAddDevice);
        btnAddDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call a method to add a new device
                addNewDevice();
            }
        });
        return view;
    }

    private void addNewDevice() {
        count++;
        ledAdapter.addItem();
        ledAdapter = new LedAdapter(count);

        rcvLed.setAdapter(ledAdapter);
    }
}