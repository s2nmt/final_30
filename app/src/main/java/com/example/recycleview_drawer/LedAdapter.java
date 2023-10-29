package com.example.recycleview_drawer;

// File: MyAdapter.java
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class LedAdapter extends RecyclerView.Adapter<LedAdapter.MyViewHolder> {
    private int numItems;
    private List<Boolean> switchStates;

    public LedAdapter() {
        switchStates = new ArrayList<>();
    }
    public LedAdapter(int numItems) {
        this.numItems = numItems;
        switchStates = new ArrayList<>(numItems);
        for (int i = 0; i < numItems; i++) {
            switchStates.add(false);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.device_turn_on_of, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        SeekBar seekBar = holder.itemView.findViewById(R.id.seek_brightness);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // Xử lý khi giá trị SeekBar thay đổi
                // progress là giá trị hiện tại của SeekBar
                // fromUser là một cờ cho biết sự thay đổi là do người dùng thao tác SeekBar hay không
                float brightness = (float) progress / 100; // Chuyển đổi giá trị thành một tỷ lệ từ 0.0 đến 1.0
                brightness = Math.max(0.2f, Math.min(1.0f, brightness));
                holder.imageView.setAlpha(brightness); // Đặt độ sáng của ảnh

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Xử lý khi người dùng bắt đầu di chuyển SeekBar
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Xử lý khi người dùng kết thúc di chuyển SeekBar
            }
        });

        holder.switch1.setChecked(switchStates.get(position));

        holder.switch1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            switchStates.set(position, isChecked);
            if (isChecked) {
                holder.imageView.setImageResource(R.drawable.led_on);
                seekBar.setVisibility(View.VISIBLE);
                seekBar.setProgress(100);
            } else {
                holder.imageView.setImageResource(R.drawable.led_off);
                seekBar.setVisibility(View.INVISIBLE);
                seekBar.setProgress(100);
            }
        });

        holder.itemView.setOnLongClickListener(v -> {
            Log.d("Position", "Position: " + position);
            new AlertDialog.Builder(v.getContext())
                    .setTitle("Delete Device") // Set title text for dialog.
                    .setMessage("Are you sure you want to delete this room?") // Set message text for dialog.
                    // Add positive button to dialog with text "OK" and click listener.
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        // This method is called when positive button is clicked.
                        public void onClick(DialogInterface dialog, int which) {
                            //If 'currentPosition' is a valid position
                            removeItem(position);
                            notifyDataSetChanged();

                        }
                    })
                    // Add negative button to dialog with text "Cancel" and null click listener.
                    .setNegativeButton(android.R.string.cancel, null)
                    // Set icon for dialog using a drawable resource.
                    .setIcon(android.R.drawable.ic_menu_delete)
                    // Show this dialog, adding it to the screen.
                    .show();

           // removeItem(position);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return numItems;
    }

    public void removeItem(int position) {
        if (position >= 0 && position < numItems) {
            switchStates.remove(position);
            numItems--;
            notifyItemRemoved(position);
        }
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public Switch switch1;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_led);
            switch1 = itemView.findViewById(R.id.sw_led);
        }
    }
    public void addItem() {
        switchStates.add(false); // Bạn có thể đặt giá trị mặc định cho trạng thái ở đây
        notifyItemInserted(switchStates.size() - 1);
    }
}


