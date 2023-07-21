package com.fuzzyrock;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.fuzzyrock.EnvConst.CHAT_GPT_API_TOKEN;

public class ChatGPT {
    private static final String TAG = ChatGPT.class.getSimpleName();
    public static String chat(String text) {
        StringBuilder sb = new StringBuilder();

        try {
            String url = "https://api.openai.com/v1/completions";
            HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();

            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Authorization", "Bearer " + CHAT_GPT_API_TOKEN);

            JSONObject data = new JSONObject();
            data.put("model", "text-davinci-003");
            data.put("prompt", text);
            data.put("max_tokens", 4000);
            data.put("temperature", 1.0);

            con.setDoOutput(true);
            con.getOutputStream().write(data.toString().getBytes());

            String output = new BufferedReader(new InputStreamReader(con.getInputStream())).lines()
                    .reduce((a, b) -> a + b).get();

            sb.append(new JSONObject(output).getJSONArray("choices").getJSONObject(0).getString("text"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }
}
