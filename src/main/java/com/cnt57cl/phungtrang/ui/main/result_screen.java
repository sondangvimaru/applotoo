package com.cnt57cl.phungtrang.ui.main;


import android.icu.util.Calendar;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.cnt57cl.phungtrang.MainActivity;
import com.cnt57cl.phungtrang.R;
import com.cnt57cl.phungtrang.object.xoso;

import com.github.chrisbanes.photoview.PhotoView;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;


public class result_screen extends Fragment {

    Spinner sp_ngay,sp_thang,sp_nam;
    ArrayList<String> arr_thang ;
    ArrayList<String> arr_nam;
    ArrayList<String> arr_ngay ;
    xoso xs;
    RadioButton rd_all,rd_2so,rd_3so;
    PhotoView pt_result,pt_duoi;
    int loai=0;
    KProgressHUD dialog;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.result_view,container,false);

        sp_ngay= view.findViewById(R.id.spiner_ngay);
        sp_thang=view.findViewById(R.id.spiner_thang);
        sp_nam=view.findViewById(R.id.spiner_nam);
        pt_result= view.findViewById(R.id.img_view_result);
        pt_duoi=view.findViewById(R.id.img_view_dauduoi);
        rd_all=view.findViewById(R.id.rd_all);
        rd_2so=view.findViewById(R.id.rd_2so);
        rd_3so=view.findViewById(R.id.rd_3so);
        rd_all.setChecked(true);
        Calendar calendar=Calendar.getInstance();

       arr_thang= new ArrayList<>();
       arr_thang.add(String.valueOf(calendar.get(Calendar.MONTH)+1));

       arr_nam= new ArrayList<>();
        arr_nam.add( String.valueOf(calendar.get(Calendar.YEAR)));

        arr_ngay= new ArrayList<>();
        int current_day=calendar.get(Calendar.DAY_OF_MONTH);
        for(int i=current_day;i>0;i--) arr_ngay.add(String.valueOf(i));
        ArrayAdapter<String> arr_ngay_ad= new ArrayAdapter<>(getActivity(),android.R.layout.simple_spinner_item,arr_ngay);

        sp_ngay.setAdapter(arr_ngay_ad);
        ArrayAdapter<String> arr_thang_ad= new ArrayAdapter<>(getActivity(),android.R.layout.simple_spinner_item,arr_thang);
        sp_thang.setAdapter(arr_thang_ad);
        ArrayAdapter<String> arr_nam_ad= new ArrayAdapter<>(getActivity(),android.R.layout.simple_spinner_item,arr_nam);
        sp_nam.setAdapter(arr_nam_ad);
        sp_ngay.setSelection(0);
        sp_thang.setSelection(0);
        sp_nam.setSelection(0);
        NumberFormat numberFormat= new DecimalFormat("00");
        String setup_defualt=sp_nam.getSelectedItem().toString()+"-"+numberFormat.format(Integer.parseInt(sp_thang.getSelectedItem().toString()))+"-"+numberFormat.format(Integer.parseInt(sp_ngay.getSelectedItem().toString()));

        init();
        setdata(setup_defualt,loai);
        listent_spiner();
        return view;
    }

    private void listent_spiner() {
        sp_ngay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                NumberFormat numberFormat= new DecimalFormat("00");

                String date=sp_nam.getSelectedItem().toString()+"-"+numberFormat.format(Integer.parseInt(sp_thang.getSelectedItem().toString()))+"-"+numberFormat.format(Integer.parseInt(sp_ngay.getSelectedItem().toString()));
                setdata(date,loai);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sp_thang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                NumberFormat numberFormat= new DecimalFormat("00");

                String date=sp_nam.getSelectedItem().toString()+"-"+numberFormat.format(Integer.parseInt(sp_thang.getSelectedItem().toString()))+"-"+numberFormat.format(Integer.parseInt(sp_ngay.getSelectedItem().toString()));
                setdata(date,loai);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sp_nam.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                NumberFormat numberFormat= new DecimalFormat("00");

                String date=sp_nam.getSelectedItem().toString()+"-"+numberFormat.format(Integer.parseInt(sp_thang.getSelectedItem().toString()))+"-"+numberFormat.format(Integer.parseInt(sp_ngay.getSelectedItem().toString()));
                setdata(date,loai);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void init() {

      dialog=  KProgressHUD.create(getActivity())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Đang tải dữ liệu")

                .setCancellable(true)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f);

        rd_all.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(rd_all.isChecked())
                {
                    loai=0;
                    NumberFormat numberFormat= new DecimalFormat("00");
                    String date =sp_nam.getSelectedItem().toString()+"-"+numberFormat.format(Integer.parseInt(sp_thang.getSelectedItem().toString()))+"-"+numberFormat.format(Integer.parseInt(sp_ngay.getSelectedItem().toString()));
                    setdata(date,loai);
                }
            }
        });
        rd_2so.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(rd_2so.isChecked()) {
                    loai = 1;
                    NumberFormat numberFormat = new DecimalFormat("00");
                    String date = sp_nam.getSelectedItem().toString() + "-" + numberFormat.format(Integer.parseInt(sp_thang.getSelectedItem().toString())) + "-" + numberFormat.format(Integer.parseInt(sp_ngay.getSelectedItem().toString()));
                    setdata(date, loai);
                }
            }
        });
        rd_3so.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(rd_3so.isChecked()) {
                    loai = 2;
                    NumberFormat numberFormat = new DecimalFormat("00");
                    String date = sp_nam.getSelectedItem().toString() + "-" + numberFormat.format(Integer.parseInt(sp_thang.getSelectedItem().toString())) + "-" + numberFormat.format(Integer.parseInt(sp_ngay.getSelectedItem().toString()));
                    setdata(date, loai);
                }
            }
        });
    }

    public static Fragment create_view()
    {
        return new result_screen();

    }

    public void setdata(final String date, final int loai)
    {


        if(!dialog.isShowing()) dialog.show();
        Response.Listener<JSONObject> objectListener= new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray array= response.getJSONArray("data");

                        JSONObject jsonObject= array.getJSONObject(0);
                         xs = new xoso();
                        xs.setId(jsonObject.getInt("id"));
                        xs.setDate(jsonObject.getString("date"));
                        xs.setImg_2so(jsonObject.getString("img_2so"));
                        xs.setImg_3so(jsonObject.getString("img_3so"));
                        xs.setImg_all(jsonObject.getString("img_all"));
                        xs.setImg_duoi(jsonObject.getString("img_duoi"));
                   switch (loai)
                   {
                       case 0:

                           Picasso.get().load("https://trangphungapp.000webhostapp.com/images/"+xs.getImg_all())
                                .error(R.drawable.ic_launcher_background).into(pt_result);
                           break;
                       case 1:
                           Picasso.get().load("https://trangphungapp.000webhostapp.com/images/"+xs.getImg_2so())
                                   .error(R.drawable.ic_launcher_background).into(pt_result);
                           break;
                       case 2:
                       Picasso.get().load("https://trangphungapp.000webhostapp.com/images/"+xs.getImg_3so())
                               .error(R.drawable.ic_launcher_background).into(pt_result);
                       break;

                   }

                    Picasso.get().load("https://trangphungapp.000webhostapp.com/images/"+xs.getImg_duoi())
                            .error(R.drawable.ic_launcher_background).into(pt_duoi);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        JsonObjectRequest request= new JsonObjectRequest(Request.Method.GET, "https://trangphungapp.000webhostapp.com/getresult.php?date=" + date, objectListener, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })

        {

            @Override
            protected void onFinish() {
                super.onFinish();
                if(dialog.isShowing()) dialog.dismiss();
            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(getActivity());
        requestQueue.getCache().clear();
        requestQueue.add(request);


    }
}
