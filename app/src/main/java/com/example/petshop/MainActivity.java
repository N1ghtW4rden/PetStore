package com.example.petshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void petInfo(View v)
    {
        TextView name = findViewById(R.id.textView1);
        EditText etid = findViewById(R.id.petID);
        ImageView image = findViewById(R.id.imageView);
        String path = String.valueOf(etid.getText());
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://petstore.swagger.io/v2/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<Pet> call = jsonPlaceHolderApi.getPet(path);

        call.enqueue(new Callback<Pet>() {
            @Override
            public void onResponse(Call<Pet> call, Response<Pet> response) {
                if (!response.isSuccessful()) {
                    name.setText(String.valueOf(response.code()));
                    return;
                }

                Pet pet = response.body();
                Category category = pet.getCategory();
                List<Tag> tagList = pet.getTags();
                Tag tag = tagList.get(0);
                String content = "";
                content += "name: " + String.valueOf(pet.getName())
                        + "\nstatus: " + String.valueOf(pet.getStatus())
                        + "\ncategory: " + String.valueOf(category.getName())
                        + "\ncolor: " + String.valueOf(tag.getName());

                name.setText(content);

                List<String> photoURLS = pet.getPhotoUrls();
                String photoURL = photoURLS.get(0);
                Picasso.get().load(photoURL).into(image);
            }

            @Override
            public void onFailure(Call<Pet> call, Throwable t) {
                name.setText("errror" + t.getMessage());
            }
        });

    }

    public void goPost(View v)
    {
        Intent intent = new Intent(this, PostActivity.class);
        startActivity(intent);
        finish();
    }

    public void goMenu(View v)
    {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
        finish();
    }
}