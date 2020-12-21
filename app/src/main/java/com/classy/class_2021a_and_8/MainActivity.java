package com.classy.class_2021a_and_8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this
                    , new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}
                    , 123);

        } else {
            writeAndReadExternalStorage();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 123) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                writeAndReadExternalStorage();
            }
        }
    }

    private void writeAndReadExternalStorage() {
        Football ftbl = new Football()
                .setTeamA("Maccabi Tel Aviv")
                .setTeamB("Beitar Jerusalem")
                .setScoreA(4)
                .setScoreB(0)
                .setGameDurationSec(5400);

        String str = new Gson().toJson(ftbl);
        writeToFile(str, "myFile.txt");

        String fileData = readFile("myFile.txt");
        if (fileData != null) {
            Football f = new Gson().fromJson(fileData, Football.class);
        }
    }

    private String readFile(String fileNameWithExtension) {
        Log.d("pttt", "readFile");
        try {
            File folder = new File(getExternalFilesDir(null)
                    + File.separator + "MyFiles" + File.separator + "Adopt_Files");
            File file = new File(folder.getPath() + File.separator + fileNameWithExtension);

            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String read;
            StringBuilder stringBuilder = new StringBuilder("");

            while ((read = bufferedReader.readLine()) != null) {
                stringBuilder.append(read);
            }
            Log.d("Output", stringBuilder.toString());
            bufferedReader.close();
            return stringBuilder.toString();
        } catch (FileNotFoundException e) {
            Log.d("pttt", "readFile FileNotFoundException" + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            Log.d("pttt", "readFile IOException" + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    private void writeToFile(String dataToWrite, String fileNameWithExtension) {
        Log.d("pttt", "writeToFile");
// Environment.getExternalStorageDirectory() needs android:requestLegacyExternalStorage="true" in manifest under application
        //File folder = new File(Environment.getExternalStorageDirectory() + File.separator + "MyFiles" + File.separator + "CSVs");


        File folder = new File(getExternalFilesDir(null)
                + File.separator + "MyFiles" + File.separator + "Adopt_Files");

        boolean isExist = folder.exists();
        boolean success = folder.mkdirs();

        //  Root -> MyFiles -> Adopt_Files -> myFile.txt
        File file = new File(folder.getPath() + File.separator + fileNameWithExtension);
        PrintWriter writer = null;

        OutputStream os = null;
        try {
            os = new FileOutputStream(file);
            writer = new PrintWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8));
            writer.print(dataToWrite);
            writer.flush();
            writer.close();
        } catch (FileNotFoundException e) {
            Log.d("pttt", "writeToFile FileNotFoundException" + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            Log.d("pttt", "writeToFile IOException" + e.getMessage());
            e.printStackTrace();
        }

    }
}



/*
Create file
Open connection
write data
close connection
 */