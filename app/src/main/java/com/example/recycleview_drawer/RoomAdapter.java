package com.example.recycleview_drawer;

import android.content.DialogInterface;
import android.os.Binder;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.RoomViewHolder> {
    public List<Room> mListRoom;

    public RoomAdapter(List<Room> mListRoom) {
        this.mListRoom = mListRoom;
    }


    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        truyen item_room vao bien view co kieu View
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_room, parent, false);
        return new RoomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomViewHolder holder, int position) {
        Room room = mListRoom.get(position);
        if (room == null) {
            return;
        }

        holder.imageAvatar.setImageResource(room.getResourceId());
        holder.txtRoom.setText(room.getRoom());
//        holder.txtDevice.setText(room.getDevice());
        // Handle item click
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailFragment detailFragment = new DetailFragment();
                // Create a new instance of DetailFragment.
                Bundle bundle = new Bundle();
                // Put an integer value into the bundle with key as "index" and value as 'position'.
                //Create a new instance of Bundle.
                bundle.putInt("index", position);
                detailFragment.setArguments(bundle);
                // Set arguments supplied by bundle to detailFragment.
                bundle.putString("roomName", room.getRoom());
                //get name of room with key is roomName
                detailFragment.setArguments(bundle);
                // Navigate to SecondFragment
                FragmentManager fragmentManager = ((AppCompatActivity) v.getContext()).getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.frameLayout,detailFragment)
                        .commit();
            }
        });

        holder.itemView.setOnLongClickListener(v -> {
            // Get adapter position of ViewHolder in RecyclerView and assign it to 'currentPosition'.
            int currentPosition = holder.getAdapterPosition();

            // Create a builder for an alert dialog that uses default alert dialog theme.
            new AlertDialog.Builder(v.getContext())
                    .setTitle("Delete Room") // Set title text for dialog.
                    .setMessage("Are you sure you want to delete this room?") // Set message text for dialog.
                    // Add positive button to dialog with text "OK" and click listener.
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        // This method is called when positive button is clicked.
                        public void onClick(DialogInterface dialog, int which) {
                            //If 'currentPosition' is a valid position

                            if (currentPosition != RecyclerView.NO_POSITION) {
                                // Remove the room at 'currentPosition' from mListRoom.
                                mListRoom.remove(currentPosition);
                                notifyDataSetChanged();
                                // Notify RoomAdapter that underlying data has changed and it should refresh itself.
                            }
                        }
                    })
                    // Add negative button to dialog with text "Cancel" and null click listener.
                    .setNegativeButton(android.R.string.cancel, null)
                    // Set icon for dialog using a drawable resource.
                    .setIcon(android.R.drawable.ic_menu_delete)
                    // Show this dialog, adding it to the screen.
                    .show();

            return true;
        });
    }

    @Override
    public int getItemCount() {
//        kiem tra neu list phong khong rong thi tra ve so luong phan tu
        if (mListRoom != null) {
            return mListRoom.size();
        }
        return 0;
    }

    public class RoomViewHolder extends RecyclerView.ViewHolder {
        //khoi tao bien
        private ImageView imageAvatar;
        private TextView txtRoom;
        private TextView txtDevice;

        public RoomViewHolder(@NonNull View itemView) {
            super(itemView);
//            anh xa bien
            imageAvatar = itemView.findViewById(R.id.img_avatar);
            txtRoom = itemView.findViewById(R.id.txt_room);
//            txtDevice = itemView.findViewById(R.id.txt_device);
        }
    }
}
