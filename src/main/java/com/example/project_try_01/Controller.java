package com.example.project_try_01;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.json.JSONObject;


public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField city;

    @FXML
    private Text feels_like;

    @FXML
    private Button getData;

    @FXML
    private Text pressure;

    @FXML
    private Text temp_info;

    @FXML
    private Text temp_max;

    @FXML
    private Text temp_min;

    @FXML
    void initialize() {
        getData.setOnAction(event -> {
            String getUserCity = city.getText().trim();
            if (!getUserCity.equals("")) {
                String output = getUrlContent("http://api.openweathermap.org/data/2.5/weather?q=" + getUserCity + "&appid=8639453c103bbbb37002a50744b5703d&units=metric");

                if (!output.isEmpty()) {
                    JSONObject obj = new JSONObject(output);

                    if (obj.has("main")) {
                        JSONObject main = obj.getJSONObject("main");

                        temp_info.setText("Температура: " + main.getDouble("temp"));
                        feels_like.setText("Ощущается как: " + main.getDouble("feels_like"));
                        temp_min.setText("Минимум: " + main.getDouble("temp_min"));
                        temp_max.setText("Максимум: " + main.getDouble("temp_max"));
                        pressure.setText("Давление: " + main.getDouble("pressure"));
                    }
                }
            }
        });
    }
    private static String getUrlContent(String urlAdress) {
        StringBuffer content = new StringBuffer();
        try {
            URL url = new URL(urlAdress);
            URLConnection urlConn = url.openConnection();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println("City have not found!");

        }
        return content.toString();
    }
}
