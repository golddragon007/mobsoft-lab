package com.example.marton.stephane.mobsoft_lab.mock.interceptors;

import android.net.Uri;
import android.util.Log;

import com.example.marton.stephane.mobsoft_lab.network.NetworkConfig;
import com.example.marton.stephane.mobsoft_lab.repository.MemoryRepository;
import com.example.marton.stephane.mobsoft_lab.utils.GsonHelper;

import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.marton.stephane.mobsoft_lab.mock.interceptors.MockHelper.makeResponse;

public class AnimeComments {
    public static Response process(Request request) {
        Uri uri = Uri.parse(request.url().toString());

        String responseString = "";
        int responseCode;
        Headers headers = request.headers();


        if (uri.getPath().equals(NetworkConfig.ENDPOINT_PREFIX + "comments") && request.method().equals("POST")) {
            MemoryRepository memoryRepository = new MemoryRepository();
            memoryRepository.open(null);
            //memoryRepository.saveComment();
            responseString = "";
            responseCode = 200;
        } else if (uri.getPath().equals(NetworkConfig.ENDPOINT_PREFIX + "comments") && request.method().equals("GET")) {
            MemoryRepository memoryRepository = new MemoryRepository();
            memoryRepository.open(null);
            //responseString = GsonHelper.getGson().toJson(memoryRepository.getComments());
            responseCode = 200;
        } else if (uri.getPath().equals(NetworkConfig.ENDPOINT_PREFIX + "comments") && request.method().equals("UPDATE")) {
            MemoryRepository memoryRepository = new MemoryRepository();
            memoryRepository.open(null);
            //responseString = GsonHelper.getGson().toJson(memoryRepository.updateComments());
            responseCode = 200;
        } else if (uri.getPath().equals(NetworkConfig.ENDPOINT_PREFIX + "comments") && request.method().equals("DELETE")) {
            MemoryRepository memoryRepository = new MemoryRepository();
            memoryRepository.open(null);
            //responseString = GsonHelper.getGson().toJson(memoryRepository.removeComment());
            responseCode = 200;
        } else if (uri.getPath().equals(NetworkConfig.ENDPOINT_PREFIX + "login") && request.method().equals("POST")) {
            MemoryRepository memoryRepository = new MemoryRepository();
            memoryRepository.open(null);
            Log.d("Headers", request.headers().toString());
            responseString = GsonHelper.getGson().toJson(memoryRepository.Login());
            responseCode = 200;
        } else {
            responseString = "ERROR";
            responseCode = 503;
        }

        return makeResponse(request, headers, responseCode, responseString);
    }
}
