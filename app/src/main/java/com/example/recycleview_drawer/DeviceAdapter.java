package com.example.recycleview_drawer;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DeviceAdapter extends RecyclerView.Adapter<DeviceAdapter.DeviceViewholder> {

    private List<Device> mListDevice;

    public DeviceAdapter(List<Device> mListDevice) {
        this.mListDevice = mListDevice;
    }

    @NonNull
    @Override
    public DeviceViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.device_detail, parent, false);
        return new DeviceViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DeviceViewholder holder, int position) {
        Device device = mListDevice.get(position);
        if(device == null){
            return ;
        }
        holder.imageDevice.setImageResource(device.getIdDevice());
        holder.txtName.setText(device.getDevice());
        holder.txtDetail.setText(device.getDetail());
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
                                mListDevice.remove(currentPosition);
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
        if (mListDevice != null) {
            return mListDevice.size();
        }
        return 0;
    }

    public class DeviceViewholder extends RecyclerView.ViewHolder{

        private ImageView imageDevice;
        private TextView txtName;
        private TextView txtDetail;
        public DeviceViewholder(@NonNull View itemView) {
            super(itemView);
//            anh xa bien
            imageDevice = itemView.findViewById(R.id.img_device);
            txtName = itemView.findViewById(R.id.txt_name);
            txtDetail = itemView.findViewById(R.id.txt_detail);
        }
    }
}
