package com.thanura.jsontoui;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.Gravity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import bsh.EvalError;
import bsh.Interpreter;

/**
 * Created by Thanura on 6/29/2017.
 */

public class Utils {

    public static Map<String,String> Mcolors = new HashMap<>();
    public static Map<String,Integer> Gravitys = new HashMap<>();
    public static Map<String,Integer> TypeFaces = new HashMap<>();

    public static void intColorsToBeanShell(Interpreter i){
        for(Map.Entry<String, String> entry : Mcolors.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            try {i.set(key, Color.parseColor(value)); } catch (EvalError evalError) {}
        }
    }
    public static void intTypeFaceToBeanShell(Interpreter i){
        for(Map.Entry<String, Integer> entry : TypeFaces.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            try {i.set("text_"+key, value); } catch (EvalError evalError) {}
        }
    }

    public static void intTypeFaces(){
        TypeFaces.put("bold", Typeface.BOLD);
        TypeFaces.put("bold-italic", Typeface.BOLD_ITALIC);
        TypeFaces.put("italic", Typeface.ITALIC);
        TypeFaces.put("normal", Typeface.NORMAL);
    }

    public static void intGravitys(){
        Gravitys.put("center", Gravity.CENTER);
        Gravitys.put("bottom", Gravity.BOTTOM);
        Gravitys.put("center_horizontal", Gravity.CENTER_HORIZONTAL);
        Gravitys.put("center_vertical", Gravity.CENTER_VERTICAL);
        Gravitys.put("top", Gravity.TOP);
        Gravitys.put("right", Gravity.RIGHT);
        Gravitys.put("left", Gravity.LEFT);
        Gravitys.put("fill", Gravity.FILL);
        Gravitys.put("fill_horizontal", Gravity.FILL_HORIZONTAL);
        Gravitys.put("fill_vertical", Gravity.FILL_VERTICAL);
        Gravitys.put("no_gravity", Gravity.NO_GRAVITY);
        Gravitys.put("start", Gravity.START);
        Gravitys.put("end", Gravity.END);
        Gravitys.put("clip_horizontal", Gravity.CLIP_HORIZONTAL);
        Gravitys.put("clip_vertical", Gravity.CLIP_VERTICAL);
    }

    public static void intColor(){
        intGravitys();
        intTypeFaces();
        Mcolors.put("red_50","#FFEBEE");
        Mcolors.put("red_100","#FFCDD2");
        Mcolors.put("red_200","#EF9A9A");
        Mcolors.put("red_300","#E57373");
        Mcolors.put("red_400","#EF5350");
        Mcolors.put("red_500","#F44336");
        Mcolors.put("red_600","#E53935");
        Mcolors.put("red_700","#D32F2F");
        Mcolors.put("red_800","#C62828");
        Mcolors.put("red_900","#B71C1C");
        Mcolors.put("red_accent_100","#FF8A80");
        Mcolors.put("red_accent_200","#FF5252");
        Mcolors.put("red_accent_400","#FF1744");
        Mcolors.put("red_accent_700","#D50000");
        Mcolors.put("pink_50","#FCE4EC");
        Mcolors.put("pink_100","#F8BBD0");
        Mcolors.put("pink_200","#F48FB1");
        Mcolors.put("pink_300","#F06292");
        Mcolors.put("pink_400","#EC407A");
        Mcolors.put("pink_500","#E91E63");
        Mcolors.put("pink_600","#D81B60");
        Mcolors.put("pink_700","#C2185B");
        Mcolors.put("pink_800","#AD1457");
        Mcolors.put("pink_900","#880E4F");
        Mcolors.put("pink_accent_100","#FF80AB");
        Mcolors.put("pink_accent_200","#FF4081");
        Mcolors.put("pink_accent_400","#F50057");
        Mcolors.put("pink_accent_700","#C51162");
        Mcolors.put("purple_50","#F3E5F5");
        Mcolors.put("purple_100","#E1BEE7");
        Mcolors.put("purple_200","#CE93D8");
        Mcolors.put("purple_300","#BA68C8");
        Mcolors.put("purple_400","#AB47BC");
        Mcolors.put("purple_500","#9C27B0");
        Mcolors.put("purple_600","#8E24AA");
        Mcolors.put("purple_700","#7B1FA2");
        Mcolors.put("purple_800","#6A1B9A");
        Mcolors.put("purple_900","#4A148C");
        Mcolors.put("purple_accent_100","#EA80FC");
        Mcolors.put("purple_accent_200","#E040FB");
        Mcolors.put("purple_accent_400","#D500F9");
        Mcolors.put("purple_accent_700","#AA00FF");
        Mcolors.put("deep_purple_50","#EDE7F6");
        Mcolors.put("deep_purple_100","#D1C4E9");
        Mcolors.put("deep_purple_200","#B39DDB");
        Mcolors.put("deep_purple_300","#9575CD");
        Mcolors.put("deep_purple_400","#7E57C2");
        Mcolors.put("deep_purple_500","#673AB7");
        Mcolors.put("deep_purple_600","#5E35B1");
        Mcolors.put("deep_purple_700","#512DA8");
        Mcolors.put("deep_purple_800","#4527A0");
        Mcolors.put("deep_purple_900","#311B92");
        Mcolors.put("deep_purple_accent_100","#B388FF");
        Mcolors.put("deep_purple_accent_200","#7C4DFF");
        Mcolors.put("deep_purple_accent_400","#651FFF");
        Mcolors.put("deep_purple_accent_700","#6200EA");
        Mcolors.put("indigo_50","#E8EAF6");
        Mcolors.put("indigo_100","#C5CAE9");
        Mcolors.put("indigo_200","#9FA8DA");
        Mcolors.put("indigo_300","#7986CB");
        Mcolors.put("indigo_400","#5C6BC0");
        Mcolors.put("indigo_500","#3F51B5");
        Mcolors.put("indigo_600","#3949AB");
        Mcolors.put("indigo_700","#303F9F");
        Mcolors.put("indigo_800","#283593");
        Mcolors.put("indigo_900","#1A237E");
        Mcolors.put("indigo_accent_100","#8C9EFF");
        Mcolors.put("indigo_accent_200","#536DFE");
        Mcolors.put("indigo_accent_400","#3D5AFE");
        Mcolors.put("indigo_accent_700","#304FFE");
        Mcolors.put("blue_50","#E3F2FD");
        Mcolors.put("blue_100","#BBDEFB");
        Mcolors.put("blue_200","#90CAF9");
        Mcolors.put("blue_300","#64B5F6");
        Mcolors.put("blue_400","#42A5F5");
        Mcolors.put("blue_500","#2196F3");
        Mcolors.put("blue_600","#1E88E5");
        Mcolors.put("blue_700","#1976D2");
        Mcolors.put("blue_800","#1565C0");
        Mcolors.put("blue_900","#0D47A1");
        Mcolors.put("blue_accent_100","#82B1FF");
        Mcolors.put("blue_accent_200","#448AFF");
        Mcolors.put("blue_accent_400","#2979FF");
        Mcolors.put("blue_accent_700","#2962FF");
        Mcolors.put("light_blue_50","#E1F5FE");
        Mcolors.put("light_blue_100","#B3E5FC");
        Mcolors.put("light_blue_200","#81D4fA");
        Mcolors.put("light_blue_300","#4fC3F7");
        Mcolors.put("light_blue_400","#29B6FC");
        Mcolors.put("light_blue_500","#03A9F4");
        Mcolors.put("light_blue_600","#039BE5");
        Mcolors.put("light_blue_700","#0288D1");
        Mcolors.put("light_blue_800","#0277BD");
        Mcolors.put("light_blue_900","#01579B");
        Mcolors.put("light_blue_accent_100","#80D8FF");
        Mcolors.put("light_blue_accent_200","#40C4FF");
        Mcolors.put("light_blue_accent_400","#00B0FF");
        Mcolors.put("light_blue_accent_700","#0091EA");
        Mcolors.put("cyan_50","#E0F7FA");
        Mcolors.put("cyan_100","#B2EBF2");
        Mcolors.put("cyan_200","#80DEEA");
        Mcolors.put("cyan_300","#4DD0E1");
        Mcolors.put("cyan_400","#26C6DA");
        Mcolors.put("cyan_500","#00BCD4");
        Mcolors.put("cyan_600","#00ACC1");
        Mcolors.put("cyan_700","#0097A7");
        Mcolors.put("cyan_800","#00838F");
        Mcolors.put("cyan_900","#006064");
        Mcolors.put("cyan_accent_100","#84FFFF");
        Mcolors.put("cyan_accent_200","#18FFFF");
        Mcolors.put("cyan_accent_400","#00E5FF");
        Mcolors.put("cyan_accent_700","#00B8D4");
        Mcolors.put("teal_50","#E0F2F1");
        Mcolors.put("teal_100","#B2DFDB");
        Mcolors.put("teal_200","#80CBC4");
        Mcolors.put("teal_300","#4DB6AC");
        Mcolors.put("teal_400","#26A69A");
        Mcolors.put("teal_500","#009688");
        Mcolors.put("teal_600","#00897B");
        Mcolors.put("teal_700","#00796B");
        Mcolors.put("teal_800","#00695C");
        Mcolors.put("teal_900","#004D40");
        Mcolors.put("teal_accent_100","#A7FFEB");
        Mcolors.put("teal_accent_200","#64FFDA");
        Mcolors.put("teal_accent_400","#1DE9B6");
        Mcolors.put("teal_accent_700","#00BFA5");
        Mcolors.put("green_50","#E8F5E9");
        Mcolors.put("green_100","#C8E6C9");
        Mcolors.put("green_200","#A5D6A7");
        Mcolors.put("green_300","#81C784");
        Mcolors.put("green_400","#66BB6A");
        Mcolors.put("green_500","#4CAF50");
        Mcolors.put("green_600","#43A047");
        Mcolors.put("green_700","#388E3C");
        Mcolors.put("green_800","#2E7D32");
        Mcolors.put("green_900","#1B5E20");
        Mcolors.put("green_accent_100","#B9F6CA");
        Mcolors.put("green_accent_200","#69F0AE");
        Mcolors.put("green_accent_400","#00E676");
        Mcolors.put("green_accent_700","#00C853");
        Mcolors.put("light_green_50","#F1F8E9");
        Mcolors.put("light_green_100","#DCEDC8");
        Mcolors.put("light_green_200","#C5E1A5");
        Mcolors.put("light_green_300","#AED581");
        Mcolors.put("light_green_400","#9CCC65");
        Mcolors.put("light_green_500","#8BC34A");
        Mcolors.put("light_green_600","#7CB342");
        Mcolors.put("light_green_700","#689F38");
        Mcolors.put("light_green_800","#558B2F");
        Mcolors.put("light_green_900","#33691E");
        Mcolors.put("light_green_accent_100","#CCFF90");
        Mcolors.put("light_green_accent_200","#B2FF59");
        Mcolors.put("light_green_accent_400","#76FF03");
        Mcolors.put("light_green_accent_700","#64DD17");
        Mcolors.put("lime_50","#F9FBE7");
        Mcolors.put("lime_100","#F0F4C3");
        Mcolors.put("lime_200","#E6EE9C");
        Mcolors.put("lime_300","#DCE775");
        Mcolors.put("lime_400","#D4E157");
        Mcolors.put("lime_500","#CDDC39");
        Mcolors.put("lime_600","#C0CA33");
        Mcolors.put("lime_700","#A4B42B");
        Mcolors.put("lime_800","#9E9D24");
        Mcolors.put("lime_900","#827717");
        Mcolors.put("lime_accent_100","#F4FF81");
        Mcolors.put("lime_accent_200","#EEFF41");
        Mcolors.put("lime_accent_400","#C6FF00");
        Mcolors.put("lime_accent_700","#AEEA00");
        Mcolors.put("yellow_50","#FFFDE7");
        Mcolors.put("yellow_100","#FFF9C4");
        Mcolors.put("yellow_200","#FFF590");
        Mcolors.put("yellow_300","#FFF176");
        Mcolors.put("yellow_400","#FFEE58");
        Mcolors.put("yellow_500","#FFEB3B");
        Mcolors.put("yellow_600","#FDD835");
        Mcolors.put("yellow_700","#FBC02D");
        Mcolors.put("yellow_800","#F9A825");
        Mcolors.put("yellow_900","#F57F17");
        Mcolors.put("yellow_accent_100","#FFFF82");
        Mcolors.put("yellow_accent_200","#FFFF00");
        Mcolors.put("yellow_accent_400","#FFEA00");
        Mcolors.put("yellow_accent_700","#FFD600");
        Mcolors.put("amber_50","#FFF8E1");
        Mcolors.put("amber_100","#FFECB3");
        Mcolors.put("amber_200","#FFE082");
        Mcolors.put("amber_300","#FFD54F");
        Mcolors.put("amber_400","#FFCA28");
        Mcolors.put("amber_500","#FFC107");
        Mcolors.put("amber_600","#FFB300");
        Mcolors.put("amber_700","#FFA000");
        Mcolors.put("amber_800","#FF8F00");
        Mcolors.put("amber_900","#FF6F00");
        Mcolors.put("amber_accent_100","#FFE57F");
        Mcolors.put("amber_accent_200","#FFD740");
        Mcolors.put("amber_accent_400","#FFC400");
        Mcolors.put("amber_accent_700","#FFAB00");
        Mcolors.put("orange_50","#FFF3E0");
        Mcolors.put("orange_100","#FFE0B2");
        Mcolors.put("orange_200","#FFCC80");
        Mcolors.put("orange_300","#FFB74D");
        Mcolors.put("orange_400","#FFA726");
        Mcolors.put("orange_500","#FF9800");
        Mcolors.put("orange_600","#FB8C00");
        Mcolors.put("orange_700","#F57C00");
        Mcolors.put("orange_800","#EF6C00");
        Mcolors.put("orange_900","#E65100");
        Mcolors.put("orange_accent_100","#FFD180");
        Mcolors.put("orange_accent_200","#FFAB40");
        Mcolors.put("orange_accent_400","#FF9100");
        Mcolors.put("orange_accent_700","#FF6D00");
        Mcolors.put("deep_orange_50","#FBE9A7");
        Mcolors.put("deep_orange_100","#FFCCBC");
        Mcolors.put("deep_orange_200","#FFAB91");
        Mcolors.put("deep_orange_300","#FF8A65");
        Mcolors.put("deep_orange_400","#FF7043");
        Mcolors.put("deep_orange_500","#FF5722");
        Mcolors.put("deep_orange_600","#F4511E");
        Mcolors.put("deep_orange_700","#E64A19");
        Mcolors.put("deep_orange_800","#D84315");
        Mcolors.put("deep_orange_900","#BF360C");
        Mcolors.put("deep_orange_accent_100","#FF9E80");
        Mcolors.put("deep_orange_accent_200","#FF6E40");
        Mcolors.put("deep_orange_accent_400","#FF3D00");
        Mcolors.put("deep_orange_accent_700","#DD2600");
        Mcolors.put("brown_50","#EFEBE9");
        Mcolors.put("brown_100","#D7CCC8");
        Mcolors.put("brown_200","#BCAAA4");
        Mcolors.put("brown_300","#A1887F");
        Mcolors.put("brown_400","#8D6E63");
        Mcolors.put("brown_500","#795548");
        Mcolors.put("brown_600","#6D4C41");
        Mcolors.put("brown_700","#5D4037");
        Mcolors.put("brown_800","#4E342E");
        Mcolors.put("brown_900","#3E2723");
        Mcolors.put("grey_50","#FAFAFA");
        Mcolors.put("grey_100","#F5F5F5");
        Mcolors.put("grey_200","#EEEEEE");
        Mcolors.put("grey_300","#E0E0E0");
        Mcolors.put("grey_400","#BDBDBD");
        Mcolors.put("grey_500","#9E9E9E");
        Mcolors.put("grey_600","#757575");
        Mcolors.put("grey_700","#616161");
        Mcolors.put("grey_800","#424242");
        Mcolors.put("grey_900","#212121");
        Mcolors.put("black_1000","#000000");
        Mcolors.put("white_1000","#ffffff");
        Mcolors.put("blue_grey_50","#ECEFF1");
        Mcolors.put("blue_grey_100","#CFD8DC");
        Mcolors.put("blue_grey_200","#B0BBC5");
        Mcolors.put("blue_grey_300","#90A4AE");
        Mcolors.put("blue_grey_400","#78909C");
        Mcolors.put("blue_grey_500","#607D8B");
        Mcolors.put("blue_grey_600","#546E7A");
        Mcolors.put("blue_grey_700","#455A64");
        Mcolors.put("blue_grey_800","#37474F");
        Mcolors.put("blue_grey_900","#263238");
    }

    public static float dipToPixels(Context context, float dipValue){
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, context.getResources().getDisplayMetrics());
    }

    public static Map<String, Object> jsonToMap(JSONObject json) throws JSONException {
        Map<String, Object> retMap = new HashMap<String, Object>();

        if(json != JSONObject.NULL) {
            retMap = toMap(json);
        }
        return retMap;
    }

    public static Map<String, Object> toMap(JSONObject object) throws JSONException {
        Map<String, Object> map = new HashMap<>();

        Iterator<String> keysItr = object.keys();
        while(keysItr.hasNext()) {
            String key = keysItr.next();
            Object value = object.get(key);

            if(value instanceof JSONArray) {value = toList((JSONArray) value);
            }

            else if(value instanceof JSONObject) {value = toMap((JSONObject) value);
            }
            map.put(key, value);
        }
        return map;
    }

    public static List<Object> toList(JSONArray array) throws JSONException {
        List<Object> list = new ArrayList<Object>();
        for(int i = 0; i < array.length(); i++) {
            Object value = array.get(i);
            if(value instanceof JSONArray) {value = toList((JSONArray) value);
            }

            else if(value instanceof JSONObject) {value = toMap((JSONObject) value);
            }
            list.add(value);
        }
        return list;
    }
}
