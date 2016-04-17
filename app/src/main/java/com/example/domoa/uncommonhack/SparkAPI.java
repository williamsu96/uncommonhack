package com.example.domoa.uncommonhack;
import com.android.volley.*;
import android.content.Context;
import android.app.Activity;
import android.widget.TextView;

import com.android.volley.toolbox.*;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;


/**
 * Created by domoa on 4/16/2016.
 */
public class SparkAPI {

    static int makeRequest(Activity ctx, String function, String arg) {
        RequestQueue queue = Volley.newRequestQueue(ctx);
        // The API for accessing our Spark Core
        String url = "https://api.particle.io/v1/devices/55ff6b065075555331281787/" + function;

        final TextView textView = (TextView) ctx.findViewById(R.id.text);

        try {
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("access_token", "38549d88528ddc5e97f6ce8dc0c2ebb33d91d6a7");
            jsonBody.put("args", arg);
            final String body = jsonBody.toString();


            // Building our request...
            StringRequest request = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            textView.setText("Response is: " + response.toString());
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            textView.setText("Error: " + error.toString());
                        }
                    }
            ) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return body == null ? null : body.getBytes("utf-8");
                    } catch (UnsupportedEncodingException e) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s",
                                body, "utf-8");
                        return null;
                    }
                }
            };

            queue.add(request);

        } catch (JSONException jse) {
            return -1;
        }
        return 1;
    }
}
