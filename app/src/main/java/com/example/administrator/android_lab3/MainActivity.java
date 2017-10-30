package com.example.administrator.android_lab3;


import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteBindOrColumnIndexOutOfRangeException;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import it.gmariotti.recyclerview.itemanimator.SlideInOutLeftItemAnimator;
import it.gmariotti.recyclerview.itemanimator.SlideInOutRightItemAnimator;
import it.gmariotti.recyclerview.itemanimator.SlideInOutTopItemAnimator;


public class MainActivity extends AppCompatActivity {

    private RecyclerView shopping_view;//所有商品的购物列表视图
    private ListView car_view;//购物车视图
    RecyclerAdapter recyclerAdapter;
    ListAdapter listAdapter;
    List<Goods> shoppingList;//购物时的列表
    List<Goods> carList;//购物车中的列表
    Goods chosedGood;//被选中的货物
    int chosedPosition;//选中的位置
    FloatingActionButton FloatButton;//浮动的界面切换按钮
    int buyNum;//购买某个商品的数量



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main);

        initial();
        shopping_view.setLayoutManager(new LinearLayoutManager(this));
        shopping_view.setAdapter(recyclerAdapter);
        car_view.setAdapter(listAdapter);
        car_view.setVisibility(View.GONE);
        shopping_view.setVisibility(View.VISIBLE);

        recyclerAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener(){
            @Override
            public void onClick(int pos){
                Intent intent = new Intent(MainActivity.this, InformationActivity.class);
                chosedGood = shoppingList.get(pos);
                chosedPosition = pos;
                intent.putExtra("goods",chosedGood);
                startActivityForResult(intent,2);
            }
            @Override
            public  void onLongClick(int pos){
                recyclerAdapter.remove(pos);
                shopping_view.setItemAnimator(new SlideInOutLeftItemAnimator(shopping_view));
                String temp = "移除第";
                Toast.makeText(MainActivity.this, temp.concat(String.valueOf(pos+1)).concat("个商品"),Toast.LENGTH_SHORT)
                        .show();
            }
        });

        car_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                Intent intent = new Intent(MainActivity.this, InformationActivity.class);
                if (pos != 0){
                    chosedGood = carList.get(pos);
//                    chosedPosition = pos;
                    intent.putExtra("goods",chosedGood);
                    startActivityForResult(intent,2);
                }
            }
        });

        car_view.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int pos, long id) {
                if (pos != 0)
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("移除商品")
                            .setMessage("从购物车移除"+carList.get(pos).name)
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    carList.remove(pos);
                                    listAdapter.notifyDataSetChanged();//移除后更新视觉的列表。
                                }
                            })
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            })
                            .create()
                            .show();
                }
                return true;
            }
        });

        FloatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(shopping_view.getVisibility() == View.GONE)
                {
                    car_view.setVisibility(View.GONE);
                    shopping_view.setVisibility(View.VISIBLE);
                    FloatButton.setImageResource(R.mipmap.shoplist);
                }
                else
                {
                    shopping_view.setVisibility(View.GONE);
                    car_view.setVisibility(View.VISIBLE);
                    FloatButton.setImageResource(R.mipmap.mainpage);
                }
            }
        });

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (requestCode == 2 && resultCode == 1){
            buyNum = intent.getExtras().getInt("buyNum");
            for(int i = 0; i < buyNum; i++)
                carList.add(chosedGood);
            shoppingList.get(chosedPosition).haveCollected = intent.getExtras().getBoolean("haveCollected");
            listAdapter.notifyDataSetChanged();
        }
    }

    public void initial(){
        shoppingList = new ArrayList<>();
        carList = new ArrayList<>();
        shoppingList.add(new Goods("Enchated Forest",   "¥ 5.00",    "作者",  "Johanna Basford",R.mipmap.enchatedforest));
        shoppingList.add(new Goods("Arla Milk",         "¥ 59.00",   "产地",  "德国",R.mipmap.arla));
        shoppingList.add(new Goods("Devondale Milk",    "¥ 79.00",   "产地",  "澳大利亚",R.mipmap.devondale));
        shoppingList.add(new Goods("Kindle Oasis",      "¥ 2399.00", "版本",  "8GB",R.mipmap.kindle));
        shoppingList.add(new Goods("waitrose 早餐麦片",  "¥ 179.00",  "重量",  "2kg",R.mipmap.waitrose));
        shoppingList.add(new Goods("Mcvitie's 饼干",     "¥ 14.90",   "产地",  "英国",R.mipmap.mcvitie));
        shoppingList.add(new Goods("Ferrero Rocher",    "¥ 132.59",  "重量",  "300g",R.mipmap.ferrero));
        shoppingList.add(new Goods("Maltesers",         "¥ 141.43",  "重量",  "118g",R.mipmap.maltesers));
        shoppingList.add(new Goods("Lindt",             "¥ 5.00",    "重量",  "249g",R.mipmap.lindt));
        shoppingList.add(new Goods("Borggreve",         "¥ 28.90",   "重量",  "640g",R.mipmap.borggreve));
        carList.add(new Goods("购物车", "价格", "类型", "产品信息",R.mipmap.shoplist));

        shopping_view = (RecyclerView) findViewById(R.id.shopping_view);
        car_view = (ListView) findViewById(R.id.car_view);
        recyclerAdapter = new RecyclerAdapter(MainActivity.this, R.layout.item,shoppingList);
        listAdapter = new ListAdapter(MainActivity.this, carList);
        FloatButton = (FloatingActionButton)findViewById(R.id.shopcar);
    }


}