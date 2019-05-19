package com.example.firstroad.fragments;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.firstroad.R;
import com.example.firstroad.activities.LogInActivity;
import com.example.firstroad.classes.User;
import com.example.firstroad.pages.mines.clopedias.Mines_Clopedia_Ha_aEr_Bin_Bin_Xue_Da_Shi_Jie;
import com.example.firstroad.pages.mines.MinesFunctionsOrders;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;

import static android.app.Activity.RESULT_OK;

public class MainMineFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private ImageView head_icon;
    private TextView nickName;
    private String path;
    private User user;
    private Bitmap bitmap;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if((Integer)msg.obj==0){
                head_icon.setImageBitmap(bitmap);
            }if (msg.what == 0x0){
//                ((Personal)mPerson).getmTips().setText("");
            }
        }
    };

    public MainMineFragment() {
        // Required empty public constructo
    }

    public static MainMineFragment newInstance(String param1, String param2) {
        MainMineFragment fragment = new MainMineFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_mine, container, false);
        LinearLayout linearLayout = view.findViewById(R.id.linearlayout);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Mines_Clopedia_Ha_aEr_Bin_Bin_Xue_Da_Shi_Jie.class));
            }
        });

        LinearLayout linearLayout1 = view.findViewById(R.id.order_linearlayout);
        linearLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MinesFunctionsOrders.class));
            }
        });

        user = BmobUser.getCurrentUser(User.class);
        iniViews(view);
        return view;
    }

    private void iniViews(View view){
        head_icon = (ImageView) view.findViewById(R.id.head_icon);
        nickName = (TextView) view.findViewById(R.id.user_name);

        head_icon.setOnClickListener(this);
        nickName.setOnClickListener(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.head_icon:
                if (BmobUser.isLogin()){
                    loadImage();
                }else {
                    LogInActivity.acrionStart((AppCompatActivity) getActivity());
                }
                break;

            case R.id.user_name:
                chnageImage();
                break;

            default:

                break;
        }
    }


    private void loadImage(){
        if(ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(),new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            },2);
        }
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 2);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 2://这里的requestCode是我自己设置的，就是确定返回到那个Activity的标志
                if (resultCode == RESULT_OK) {//resultcode是setResult里面设置的code值
                    try {
                        Uri selectedImage = data.getData(); //获取系统返回的照片的Uri
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
                        Cursor cursor = getActivity().getContentResolver().query(selectedImage,
                                filePathColumn, null, null, null);//从系统表中查询指定Uri对应的照片
                        cursor.moveToFirst();
                        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                        path = cursor.getString(columnIndex);  //获取照片路径
                        cursor.close();

                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inSampleSize = 1;
                        bitmap = BitmapFactory.decodeFile(path, options);
                        bitmap = Bitmap.createScaledBitmap(bitmap, 700, 400, false);
                        chnageImage();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;

            default:

                break;
        }
    }

    /*定义一个Handler，定义延时执行的行为*/
    @RequiresApi(api = Build.VERSION_CODES.FROYO)
    public void chnageImage(){
        final String picPath = path;
        final BmobFile bmobFile = new BmobFile(imageFactory(picPath));
        bmobFile.uploadblock(new UploadFileListener() {

            @Override
            public void done(BmobException e) {
                if(e==null){
                    user.setImageFile(bmobFile);
                    user.update(new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if (e == null) {
                                Toast.makeText(getActivity(), "头像已更新", Toast.LENGTH_SHORT).show();
                            } else {
                                Message message = handler.obtainMessage();
                                message.obj = 0;
                                handler.sendMessage(message);
                                Toast.makeText(getActivity(), "失败" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else{
                    Toast.makeText(getActivity(), "设置失败，请重新选择", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onProgress(Integer value) {

            }
        });
    }

    /*
    压缩路径下的文件
     */
    @RequiresApi(api = Build.VERSION_CODES.FROYO)
    private File imageFactory(String picPath){
        BitmapFactory.Options o=new BitmapFactory.Options();
        Bitmap bitmap=BitmapFactory.decodeFile(picPath, o);
        bitmap=Bitmap.createScaledBitmap(bitmap, 400, 400, false);
        File root = getActivity().getExternalCacheDir();
        File pic=new File(root,"test.jpg");
        try {
            FileOutputStream fos=new FileOutputStream(pic);
            bitmap.compress(Bitmap.CompressFormat.JPEG,50,fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return pic;
    }

}
