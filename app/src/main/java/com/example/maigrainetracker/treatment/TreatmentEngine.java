package com.example.maigrainetracker.treatment;

import com.example.maigrainetracker.data.MigraineRecord;

public class TreatmentEngine {

    public String getTreatmentAdvice(MigraineRecord record) {
        if (record == null) {
            return "No migraine data available to generate advice.";
        }

        StringBuilder advice = new StringBuilder();

        // Pain Level Advice
        if (record.painLevel >= 7) {
            advice.append("High pain level detected. It is recommended to rest in a dark, quiet room.\n\n");
        } else if (record.painLevel >= 4) {
            advice.append("Moderate pain level detected. Consider taking a break and relaxing.\n\n");
        } else {
            advice.append("Low pain level detected. Try to continue with your daily activities but avoid strenuous tasks.\n\n");
        }

        // Trigger-based Advice
        if (record.stress) {
            advice.append("Stress was identified as a trigger. Practice deep breathing exercises and mindfulness to reduce stress.\n\n");
        }
        if (record.sleepDeprivation) {
            advice.append("Sleep deprivation was a trigger. Aim for 7-9 hours of quality sleep per night and maintain a consistent sleep schedule.\n\n");
        }

        // Medicine Suggestions
        advice.append("Non-prescription medicine suggestions:\n");
        advice.append("- Paracetamol\n");
        advice.append("- Ibuprofen\n");
        advice.append("- Sumatriptan (Note: Consult with a doctor before taking Sumatriptan as it is a prescription medication.)\n\n");

        advice.append("\nDisclaimer: The information provided is for educational purposes only and is not a substitute for professional medical advice. Always consult with a healthcare professional for any health concerns or before making any decisions related to your health or treatment.");

        return advice.toString();
    }
}
