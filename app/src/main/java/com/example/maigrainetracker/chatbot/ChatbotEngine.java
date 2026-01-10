package com.example.maigrainetracker.chatbot;

public class ChatbotEngine {

    public String getResponse(String input) {
        String lowerCaseInput = input.toLowerCase();

        if (lowerCaseInput.contains("paracetamol")) {
            return "Paracetamol is a common pain reliever that can be effective for mild to moderate migraines. Always follow the recommended dosage.\n\nDisclaimer: This is not medical advice. Consult a doctor for any health concerns.";
        } else if (lowerCaseInput.contains("ibuprofen")) {
            return "Ibuprofen is a nonsteroidal anti-inflammatory drug (NSAID) that can help reduce migraine pain and inflammation. It is available over-the-counter.\n\nDisclaimer: This is not medical advice. Consult a doctor for any health concerns.";
        } else if (lowerCaseInput.contains("sumatriptan")) {
            return "Sumatriptan is a prescription medication used to treat acute migraines. It works by narrowing blood vessels in the brain. It is important to use this medication only as prescribed by your doctor.\n\nDisclaimer: This is not medical advice. Consult a doctor for any health concerns.";
        } else if (lowerCaseInput.contains("hello") || lowerCaseInput.contains("hi")) {
            return "Hello! How can I help you today? You can ask me about common migraine medications like Paracetamol, Ibuprofen, or Sumatriptan.";
        } else {
            return "I'm sorry, I can only provide information on a few common migraine medications. Please ask me about Paracetamol, Ibuprofen, or Sumatriptan.\n\nDisclaimer: This is not medical advice. Consult a doctor for any health concerns.";
        }
    }
}
