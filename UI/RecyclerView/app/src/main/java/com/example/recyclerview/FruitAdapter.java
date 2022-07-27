package com.example.recyclerview;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {

    private List<Fruit> mFruitList;

    // 内部类
    static class ViewHolder extends RecyclerView.ViewHolder {

        View fruitView;   // 为了设置事件

        ImageView fruitImage;
        TextView fruitName;

        public ViewHolder(View view) {   //  一般传入外层布局
            super(view);

            fruitView = view;  //  为了设置事件

            fruitImage = (ImageView) view.findViewById(R.id.fruit_image);
            fruitName = (TextView) view.findViewById(R.id.fruit_name);
        }
    }

    // 构造方法   把要操作的数据传过来  并赋值给一个新的变量 后续工作在此基础上进行操作
    public FruitAdapter(List<Fruit> fruitList) {
        mFruitList = fruitList;
    }

    @NonNull
    @Override

    //  用于建立 ViewHolder 实例
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fruit_item,parent,false); // 加载布局

        final ViewHolder holder = new ViewHolder(view);      // 创建实例
        holder.fruitView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Fruit fruit = mFruitList.get(position);
                Toast.makeText(view.getContext(), "you clicked view" + fruit.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        holder.fruitImage.setOnClickListener(view1 -> {
             int position = holder.getAdapterPosition();
             Fruit fruit = mFruitList.get(position);
            Toast.makeText(view.getContext(), "you click image" + fruit.getName(), Toast.LENGTH_SHORT).show();
        });

        return holder;  // 返回实例
    }

    @Override

    // 对实例对象进行赋值
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {  // position 当前加载的实例
        Fruit fruit = mFruitList.get(position);
        holder.fruitImage.setImageResource(fruit.getImageId());  // 设置数据
        holder.fruitName.setText(fruit.getName());    // 设置数据
    }

    @Override
    //  告诉RecyclerView 一共有多少子项
    public int getItemCount() {
        return mFruitList.size();
    }
}
