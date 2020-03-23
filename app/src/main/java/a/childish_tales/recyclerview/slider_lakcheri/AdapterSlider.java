package a.childish_tales.recyclerview.slider_lakcheri;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import a.childish_tales.activtiy.InfoStoryActivity;
import a.childish_tales.R;
import a.childish_tales.util.view.ColorUtil;

public class AdapterSlider extends RecyclerView.Adapter<AdapterSlider.ViewHolder>{

  private ArrayList<ItemSlider> itemIntroList;
  private LayoutInflater mInflater;
  private Context context;

  // data is passed into the constructor
  public AdapterSlider(Context context, ArrayList<ItemSlider> itemIntroList) {
    this.context = context;
    this.mInflater = LayoutInflater.from(context);
    this.itemIntroList = itemIntroList;
  }

  // inflates the row layout from xml when needed
  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = mInflater.inflate(R.layout.item_slider, parent, false);
    return new ViewHolder(view);
  }

  // binds the data to the TextView in each row
  @SuppressLint("SetTextI18n")
  @Override
  public void onBindViewHolder(ViewHolder holder, final int position) {
    ItemSlider item = itemIntroList.get(position);
//    ViewUtil.setImageResource(context,holder.imageView,item.getImage());

    Glide.with(context).load(item.getImageـurl()).into(holder.imageView);
    holder.title.setText(item.getTitle());
    holder.desc.setText(item.getDesc());
    holder.writer_narrator.setText(item.getRecorder());
    holder.time.setText(item.getTime());

    ColorUtil.setGradient(holder.box_detail,"#00000000","#333333");

    holder.view.setOnClickListener(view -> {
      Intent intent = new Intent(context, InfoStoryActivity.class);
      intent.putExtra("id",item.getId());
      intent.putExtra("title",item.getTitle());
      intent.putExtra("desc",item.getDesc());
      intent.putExtra("text",item.getText());
      intent.putExtra("imageـurl",item.getImageـurl());
      intent.putExtra("time",item.getTime());
      intent.putExtra("recorder",item.getRecorder());
      intent.putExtra("sound_name",item.getSound_name());
      intent.putExtra("sound_url",item.getSound_url());
      context.startActivity(intent);
    });
  }

  // total number of rows
  @Override
  public int getItemCount() {
    return itemIntroList.size();
  }


  // stores and recycles views as they are scrolled off screen
  public class ViewHolder extends RecyclerView.ViewHolder{
      View view,box_detail;
      ImageView imageView;
      TextView title,star,desc,text,writer_narrator,time;
    ViewHolder(View itemView) {
      super(itemView);
      view = itemView;
      imageView = itemView.findViewById(R.id.image);
      title = itemView.findViewById(R.id.title);
      time = itemView.findViewById(R.id.time);
      desc = itemView.findViewById(R.id.desc);
      writer_narrator = itemView.findViewById(R.id.writer_narrator);
      box_detail = itemView.findViewById(R.id.box_detail);
    }
  }
  boolean urlIsTrue(String url){
    if (url!=null &&
        url.length()>3 &&
        url.startsWith("http")){
      return true;
    }
    return false;
  }
}