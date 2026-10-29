package com.example.practicalfive;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.ActivityNotFoundException;
import android.net.Uri;
import android.widget.Toast;

public class Municipality extends AppCompatActivity {

    private String selectedMunicipality;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_municipality);

        TextView nameView = findViewById(R.id.municipalityName);
        ImageView imageView = findViewById(R.id.municipalityImage);
        TextView infoView = findViewById(R.id.municipalityInfo);
        Button visitButton = findViewById(R.id.visitSiteButton);

        TextProcessor processor = new TextProcessor();
        selectedMunicipality = processor.processMunicipality(
                getIntent().getStringExtra("municipality_name"));

        if (selectedMunicipality == null) {
            finish();
            return;
        }

        String[] information =
                getResources().getStringArray(R.array.municipalities_array);

        int imageRes;
        int colorRes;
        int infoIndex;

        switch (selectedMunicipality) {
            case "Beijing":
                imageRes = R.drawable.beijing;
                colorRes = R.color.municipality_beijing;
                infoIndex = 0;
                break;

            case "Shanghai":
                imageRes = R.drawable.shanghai;
                colorRes = R.color.municipality_shanghai;
                infoIndex = 1;
                break;

            case "Chongqing":
                imageRes = R.drawable.chongqing;
                colorRes = R.color.municipality_chongqing;
                infoIndex = 2;
                break;

            case "Tianjin":
                imageRes = R.drawable.tianjin;
                colorRes = R.color.municipality_tianjin;
                infoIndex = 3;
                break;

            default:
                finish();
                return;
        }

        int color = ContextCompat.getColor(this, colorRes);

        nameView.setText(selectedMunicipality);
        nameView.setBackgroundColor(color);
        imageView.setImageResource(imageRes);
        infoView.setText(information[infoIndex]);
        infoView.setTextColor(color);
        visitButton.setBackgroundTintList(
                ContextCompat.getColorStateList(this, colorRes));
        visitButton.setOnClickListener(v -> {
            String url;

            switch (selectedMunicipality) {
                case "Beijing":
                    url = "https://english.beijing.gov.cn/";
                    break;
                case "Shanghai":
                    url = "https://english.shanghai.gov.cn/";
                    break;
                case "Tianjin":
                    url = "https://en.tj.gov.cn/";
                    break;
                case "Chongqing":
                    url = "https://english.cq.gov.cn/";
                    break;
                default:
                    return;
            }

            Intent intent = new Intent(
                    Intent.ACTION_VIEW, Uri.parse(url));

            try {
                startActivity(intent);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(this,
                        "No browser available",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}