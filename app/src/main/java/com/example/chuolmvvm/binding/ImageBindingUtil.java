package com.example.chuolmvvm.binding;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.chuolmvvm.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class ImageBindingUtil {

    @BindingAdapter("url")
    public static void setImageUrl(CircleImageView imageView, String url) {
        Glide.with(imageView)
                .load(url)
                .apply(new RequestOptions()
                        .placeholder(R.drawable.user_logo))
                .into(imageView);
    }
}
