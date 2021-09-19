package com.example.theweatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {
    TextView findzip;
    TextView currenttemp;
    ImageView currentTempImage;
    ImageView imageDayOne;
    ImageView ImageDayTwo;
    ImageView imageDayThree;
    ImageView imageDayFour;
    ImageView imageDayFive;
    EditText editText;
    Button button;
    String zipcode;
    TextView minTemp;
    TextView DayOneMaxTemp;
    TextView DayTwoMinTemp;
    TextView DayTwoMaxTemp;
    TextView DayThreeMinTemp;
    TextView DayThreeMaxTemp;
    TextView DayFourMinTemp;
    TextView DayFourMaxTemp;
    TextView DayFiveMinTemp;
    TextView DayFiveMaxTemp;
    TextView Time;
    TextView DayTwoTime;
    TextView DayThreeTime;
    TextView DayFourTime;
    TextView DayFiveTime;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findzip = findViewById(R.id.editText);
        //findzip.setTextColor(Color.WHITE);
        currenttemp = findViewById(R.id.currenttemp);
        //currenttemp.setTextColor(Color.WHITE);
        //day1 = findViewById(R.id.day1);
        //day1.setTextColor(Color.WHITE);
        //day2 = findViewById(R.id.day2);
     //   day2.setTextColor(Color.WHITE);
        //day3 = findViewById(R.id.day3);
        //day3.setTextColor(Color.WHITE);
        //day4 = findViewById(R.id.day4);
       // day4.setTextColor(Color.WHITE);
       // day5 = findViewById(R.id.day5);
        //day5.setTextColor(Color.WHITE);
        currentTempImage = findViewById(R.id.imageView6);
        imageDayOne = findViewById(R.id.imageDaOne);
        ImageDayTwo = findViewById(R.id.imageDayOne);
        imageDayThree = findViewById(R.id.imageDayThree);
        imageDayFour = findViewById(R.id.imageDayFour);
        imageDayFive = findViewById(R.id.imageDayFive);
        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);
        minTemp = findViewById(R.id.lowDayOne);
     //   min1.setTextColor(Color.WHITE);
        DayOneMaxTemp = findViewById(R.id.HighDayOne);
      //  max1.setTextColor(Color.WHITE);
        DayTwoMinTemp = findViewById(R.id.lowdayTwo2);
       // min2.setTextColor(Color.WHITE);
        DayTwoMaxTemp = findViewById(R.id.highdaytwo);
       // max2.setTextColor(Color.WHITE);
        DayThreeMinTemp = findViewById(R.id.lowdayThree);
       // min3.setTextColor(Color.WHITE);
        DayThreeMaxTemp = findViewById(R.id.highdaythree);
      //  max3.setTextColor(Color.WHITE);
        DayFourMinTemp = findViewById(R.id.lowdayfour);
       // min4.setTextColor(Color.WHITE);
        DayFourMaxTemp = findViewById(R.id.highdayfour);
        //max4.setTextColor(Color.WHITE);
        DayFiveMinTemp = findViewById(R.id.lowdayfive);
       // min5.setTextColor(Color.WHITE);
        DayFiveMaxTemp = findViewById(R.id.highdayfive);
       // max5.setTextColor(Color.WHITE);
        Time = findViewById(R.id.TimeOne);
       // time1.setTextColor(Color.WHITE);
        DayTwoTime = findViewById(R.id.TimeTwo);
       // time2.setTextColor(Color.WHITE);
        DayThreeTime = findViewById(R.id.TimeThree);
       // time3.setTextColor(Color.WHITE);
        DayFourTime = findViewById(R.id.TimeFour);
        //time4.setTextColor(Color.WHITE);
        DayFiveTime = findViewById(R.id.TimeFive);
       // time5.setTextColor(Color.WHITE);

        AsyncThread myThread = new AsyncThread();
        myThread.execute();
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                zipcode = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //AsyncThread myThread = new AsyncThread();
                //myThread.execute(zipcode);
            }
        });

    }
    public class AsyncThread extends AsyncTask<String, Void, Void>
    {


        @SuppressLint("WrongThread")
        @Override
        protected Void doInBackground(String... params) {
            try {
                URL url = new URL("http://api.openweathermap.org/data/2.5/forecast?zip=08852&APPID=c90aaa82260e4dde90662c6d498075cb&units=imperial");
                //URL example = new URL("http://api.openweathermap.org/data/2.5/forecast?zip=" + params[0] + "&APPID=c90aaa82260e4dde90662c6d498075cb&units=imperial");
                URLConnection connection = url.openConnection();
                InputStream stream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                String line = reader.readLine();
                URL urlTwo = new URL("http://api.openweathermap.org/data/2.5/weather?zip=08852,us&appid=c90aaa82260e4dde90662c6d498075cb&units=imperial");
                URLConnection connection2 = urlTwo.openConnection();
                InputStream StreamTwo = connection2.getInputStream();
                BufferedReader ReaderTwo = new BufferedReader(new InputStreamReader(StreamTwo));
                String lineTwo = ReaderTwo.readLine();
                onPostExecute(line, lineTwo);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }  catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        protected void onPostExecute(String a, String b) throws JSONException {
            JSONObject object = new JSONObject(a);
            JSONArray list = object.getJSONArray("list");

            //current date
            JSONObject objectNow = new JSONObject(b);
            JSONObject mainNow = objectNow.getJSONObject("main");
            int tempNow = (int)mainNow.getDouble("temp");
            currenttemp.setText(String.valueOf(tempNow) + "°");

            JSONArray weatherNow = objectNow.getJSONArray("weather");
            int nowID = weatherNow.getJSONObject(0).getInt("id");
            if(nowID >= 200 && nowID <= 232) {
                currentTempImage.setImageResource(R.drawable.thunderstorm);
            }
            else if(nowID >= 300 && nowID <= 321)
            {
                currentTempImage.setImageResource(R.drawable.drizzle);
            }
            else if(nowID >= 500 && nowID <= 531)
            {
                currentTempImage.setImageResource(R.drawable.rainy);
            }
            else if(nowID >= 600 && nowID <= 622)
            {
                currentTempImage.setImageResource(R.drawable.snowflake);
            }
            else if(nowID == 800)
            {
                currentTempImage.setImageResource(R.drawable.clear);
            }
            else if(nowID >= 801 && nowID <= 804)
            {
                currentTempImage.setImageResource(R.drawable.cloud);
            }

            //day1
            JSONObject firstMain = list.getJSONObject(0);
            JSONObject main1 = firstMain.getJSONObject("main");
            int temp1 = (int) main1.getDouble("temp");
           // day1.setText(String.valueOf(temp1) + "°");

            String firstTime = new JSONObject(a).getJSONArray("list").getJSONObject(0).getString("dt_txt");
            if(Integer.parseInt(firstTime.substring(firstTime.indexOf(" ") + 1,firstTime.indexOf(" ") + 3)) - 5 > 12)
            {
                Time.setText(Integer.toString(Integer.parseInt(firstTime.substring(firstTime.indexOf(" ") + 1, firstTime.indexOf(" ") + 3)) - 17) + ":00 PM");
            }
            else if(Integer.parseInt(firstTime.substring(firstTime.indexOf(" ") + 1,firstTime.indexOf(" ") + 3)) - 5 < 0)
                Time.setText(Integer.toString(Integer.parseInt(firstTime.substring(firstTime.indexOf(" ") + 1, firstTime.indexOf(" ") + 3)) - 7) + ":00 PM");
            else
                Time.setText(Integer.toString(Integer.parseInt(firstTime.substring(firstTime.indexOf(" ") + 1, firstTime.indexOf(" ") + 3)) - 5) + ":00 AM");



            int tempMin1 = (int)main1.getDouble("temp_min");
            int tempMax1 = (int)main1.getDouble("temp_max");
            minTemp.setText("Low: " + Integer.toString(tempMin1) + "°");
            DayOneMaxTemp.setText("High: " + Integer.toString(tempMax1) + "°");

            JSONArray weather1 = firstMain.getJSONArray("weather");
            int ID1 = weather1.getJSONObject(0).getInt("id");

            if(ID1 >= 200 && ID1 <= 232) {
                //main.setImageResource(R.drawable.thunder);
                imageDayOne.setImageResource(R.drawable.thunderstorm);
            }
            else if(ID1 >= 300 && ID1 <= 321)
            {
                //main.setImageResource(R.drawable.drizzle);
                imageDayOne.setImageResource(R.drawable.drizzle);
            }
            else if(ID1 >= 500 && ID1 <= 531)
            {
                //main.setImageResource(R.drawable.rain);
                imageDayOne.setImageResource(R.drawable.rainy);
            }
            else if(ID1 >= 600 && ID1 <= 622)
            {
                //main.setImageResource(R.drawable.snow);
                imageDayOne.setImageResource(R.drawable.snowflake);
            }
            else if(ID1 == 800)
            {
                //main.setImageResource(R.drawable.clear);
                imageDayOne.setImageResource(R.drawable.clear);
            }
            else if(ID1 >= 801 && ID1 <= 804)
            {
                //main.setImageResource(R.drawable.clouds);
                imageDayOne.setImageResource(R.drawable.cloud);
            }

            //day2
            JSONObject secondMain = list.getJSONObject(1);
            JSONObject main2 = secondMain.getJSONObject("main");
            int temp2 = (int) main2.getDouble("temp");
            //day2.setText(String.valueOf(temp2) + "°");


            String secondTime = new JSONObject(a).getJSONArray("list").getJSONObject(1).getString("dt_txt");
            if(Integer.parseInt(secondTime.substring(secondTime.indexOf(" ") + 1,secondTime.indexOf(" ") + 3)) - 5 > 12)
            {
                DayTwoTime.setText(Integer.toString(Integer.parseInt(firstTime.substring(firstTime.indexOf(" ") + 1, firstTime.indexOf(" ") + 3)) - 17) + ":00 PM");
            }
            else if(Integer.parseInt(secondTime.substring(secondTime.indexOf(" ") + 1,secondTime.indexOf(" ") + 3)) - 5 < 0)
                DayTwoTime.setText(Integer.toString(Integer.parseInt(secondTime.substring(secondTime.indexOf(" ") + 1, secondTime.indexOf(" ") + 3)) + 7) + ":00 PM");
            else
                DayTwoTime.setText(Integer.toString(Integer.parseInt(secondTime.substring(secondTime.indexOf(" ") + 1, secondTime.indexOf(" ") + 3)) - 5) + ":00 AM");


            int tempMin2 = (int)main2.getDouble("temp_min");
            int tempMax2 = (int)main2.getDouble("temp_max");
            DayTwoMinTemp.setText("Low: " + Integer.toString(tempMin2) + "°");
            DayTwoMaxTemp.setText("High: " + Integer.toString(tempMax2) + "°");

            JSONArray weather2 = secondMain.getJSONArray("weather");
            int ID2 = weather2.getJSONObject(0).getInt("id");
            if(ID2 >= 200 && ID2 <= 232)
                ImageDayTwo.setImageResource(R.drawable.thunderstorm);
            else if(ID2 >= 300 && ID2 <= 321)
                ImageDayTwo.setImageResource(R.drawable.drizzle);
            else if(ID2 >= 500 && ID2 <= 531)
                ImageDayTwo.setImageResource(R.drawable.rainy);
            else if(ID2 >= 600 && ID2 <= 622)
                ImageDayTwo.setImageResource(R.drawable.snowflake);
            else if(ID2 == 800)
                ImageDayTwo.setImageResource(R.drawable.clear);
            else if(ID2 >= 801 && ID2 <= 804)
                ImageDayTwo.setImageResource(R.drawable.cloud);

            //day3
            JSONObject thirdMain = list.getJSONObject(2);
            JSONObject main3 = thirdMain.getJSONObject("main");
            int temp3 = (int) main3.getDouble("temp");
           // day3.setText(String.valueOf(temp3) + "°");

            String thirdTime = new JSONObject(a).getJSONArray("list").getJSONObject(2).getString("dt_txt");
            if(Integer.parseInt(thirdTime.substring(thirdTime.indexOf(" ") + 1,thirdTime.indexOf(" ") + 3)) - 5 > 12)
            {
                DayThreeTime.setText(Integer.toString(Integer.parseInt(thirdTime.substring(thirdTime.indexOf(" ") + 1, thirdTime.indexOf(" ") + 3)) - 17) + ":00 PM");
            }
            else if(Integer.parseInt(thirdTime.substring(thirdTime.indexOf(" ") + 1,thirdTime.indexOf(" ") + 3)) - 5 < 0)
                DayThreeTime.setText(Integer.toString(Integer.parseInt(thirdTime.substring(thirdTime.indexOf(" ") + 1, thirdTime.indexOf(" ") + 3)) + 7) + ":00 PM");
            else
                DayThreeTime.setText(Integer.toString(Integer.parseInt(thirdTime.substring(thirdTime.indexOf(" ") + 1, thirdTime.indexOf(" ") + 3)) - 5) + ":00 AM");


            int tempMin3 = (int)main3.getDouble("temp_min");
            int tempMax3 = (int)main3.getDouble("temp_max");
            DayThreeMinTemp.setText("Low: " + Integer.toString(tempMin3) + "°");
            DayThreeMaxTemp.setText("High: " + Integer.toString(tempMax3) + "°");

            JSONArray weather3 = thirdMain.getJSONArray("weather");
            int ID3 = weather3.getJSONObject(0).getInt("id");
            if(ID3 >= 200 && ID3 <= 232)
                imageDayThree.setImageResource(R.drawable.thunderstorm);
            else if(ID3 >= 300 && ID3 <= 321)
                imageDayThree.setImageResource(R.drawable.drizzle);
            else if(ID3 >= 500 && ID3 <= 531)
                imageDayThree.setImageResource(R.drawable.rainy);
            else if(ID3 >= 600 && ID3 <= 622)
                imageDayThree.setImageResource(R.drawable.snowflake);
            else if(ID3 == 800)
                imageDayThree.setImageResource(R.drawable.clear);
            else if(ID3 >= 801 && ID3 <= 804)
                imageDayThree.setImageResource(R.drawable.cloud);

            //day4
            JSONObject fourthMain = list.getJSONObject(3);
            JSONObject main4 = fourthMain.getJSONObject("main");
            int temp4 = (int) main4.getDouble("temp");
            //day4.setText(String.valueOf(temp4) + "°");

            String fourthTime = new JSONObject(a).getJSONArray("list").getJSONObject(3).getString("dt_txt");
            if(Integer.parseInt(fourthTime.substring(fourthTime.indexOf(" ") + 1,fourthTime.indexOf(" ") + 3)) - 5 > 12)
            {
                DayFourTime.setText(Integer.toString(Integer.parseInt(fourthTime.substring(fourthTime.indexOf(" ") + 1, fourthTime.indexOf(" ") + 3)) - 17) + ":00 PM");
            }
            else if(Integer.parseInt(fourthTime.substring(fourthTime.indexOf(" ") + 1,fourthTime.indexOf(" ") + 3)) - 5 < 0)
                DayFourTime.setText(Integer.toString(Integer.parseInt(fourthTime.substring(fourthTime.indexOf(" ") + 1, fourthTime.indexOf(" ") + 3)) + 7) + ":00 PM");
            else
                DayFourTime.setText(Integer.toString(Integer.parseInt(fourthTime.substring(fourthTime.indexOf(" ") + 1, fourthTime.indexOf(" ") + 3)) - 5) + ":00 AM");


            int tempMin4 = (int)main4.getDouble("temp_min");
            int tempMax4 = (int)main4.getDouble("temp_max");
            DayFourMinTemp.setText("Low: " + Integer.toString(tempMin4) + "°");
            DayFourMaxTemp.setText("High: " + Integer.toString(tempMax4) + "°");

            JSONArray weather4 = fourthMain.getJSONArray("weather");
            int ID4 = weather4.getJSONObject(0).getInt("id");
            if(ID4 >= 200 && ID4 <= 232)
                imageDayFour.setImageResource(R.drawable.thunderstorm);
            else if(ID4 >= 300 && ID4 <= 321)
                imageDayFour.setImageResource(R.drawable.drizzle);
            else if(ID4 >= 500 && ID4 <= 531)
                imageDayFour.setImageResource(R.drawable.rainy);
            else if(ID4 >= 600 && ID4 <= 622)
                imageDayFour.setImageResource(R.drawable.snowflake);
            else if(ID4 == 800)
                imageDayFour.setImageResource(R.drawable.clear);
            else if(ID4 >= 801 && ID4 <= 804)
                imageDayFour.setImageResource(R.drawable.cloud);

            //day5
            JSONObject fifthMain = list.getJSONObject(4);
            JSONObject main5 = fifthMain.getJSONObject("main");
            int temp5 = (int) main5.getDouble("temp");
           // day5.setText(String.valueOf(temp5) + "°");

            String fifthTime = new JSONObject(a).getJSONArray("list").getJSONObject(4).getString("dt_txt");
            if(Integer.parseInt(fifthTime.substring(fifthTime.indexOf(" ") + 1,fifthTime.indexOf(" ") + 3)) - 5 > 12)
            {
                DayFiveTime.setText(Integer.toString(Integer.parseInt(fifthTime.substring(fifthTime.indexOf(" ") + 1, fifthTime.indexOf(" ") + 3)) - 17) + ":00 PM");
            }
            else if(Integer.parseInt(fifthTime.substring(fifthTime.indexOf(" ") + 1,fifthTime.indexOf(" ") + 3)) - 5 < 0)
                DayFiveTime.setText(Integer.toString(Integer.parseInt(fifthTime.substring(fifthTime.indexOf(" ") + 1, fifthTime.indexOf(" ") + 3)) + 7) + ":00 PM");
            else {
                DayFiveTime.setText(Integer.toString(Integer.parseInt(fifthTime.substring(fifthTime.indexOf(" ") + 1, fifthTime.indexOf(" ") + 3)) - 5) + ":00 AM");
            }


            int tempMin5 = (int)main5.getDouble("temp_min");
            int tempMax5 = (int)main5.getDouble("temp_max");
            DayFiveMinTemp.setText("Low: " + Integer.toString(tempMin5) + "°");
            DayFiveMaxTemp.setText("High: " + Integer.toString(tempMax5) + "°");

            JSONArray weather5 = fifthMain.getJSONArray("weather");
            int ID5 = weather5.getJSONObject(0).getInt("id");
            if(ID5 >= 200 && ID5 <= 232)
                imageDayFive.setImageResource(R.drawable.thunderstorm);
            else if(ID5 >= 300 && ID5 <= 321)
                imageDayFive.setImageResource(R.drawable.drizzle);
            else if(ID5 >= 500 && ID5 <= 531)
                imageDayFive.setImageResource(R.drawable.rainy);
            else if(ID5 >= 600 && ID5 <= 622)
                imageDayFive.setImageResource(R.drawable.snowflake);
            else if(ID5 == 800)
                imageDayFive.setImageResource(R.drawable.clear);
            else if(ID5 >= 801 && ID5 <= 804)
                imageDayFive.setImageResource(R.drawable.cloud);
        }
    }
}





