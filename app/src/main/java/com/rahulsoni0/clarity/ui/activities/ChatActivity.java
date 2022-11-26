package com.rahulsoni0.clarity.ui.activities;


import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rahulsoni0.clarity.adapters.ChatAdapter;
import com.rahulsoni0.clarity.databinding.ActivityChatBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ChatActivity extends AppCompatActivity {

    private ActivityChatBinding binding;
    String UserName, SelectedTopic, user_msg_key;
    private DatabaseReference dbr;
    List<String> chats = new ArrayList<>();
    ChatAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (getIntent().getExtras() != null) {
            UserName = getIntent().getExtras().get("user_name").toString();
            SelectedTopic = getIntent().getExtras().get("selected_topic").toString();
            Toast.makeText(this, " " + SelectedTopic + "   " + UserName, Toast.LENGTH_SHORT).show();
        }


        dbr = FirebaseDatabase.getInstance().getReference().child(SelectedTopic);
        binding.tvChatRoomTitle.setText("Topic : " + SelectedTopic);

        initRv();

        binding.btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> map = new HashMap<String, Object>();
                user_msg_key = dbr.push().getKey();
                dbr.updateChildren(map);
                DatabaseReference dbr2 = dbr.child(user_msg_key);
                Map<String, Object> map2 = new HashMap<String, Object>();
                map2.put("msg", binding.edtMsg.getText().toString());
                map2.put("user", UserName);
                dbr2.updateChildren(map2);
                initRv();
                binding.edtMsg.setText("");
            }
        });


        dbr.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                updateConversation(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                updateConversation(dataSnapshot);
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
        });

    }

    private void initRv() {
        adapter = new ChatAdapter(chats, this);
        binding.rvChat.setAdapter(adapter);
        binding.rvChat.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        adapter.notifyDataSetChanged();
    }

    private void updateConversation(DataSnapshot dataSnapshot) {
        String msg, user, conversation;
        Iterator i = dataSnapshot.getChildren().iterator();
        while (i.hasNext()) {
            msg = (String) ((DataSnapshot) i.next()).getValue();
            user = (String) ((DataSnapshot) i.next()).getValue();

            conversation = user + ": " + msg;

            chats.add(conversation);

            initRv();

        }
    }


}