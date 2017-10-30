package com.example.administrator.android_lab3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/10/26.
 */

public class InformationActivity extends AppCompatActivity {
    ImageButton back;//返回按钮
    ImageButton star;//收藏按钮
    ImageButton buy;//购买按钮
    TextView name;//商品名
    TextView price;//价格
    TextView type;//附加信息类型
    TextView information;//附加信息
    ImageView image;//商品图片
    ListView option;//下面的更多操作
//    ArrayAdapter<String> arrayAdapter;
    int buyNum;//购买次数

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imformation);
        initial();

        final Goods goods = (Goods) getIntent().getSerializableExtra("goods");
        name.setText(goods.name);
        image.setImageResource(goods.id);
        price.setText(goods.price);
        type.setText(goods.type);
        information.setText(goods.infomation);
        if(goods.haveCollected) star.setImageResource(R.mipmap.full_star);
        else star.setImageResource(R.mipmap.empty_star);

        final String [] imfo = {"    一键下单","    分享商品","    不感兴趣","    查看更多商品促销信息"};
        option.setAdapter(new ArrayAdapter<>(InformationActivity.this, R.layout.oper, imfo));

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("buyNum",buyNum);
                intent.putExtra("haveCollected",goods.haveCollected);
                setResult(1,intent);
                finish();
            }
        });
        star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goods.haveCollected = !goods.haveCollected;
                if(goods.haveCollected) star.setImageResource(R.mipmap.full_star);
                else star.setImageResource(R.mipmap.empty_star);
            }
        });

        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buyNum++;
                Toast.makeText(InformationActivity.this,"商品已添加到购物车",Toast.LENGTH_SHORT).show();
            }
        });

    }

    void initial(){
        back = (ImageButton) findViewById(R.id.back);
        star = (ImageButton) findViewById(R.id.star);
        name = (TextView) findViewById(R.id.good_name);
        image = (ImageView) findViewById(R.id.good_image);
        buy = (ImageButton) findViewById(R.id.shopimage);
        price = (TextView) findViewById(R.id.price);
        type = (TextView) findViewById(R.id.type);
        information = (TextView) findViewById(R.id.information);
        option = (ListView) findViewById(R.id.option);
        buyNum = 0;

    }

}
