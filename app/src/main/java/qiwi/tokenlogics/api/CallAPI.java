package qiwi.tokenlogics.api;

import static org.apache.http.HttpHeaders.AUTHORIZATION;

import android.os.AsyncTask;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;


public class CallAPI extends AsyncTask<String, String, String> {

//    public CallAPI() {
//        //set context variables if required
//    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    public String doInBackground(String... params) {
        final RestTemplate restTemplate = new RestTemplate();
        final HttpHeaders httpHeaders = new HttpHeaders();
        final HttpEntity<String> request = new HttpEntity<>(" ", httpHeaders);
        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:8080/api/v1/user/init", HttpMethod.POST, request, String.class);

        httpHeaders.set("Accept", "application/json");
        httpHeaders.set("Content-type", "application/json");


        return response.getBody();


    }
}