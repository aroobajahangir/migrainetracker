package com.example.maigrainetracker.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.maigrainetracker.chatbot.ChatAdapter;
import com.example.maigrainetracker.chatbot.ChatbotEngine;
import com.example.maigrainetracker.chatbot.ChatMessage;
import com.example.maigrainetracker.databinding.FragmentChatBinding;

import java.util.ArrayList;
import java.util.List;

public class ChatFragment extends Fragment {

    private FragmentChatBinding binding;
    private ChatAdapter chatAdapter;
    private List<ChatMessage> messages;
    private ChatbotEngine chatbotEngine;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        messages = new ArrayList<>();
        chatbotEngine = new ChatbotEngine();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentChatBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupRecyclerView();
        addInitialBotMessage();

        binding.btnSend.setOnClickListener(v -> sendMessage());
    }

    private void setupRecyclerView() {
        chatAdapter = new ChatAdapter(messages);
        binding.recyclerViewChat.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerViewChat.setAdapter(chatAdapter);
    }

    private void addInitialBotMessage() {
        messages.add(new ChatMessage("Hello! How can I help you today? You can ask me about common migraine medications like Paracetamol, Ibuprofen, or Sumatriptan.", false));
        chatAdapter.notifyItemInserted(messages.size() - 1);
    }

    private void sendMessage() {
        String userInput = binding.etChatInput.getText().toString().trim();
        if (userInput.isEmpty()) {
            return;
        }

        // Add user message to the list
        messages.add(new ChatMessage(userInput, true));
        chatAdapter.notifyItemInserted(messages.size() - 1);
        binding.recyclerViewChat.scrollToPosition(messages.size() - 1);

        // Get bot response
        String botResponse = chatbotEngine.getResponse(userInput);
        messages.add(new ChatMessage(botResponse, false));
        chatAdapter.notifyItemInserted(messages.size() - 1);
        binding.recyclerViewChat.scrollToPosition(messages.size() - 1);

        // Clear the input field
        binding.etChatInput.setText("");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
