package com.example.chatapp.adapters;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatapp.databinding.ItemContainerReceivedMessageBinding;
import com.example.chatapp.databinding.ItemContainerSentMessageBinding;
import com.example.chatapp.models.ChatMessage;

import java.util.List;


public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Bitmap receiverProfileImage;


    private final List<ChatMessage> chatMessages;
    private final String sendId;

    public static final int VIEW_TYPE_SENT =1;
    public static final int VIEW_TYPE_RECEIVED =2;

    public ChatAdapter(List<ChatMessage> chatMessage, Bitmap receiverProfileImage, String sendId) {
        this.chatMessages = chatMessage;
        this.receiverProfileImage = receiverProfileImage;
        this.sendId = sendId;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == VIEW_TYPE_SENT){
            return new SentMessageViewHolder(ItemContainerSentMessageBinding
                    .inflate(LayoutInflater.from(parent.getContext()), parent, false));

        } else {
            return new ReceiverMessageViewHolder(ItemContainerReceivedMessageBinding
                    .inflate(LayoutInflater.from(parent.getContext()), parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(getItemViewType(position) == VIEW_TYPE_SENT) {
            ((SentMessageViewHolder)holder).setData(chatMessages.get(position));
        } else {
            ((ReceiverMessageViewHolder)holder)
                    .setData(chatMessages.get(position), receiverProfileImage);
        }
    }

    @Override
    public int getItemCount() {
        return chatMessages.size();
    }



    @Override
    public int getItemViewType(int position){
        if (sendId != null && sendId.equals(chatMessages.get(position).senderId)) {
            return VIEW_TYPE_SENT;
        }else {
            return VIEW_TYPE_RECEIVED;
        }
    }

    /**
     *
     */
    static class SentMessageViewHolder extends RecyclerView.ViewHolder {

        private final ItemContainerSentMessageBinding binding;

        public SentMessageViewHolder(ItemContainerSentMessageBinding itemContainerSentMessageBinding) {
            super(itemContainerSentMessageBinding.getRoot());
            binding = itemContainerSentMessageBinding;
        }

        void setData(ChatMessage chatMessage) {
            binding.textMessage.setText(chatMessage.message);
            binding.textDateTime.setText(chatMessage.dateTime);
        }
    }
        /**
         *
         */
        static class ReceiverMessageViewHolder extends RecyclerView.ViewHolder{
            private final ItemContainerReceivedMessageBinding binding;

            public ReceiverMessageViewHolder(ItemContainerReceivedMessageBinding itemContainerReceivedMessageBinding) {
                super(itemContainerReceivedMessageBinding.getRoot());
                binding = itemContainerReceivedMessageBinding;
            }

            void setData(ChatMessage chatMessage, Bitmap receiverProfileImage){
                binding.textMessage.setText(chatMessage.message);
                binding.textMessage.setText(chatMessage.dateTime);

               // if(receiverProfileImage != null){}
                binding.imageProfile.setImageBitmap(receiverProfileImage);
            }
        }
    }


