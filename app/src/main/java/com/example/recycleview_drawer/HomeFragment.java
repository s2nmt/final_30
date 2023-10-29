package com.example.recycleview_drawer;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {


    private RecyclerView rcvData;
    private RoomAdapter roomAdapter;
    public List<Room> list = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        rcvData = view.findViewById(R.id.rcv_data);
        rcvData.setLayoutManager(new LinearLayoutManager(getActivity()));

//        tao khoan cach giua cac item
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL);
        rcvData.addItemDecoration(itemDecoration);

//        set data trong list len view
        roomAdapter = new RoomAdapter(getListRoom());
        rcvData.setAdapter(roomAdapter);

        Button btnAddRoom = view.findViewById(R.id.btnAddRoom);
        btnAddRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // tao mot doi tuong phong khac va them vao list
                Dialog_Room();
            }
        });
        return view;

    }
    private void Dialog_Room(){
        Dialog dialogroom = new Dialog(getContext());
        dialogroom.setContentView(R.layout.dialog_room);

        EditText nameroom = (EditText) dialogroom.findViewById(R.id.idnameroom);
        Button btn_ok = (Button) dialogroom.findViewById(R.id.btn_ok);

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten_room = nameroom.getText().toString().trim();
                Room newRoom = new Room(R.drawable.img_7, ten_room);
                list.add(newRoom);

                // thong bao adapter biet dua lieu da bi thay doi
                roomAdapter.notifyDataSetChanged();
                dialogroom.cancel();
            }
        });
        dialogroom.show();
    }

    // them cac phong vao ArrayList
    public List<Room> getListRoom() {
//        list = new ArrayList<>();
        if(list.size() == 0){
            list.add(new Room(R.drawable.img_7, "Living Room"));
            list.add(new Room(R.drawable.img_8, "Kitchen"));
            list.add(new Room(R.drawable.img_9, "Bed Room"));
            list.add(new Room(R.drawable.img_10, "Dining Room"));
        }

        return list;
    }
}