package com.example.marton.stephane.mobsoft_lab.mock.interceptors;

import android.net.Uri;

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

        String responseString;
        int responseCode;
        Headers headers = request.headers();


        if (uri.getPath().equals(NetworkConfig.ENDPOINT_PREFIX + "comments") && request.method().equals("POST")) {
            responseString = "";
            responseCode = 200;
        }else if (uri.getPath().equals(NetworkConfig.ENDPOINT_PREFIX + "comments") && request.method().equals("Get")) {
            MemoryRepository memoryRepository = new MemoryRepository();
            memoryRepository.open(null);
            responseString = GsonHelper.getGson().toJson(memoryRepository.getAnimeListItems());
            responseCode = 200;
        } else {
            responseString = "ERROR";
            responseCode = 503;
        }

        return makeResponse(request, headers, responseCode, responseString);
    }
}
