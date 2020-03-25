package a.childish_tales.activtiy;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

import a.childish_tales.R;
import a.childish_tales.manager.SaveManager;
import a.childish_tales.recyclerview.multi.MultiAdaptor;
import a.childish_tales.recyclerview.multi.MultiItem;
import a.childish_tales.util.file.FileUtil;

public class MainActivity extends AppCompatActivity {
    private String TAG = "amnigoli-MainActivity";

    ArrayList<MultiItem> itemIntroList;
    RecyclerView mRecyclerView;
    MultiAdaptor mAdapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onStart() {
        super.onStart();
        onWindowFocusChanged(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemIntroList = new ArrayList<>();
        mRecyclerView = findViewById(R.id.recycler_view);
        mAdapter = new MultiAdaptor(itemIntroList,this);
        mRecyclerView.setAdapter(mAdapter);

//        addItemFromJson();
        String image = "https://github.com/amingoli78/childish-tales/raw/master/image/creation.png";
        String title = "حضرت مهدی (عج)";

        JSON_SETER();

        /*itemIntroList.add(IMAGE(image,title));
        itemIntroList.add(IMAGE(image));
        itemIntroList.add(TITLE(title));
        additem_lakcheri();
        itemIntroList.add(TITLE(title));
        additem_h();
        itemIntroList.add(TITLE(title));
        additem_v();*/

        layoutManager= new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    MultiItem IMAGE(String image,String title){
        MultiItem item = new MultiItem();
        item.setType(MultiItem.IMAGE);
        item.setImage(image);
        item.setTitle(title);
        return item;
    }
    MultiItem IMAGE(String image){
        MultiItem item = new MultiItem();
        item.setType(MultiItem.IMAGE);
        item.setImage(image);
        item.setTitle(null);
        return item;
    }

    MultiItem TITLE(String title, Boolean showFlesh){
        MultiItem item = new MultiItem();
        item.setType(MultiItem.TITLE);
        item.setTitle(title);
        item.setBoolean(showFlesh);
        return item;
    }
    MultiItem TITLE(String title){
        MultiItem item = new MultiItem();
        item.setType(MultiItem.TITLE);
        item.setTitle(title);
        item.setBoolean(false);
        return item;
    }

    MultiItem SLIDER_LAKCHERI(ArrayList<MultiItem> arrayList){
        MultiItem item = new MultiItem();
        item.setType(MultiItem.SLIDER_LAKCHERI);
        item.setArrayList(arrayList);
        return item;
    }

    MultiItem SLIDER_HORIZONTAL(ArrayList<MultiItem> arrayList){
        MultiItem item = new MultiItem();
        item.setType(MultiItem.SLIDER_HORIZONTAL);
        item.setArrayList(arrayList);
        return item;
    }

    MultiItem SLIDER_VERTICAL(ArrayList<MultiItem> arrayList){
        MultiItem item = new MultiItem();
        item.setType(MultiItem.SLIDER_VERTICAL);
        item.setArrayList(arrayList);
        return item;
    }

    MultiItem ADD_STORY_ITEM(String title,String desc,String image,String soundName,String sound){
        MultiItem item = new MultiItem();
        item.setStory_title(title);
        item.setStory_desc(desc);
        item.setStory_image(image);
        item.setStory_soundName(soundName);
        item.setStory_sound(sound);
        return item;
    }


    void additem_lakcheri(){
        try {
            JSONObject jsonObject = new JSONObject(getJsonMain());
            Iterator iterator = jsonObject.keys();
            while(iterator.hasNext()){
                ArrayList<MultiItem> itemTwos = new ArrayList<>();
                String key = (String)iterator.next();
                JSONObject issue = jsonObject.getJSONObject(key);
                //  get id from  issue
                String name = issue.optString("name");
                JSONArray array = issue.getJSONArray("array");

                String sound_url = null;
                for (int i = 0; i < array.length(); i++) {
                    JSONObject object = array.getJSONObject(i);
                    String title = object.getString("title");
                    String desc = object.getString("desc");
                    String image_url = object.getString("image_url");
                    String sound_name = object.getString("sound_name");
                    if (!object.isNull("sound_url"))
                        sound_url = object.getString("sound_url");
                    itemTwos.add(ADD_STORY_ITEM(title,desc,image_url,sound_name,sound_url));
                }
                itemIntroList.add(SLIDER_LAKCHERI(itemTwos));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    void additem_h(){
        try {
            JSONObject jsonObject = new JSONObject(getJsonMain());
            Iterator iterator = jsonObject.keys();
            while(iterator.hasNext()){
                ArrayList<MultiItem> itemTwos = new ArrayList<>();
                String key = (String)iterator.next();
                JSONObject issue = jsonObject.getJSONObject(key);
                //  get id from  issue
                String name = issue.optString("name");
                JSONArray array = issue.getJSONArray("array");

                String sound_url = null;
                for (int i = 0; i < array.length(); i++) {
                    JSONObject object = array.getJSONObject(i);
                    String title = object.getString("title");
                    String desc = object.getString("desc");
                    String image_url = object.getString("image_url");
                    String sound_name = object.getString("sound_name");
                    if (!object.isNull("sound_url"))
                        sound_url = object.getString("sound_url");
                    itemTwos.add(ADD_STORY_ITEM(title,desc,image_url,sound_name,sound_url));
                }
                itemIntroList.add(SLIDER_HORIZONTAL(itemTwos));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    void additem_v(){
        try {
            JSONObject jsonObject = new JSONObject(getJsonMain());
            Iterator iterator = jsonObject.keys();
            while(iterator.hasNext()){
                ArrayList<MultiItem> itemTwos = new ArrayList<>();
                String key = (String)iterator.next();
                JSONObject issue = jsonObject.getJSONObject(key);
                //  get id from  issue
                String name = issue.optString("name");
                JSONArray array = issue.getJSONArray("array");

                String sound_url = null;
                for (int i = 0; i < array.length(); i++) {
                    JSONObject object = array.getJSONObject(i);
                    String title = object.getString("title");
                    String desc = object.getString("desc");
                    String image_url = object.getString("image_url");
                    String sound_name = object.getString("sound_name");
                    if (!object.isNull("sound_url"))
                        sound_url = object.getString("sound_url");
                    itemTwos.add(ADD_STORY_ITEM(title,desc,image_url,sound_name,sound_url));
                }
                itemIntroList.add(SLIDER_VERTICAL(itemTwos));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    void JSON_SETER(){
        try {
            JSONArray jsonArray = new JSONArray(FileUtil.readAssets(this,"list_story.json"));
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                String type = object.getString("type");
                switch (type){
                    case "TEXT_BOX":
                        TEXT_BOX(String.valueOf(object));
                        break;
                    case "IMAGE":
                        break;
                    case "TITLE":
                        break;
                    case "SLIDER_L":
                        break;
                    case "SLIDER_H":
                        break;
                    case "SLIDER_V":
                        break;
                    default:
                        break;
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
            Log.d(TAG, "JSON_SETER: "+e);
        }
    }

    void TEXT_BOX(String json) throws JSONException {
        MultiItem items = new MultiItem();
        JSONObject object = new JSONObject(json);

        items.setType(MultiItem.TEXT_BOX);
        if (!object.isNull("text"))
            items.setText(object.getString("text"));

        if (!object.isNull("on_click"))
            items.setOn_click(object.getString("on_click"));
        else items.setOn_click("null");

        if (!object.isNull("url"))
            items.setUrl(object.getString("url"));

        if (!object.isNull("title"))
            items.setStory_title(object.getString("title"));

        if (!object.isNull("image"))
            items.setStory_image(object.getString("image"));

        if (!object.isNull("sound_name"))
            items.setStory_soundName(object.getString("sound_name"));

        if (!object.isNull("sound"))
            items.setStory_sound(object.getString("sound"));
        itemIntroList.add(items);
    }

    String getJsonMain(){
        return SaveManager.get(this).getstring_appINFO().get(SaveManager.jsonMain);
    }
}