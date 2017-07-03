package com.thanura.jsontoui;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import bsh.Interpreter;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference BasicInfo;
    DatabaseReference UI;
    DatabaseReference Script;
    String UIscript;
    LinearLayout layout;
    Interpreter interpreter = new Interpreter();

    private void runString(String code){
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        System.setErr(ps);
        try {
            interpreter.eval(code);
        }catch (Exception e){//handle exception
            e.printStackTrace();
        }
        String output = os.toString();
        if(output!=null && output.length()>0)
            InformMe(output);
    }

    public void InformMe(String error){
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setTitle("Script Error")
                .setMessage(error)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public String getRealData(DataSnapshot ds,String key){
        return (ds.child(key).getValue()==null)?null:ds.child(key).getValue().toString();
    }
    public String getRealData(DataSnapshot ds,String key,String Default){
        return (ds.child(key).getValue()==null)?Default:ds.child(key).getValue().toString();
    }
    public int getRealData(DataSnapshot ds,String key,int Default){
        return (ds.child(key).getValue()==null)?Default:Integer.parseInt(ds.child(key).getValue().toString());
    }

    public int getRealColor(DataSnapshot ds,String key,int Default){
        String RGB = String.format("#%06X", (0xFFFFFF & Default));
        try{
            if(getRealData(ds,key,RGB).startsWith("#"))
                return Color.parseColor(getRealData(ds,key,RGB));
            else
                return Color.parseColor(Utils.Mcolors.get(getRealData(ds,key,RGB)));
        }catch (Exception e) {
            Toast.makeText(MainActivity.this,key+" is Invalid",Toast.LENGTH_LONG).show();
            return Default;
        }
    }

    public int[] paddingCorrectWay(String padding){
        int data[] = new int[4];
        if(padding.split(" ").length >1){
            for (int x=0;x<4 ;x++) {
                data[x]= Integer.parseInt(padding.split(" ")[x]);
            }
        }else{
            for (int x=0;x<4 ;x++) {
                data[x]= Integer.parseInt(padding);
            }
        }
        return data;
    }

    public void addNewButton(ViewGroup v,DataSnapshot ds){
        Button Compnent = new Button(this);
        Compnent.setText(getRealData(ds,"lable"));
        String Width = getRealData(ds,"width");
        String Height = getRealData(ds,"height");
        String onClick = getRealData(ds,"onclick");
        Compnent.setTextColor(getRealColor(ds,"text-color",Compnent.getTextColors().getDefaultColor()));
        Compnent.setTextSize(TypedValue.COMPLEX_UNIT_PX,Float.parseFloat(getRealData(ds,"text-size",Compnent.getTextSize()+"")));
        v.addView(Compnent);
        setPaddingsMargins(Compnent,ds);
        if (Width!=null) Compnent.setWidth(Integer.parseInt(Width));
        if (Height!=null) Compnent.setHeight(Integer.parseInt(Height));
        Compnent.setTypeface(Compnent.getTypeface(),Utils.TypeFaces.get(getRealData(ds,"text-style","normal")));
        Compnent.setGravity(Utils.Gravitys.get(getRealData(ds,"gravity","center")));
        if(onClick !=null)
            Compnent.setOnClickListener(new _onClick(onClick) {
                @Override
                public void onClick(View view) {
                    runString(this.script);
                }
            });
    }

    public void loadLayout(final String UIName,final ViewGroup v){
        UI.child(UIName).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String title = getRealData(dataSnapshot,"title");
                String Orientation = getRealData(dataSnapshot,"orientation","vertical");
                UIscript = getRealData(dataSnapshot,"script");
                Spannable text = new SpannableString(title);
                layout.setOrientation(Orientation.equalsIgnoreCase("horizontal")?LinearLayout.HORIZONTAL:LinearLayout.VERTICAL);
                if (getRealData(dataSnapshot,"title-color") != null)
                    text.setSpan(new ForegroundColorSpan(getRealColor(dataSnapshot,"title-color",0)), 0, text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
                getSupportActionBar().setTitle(text);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    if (getRealData(dataSnapshot,"statusbar-color") != null)
                        window.setStatusBarColor(getRealColor(dataSnapshot,"statusbar-color",0));
                    if (getRealData(dataSnapshot,"actionbar-color") != null)
                        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getRealColor(dataSnapshot,"actionbar-color",0)));
                }
                if (getRealData(dataSnapshot,"bg-color") != null)
                    layout.setBackgroundColor(getRealColor(dataSnapshot,"bg-color",0));
                addComponets(dataSnapshot,v);
                if(UIscript != null) loadScript(UIscript);
                if(UIscript != null) runString("onCreate();");
                UI.child(UIName).addValueEventListener(new ValueEventListener() {
                    private boolean FirstTime = true;
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(FirstTime){
                            FirstTime = false;
                            return;
                        }
                        Toast.makeText(MainActivity.this,"Hotfix Pached,Restart Requested",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(MainActivity.this,databaseError.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(MainActivity.this,databaseError.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    private void addComponets(DataSnapshot dataSnapshot,ViewGroup v){
        for(DataSnapshot child : dataSnapshot.child("view").getChildren() ){
            String viewtype = getRealData(child,"viewtype");
            if(viewtype.equalsIgnoreCase("button"))
                addNewButton(v,child);
            if(viewtype.equalsIgnoreCase("textview"))
                addNewTextView(v,child);
            if(viewtype.equalsIgnoreCase("layout"))
                addNewLayout(v,child);

        }
    }

    private void setPaddingsMargins(View Compnent, DataSnapshot ds){
        String BackGroundColor = getRealData(ds,"bg-color");
        String Padding = getRealData(ds,"padding");
        String Margin = getRealData(ds,"margin");
        String ID = getRealData(ds,"id");
        int PaddingTop = getRealData(ds,"padding-top",Compnent.getPaddingTop());
        int PaddingBottom = getRealData(ds,"padding-bottom",Compnent.getPaddingBottom());
        int PaddingLeft = getRealData(ds,"padding-left",Compnent.getPaddingLeft());
        int PaddingRight = getRealData(ds,"margin-right",Compnent.getPaddingRight());
        int MarginTop = getRealData(ds,"margin-top",Compnent.getPaddingTop());
        int MarginBottom = getRealData(ds,"margin-bottom",Compnent.getPaddingBottom());
        int MarginLeft = getRealData(ds,"margin-left",Compnent.getPaddingLeft());
        int MarginRight = getRealData(ds,"margin-right",Compnent.getPaddingRight());
        int WidthMatchParent = getRealData(ds,"width-matchparent")==null? LayoutParams.WRAP_CONTENT:LayoutParams.MATCH_PARENT ;
        int HeightMatchParent = getRealData(ds,"height-matchparent")==null? LayoutParams.WRAP_CONTENT:LayoutParams.MATCH_PARENT ;
        if(Padding != null){
            int[] Paddings = paddingCorrectWay(Padding);
            PaddingLeft  = Paddings[0];
            PaddingTop  = Paddings[1];
            PaddingRight  = Paddings[2];
            PaddingBottom  = Paddings[3];
        }
        if(Margin != null){
            int[] Margins = paddingCorrectWay(Margin);
            MarginLeft  = Margins[0];
            MarginTop  = Margins[1];
            MarginRight  = Margins[2];
            MarginBottom  = Margins[3];
        }
        LinearLayout.LayoutParams ll = new LinearLayout.LayoutParams(WidthMatchParent,HeightMatchParent);
        ll.setMargins(MarginLeft,MarginTop,MarginRight,MarginBottom);
        if(BackGroundColor != null) Compnent.setBackgroundColor(getRealColor(ds,"bg-color",0));
        Compnent.setLayoutParams(ll);
        Compnent.setPadding(PaddingLeft,PaddingTop,PaddingRight,PaddingBottom);
        try {interpreter.set((ID!=null)?ID:ds.getKey(), Compnent);}catch (Exception e){}
    }

    private void addNewLayout(ViewGroup v, DataSnapshot ds) {
        LinearLayout Compnent = new LinearLayout(this);
        String Orientation = getRealData(ds,"orientation","vertical");
        Compnent.setOrientation(Orientation.equalsIgnoreCase("horizontal")?LinearLayout.HORIZONTAL:LinearLayout.VERTICAL);
        v.addView(Compnent);
        addComponets(ds,Compnent);
        setPaddingsMargins(Compnent,ds);
        Compnent.setGravity(Utils.Gravitys.get(getRealData(ds,"gravity","center")));
    }

    private void addNewTextView(ViewGroup v, DataSnapshot ds) {
        TextView Compnent = new TextView(this);
        Compnent.setText(getRealData(ds,"lable"));
        String Width = getRealData(ds,"width");
        String Height = getRealData(ds,"height");
        Compnent.setTextColor(getRealColor(ds,"text-color",Compnent.getTextColors().getDefaultColor()));
        Compnent.setTextSize(TypedValue.COMPLEX_UNIT_PX,Float.parseFloat(getRealData(ds,"text-size",Compnent.getTextSize()+"")));
        v.addView(Compnent);
        setPaddingsMargins(Compnent,ds);
        if (Width!=null) Compnent.setWidth(Integer.parseInt(Width));
        if (Height!=null) Compnent.setHeight(Integer.parseInt(Height));
        Compnent.setTypeface(Compnent.getTypeface(),Utils.TypeFaces.get(getRealData(ds,"text-style","normal")));
        Compnent.setGravity(Utils.Gravitys.get(getRealData(ds,"gravity","center")));
    }

    public void loadScript(final String ScriptName){
        Script.child(ScriptName).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                runString(dataSnapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {interpreter.set("context", this);}catch (Exception e){}
        try {FirebaseDatabase.getInstance().setPersistenceEnabled(true);}catch (Exception e){}
        layout = (LinearLayout) findViewById(R.id.Space);
        database = FirebaseDatabase.getInstance();
        Utils.intColor();
        Utils.intColorsToBeanShell(interpreter);
        Utils.intTypeFaceToBeanShell(interpreter);
        BasicInfo = database.getReference("BasicInfo");
        UI = database.getReference("UI");
        Script = database.getReference("Script");
        BasicInfo.keepSynced(true);
        UI.keepSynced(true);
        BasicInfo.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.child("MainUI").getValue()==null) return;
                final String MainUI = dataSnapshot.child("MainUI").getValue().toString();
                loadLayout(MainUI,layout);
                BasicInfo.addValueEventListener(new ValueEventListener() {
                    private boolean FirstTime = true;
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(FirstTime){
                            FirstTime = false;
                            return;
                        }
                        Toast.makeText(MainActivity.this,"Hotfix Pached,Restart Requested",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(MainActivity.this,databaseError.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(MainActivity.this,databaseError.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

    }
}
