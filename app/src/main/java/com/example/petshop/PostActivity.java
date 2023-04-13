package com.example.petshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostActivity extends AppCompatActivity
{
    List<String> photoURLS;
    List<Tag> tagList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
    }

    public void goBack(View v)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void petPost(View v)
    {
        EditText etid = findViewById(R.id.postETID);
        EditText etcolor = findViewById(R.id.postETColor);
        EditText etstatus = findViewById(R.id.postETStatus);
        EditText etname = findViewById(R.id.postETName);
        EditText etpic = findViewById(R.id.postETPic);
        EditText etcategory = findViewById(R.id.postETCategory);
        TextView errorTV = findViewById(R.id.errorTV);
        String path = String.valueOf(etid.getText());
        String name = String.valueOf(etname.getText());
        String status = String.valueOf(etstatus.getText());
        String picURL = String.valueOf(etpic.getText());
        String color = String.valueOf(etcolor.getText());
        String categoryText = String.valueOf(etcategory.getText());
        int id = Integer.parseInt(path);



        Pet pet = new Pet();
        Category category = new Category();
        tagList = new ArrayList<Tag>();

        photoURLS = new ArrayList<String>();

        Tag tag = new Tag();
        tag.setName(color);
        tagList.add(tag);

        photoURLS.add(picURL);

        category.setName(categoryText);

        pet.setCategory(category);
        pet.setTags(tagList);
        pet.setName(name);
        pet.setPhotoUrls(photoURLS);
        pet.setId(id);
        pet.setStatus(status);

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://petstore.swagger.io/v2/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<Void> call = jsonPlaceHolderApi.postPet(pet);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response)
            {
                if (!response.isSuccessful())
                {
                    errorTV.setText(String.valueOf(response.code()));
                    return;
                }

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t)
            {
                errorTV.setText("errror" + t.getMessage());
            }
        });
    }

    public void goMenu(View v)
    {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
        finish();
    }

}