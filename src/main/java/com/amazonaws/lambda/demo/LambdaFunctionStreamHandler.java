package com.amazonaws.lambda.demo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.HashMap;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


/**
 * A Sample request handler for REST or HTTP APIs using the RequestStreamHandler input method
 * Supports either v1 or v2 payload formats
 * @author georgmao
 *
 */
public class LambdaFunctionStreamHandler implements RequestStreamHandler {
	Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
    @Override
    public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(input, Charset.forName("US-ASCII")));
        PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(output, Charset.forName("US-ASCII"))));
        
        HashMap event = gson.fromJson(reader, HashMap.class);
        
        writer.write(gson.toJson(event));
    }

}
