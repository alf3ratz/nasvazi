package com.ru.alferatz.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Glide.init
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.ru.alferatz.R
import com.ru.alferatz.databinding.ItemContainerSliderImageBinding
import com.ru.alferatz.listOfInfoLocalImages

class ImageSliderAdapter(sliderImages: ArrayList<String>, context_: Context) :
    RecyclerView.Adapter<ImageSliderAdapter.ImageSliderViewHolder>() {
    private var sliderImages: ArrayList<String>? = null
    private var layoutInflater: LayoutInflater? = null
    private var context = context_

    init {
        this.sliderImages = sliderImages
    }

    inner class ImageSliderViewHolder(itemContainerSliderImageBinding: ItemContainerSliderImageBinding) :
        RecyclerView.ViewHolder(itemContainerSliderImageBinding.root) {
        private var itemContainerSliderImageBinding: ItemContainerSliderImageBinding? = null

        init {
            this.itemContainerSliderImageBinding = itemContainerSliderImageBinding
        }

        fun bindSliderImage(pictureId:Int) {
            Glide.with(context).load(ContextCompat.getDrawable(context, pictureId))
                //.optionalFitCenter() // scale image to fill the entire ImageView
                .transform(RoundedCorners(25))
                .into(itemContainerSliderImageBinding!!.img)
            //itemContainerSliderImageBinding?.imageUrl = imageUrl
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageSliderViewHolder {
        if (layoutInflater == null)
            layoutInflater = LayoutInflater.from(parent.context)
        val sliderImageBinding: ItemContainerSliderImageBinding = DataBindingUtil.inflate(
            layoutInflater!!, R.layout.item_container_slider_image, parent, false
        )
        return ImageSliderViewHolder(sliderImageBinding)
    }

    override fun onBindViewHolder(holder: ImageSliderViewHolder, position: Int) {
        holder.bindSliderImage(listOfInfoLocalImages[position])
//        sliderImages?.get(position)
//            ?.let { holder.bindSliderImage(it) }

    }

    override fun getItemCount(): Int {
        return listOfInfoLocalImages.size
    }
}