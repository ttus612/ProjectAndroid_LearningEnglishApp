package com.example.myapplication.UI;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.DB.Database;
import com.example.myapplication.Login;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TaiKhoanFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TaiKhoanFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView tvTenTaiKhoan, tvTitleTenTaiKhoan, tvNgaySinh, tvGioiTinh, tvSoDienThoai, tvUserName, tvPassword;
    private MainActivity mMainActivity;

    private Button btnDoiThongTinCaNhan,btnDoiPassword, btnXoaTaiKhoan, btnDangXuat;


    Database db;
    private String tt;
    private String username;
    private String password;
    private String idND;
    private String HoTen;
    private String ngaySinh;
    private String gioiTinh;
    private String soDienThoai;

    public TaiKhoanFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SubscriptionsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TaiKhoanFragment newInstance(String param1, String param2) {
        TaiKhoanFragment fragment = new TaiKhoanFragment();
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
        View view = inflater.inflate(R.layout.fragment_taikhoan, container, false);
        tvTenTaiKhoan = view.findViewById(R.id.tvTenTaiKhoan);
        tvTitleTenTaiKhoan = view.findViewById(R.id.tvTitleTenTaiKhoan);
        tvNgaySinh = view.findViewById(R.id.tvNgaySinh);
        tvGioiTinh= view.findViewById(R.id.tvGioiTinh);
        tvSoDienThoai = view.findViewById(R.id.tvSoDienThoai);
        tvUserName = view.findViewById(R.id.tvUserName);
        tvPassword = view.findViewById(R.id.tvPassword);
        btnDoiThongTinCaNhan  = view.findViewById(R.id.btnDoiThongTinCaNhan);
        btnDoiPassword = view.findViewById(R.id.btnDoiPassword);
        btnDangXuat = view.findViewById(R.id.btnDangXuat);
        db = new Database(getContext());

        mMainActivity = (MainActivity) getActivity();
        Log.d("maTK", "TAI KHOAN"+mMainActivity.getmTk());
        getThongTinNguoiDung(mMainActivity.getmTk());

        btnDoiThongTinCaNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Update thong tin
                chinhSua();
            }
        });

        btnDoiPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Update thong tin
                chinhSuaPassword();
            }
        });

        btnDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Login.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

//        btnXoaTaiKhoan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                    try {
//                        String sql = "DELETE FROM nguoidung WHERE idND = '"+idND+"'";
//                        db.query_noresult(sql);
//                        Toast.makeText(getContext(), "Xóa thành công", Toast.LENGTH_SHORT).show();
//
//                        showConFormNopBai();
//
//                    }catch (Exception e) {
//                        e.printStackTrace();
//
//                }
//            }
//        });

        return view;
    }

    private void chinhSuaPassword() {
        androidx.appcompat.app.AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
        alertDialog.setTitle("Đổi pass");
        alertDialog.setIcon(R.drawable.question_mark);

        // Tạo layout chứa EditText
        LinearLayout layout = new LinearLayout(getContext());
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(50, 50, 50, 50);

        //Pass
        TextView tvPass = new TextView(getContext());
        tvPass.setText("Password: ");
        layout.addView(tvPass);

        EditText editPass = new EditText(getContext());
        editPass.setText(password);
        layout.addView(editPass);

        alertDialog.setView(layout);

        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Thoát", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                alertDialog.cancel();
            }
        });

        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Sửa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                try {
                    String pass = editPass.getText().toString();
                    String sql = "UPDATE TaiKhoan SET Password = '"+pass+"' WHERE Username = '"+username+"'";
                    db.query_noresult(sql);
                    Toast.makeText(getContext(), "Sửa thành công", Toast.LENGTH_SHORT).show();
                }catch (Exception e) {
                    e.printStackTrace();
                }
                //load lại dữ liệu
                getThongTinNguoiDung(mMainActivity.getmTk());
            }
        });
        alertDialog.show();
    }

    private void chinhSua() {
            androidx.appcompat.app.AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
            alertDialog.setTitle("Chỉnh sửa thông tin người dùng");
            alertDialog.setIcon(R.drawable.question_mark);

            // Tạo layout chứa EditText
            LinearLayout layout = new LinearLayout(getContext());
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.setPadding(50, 50, 50, 50);

            //Họ tên
            TextView tvHoten = new TextView(getContext());
            tvHoten.setText("Họ và tên: ");
            layout.addView(tvHoten);

            EditText editTextHoTen = new EditText(getContext());
            editTextHoTen.setText(HoTen);
            layout.addView(editTextHoTen);

            //Ngày sinh
            TextView tvNgaySinh = new TextView(getContext());
            tvNgaySinh.setText("Ngày sinh: ");
            layout.addView(tvNgaySinh);

            EditText editTextNgaySinh = new EditText(getContext());
            editTextNgaySinh.setText(ngaySinh);
            layout.addView(editTextNgaySinh);

            //Giới tính
            TextView tvGioiTinh = new TextView(getContext());
            tvGioiTinh.setText("Giới tính: ");
            layout.addView(tvGioiTinh);

            EditText editTextGioiTinh = new EditText(getContext());
            if (gioiTinh.equals("1"))
                gioiTinh = "Nam";
            else
                gioiTinh = "Nữ";
            editTextGioiTinh.setText(gioiTinh);
            layout.addView(editTextGioiTinh);

            //Số điện thoại
            TextView tvSoDienThoai = new TextView(getContext());
            tvSoDienThoai.setText("Số điện thoại: ");
            layout.addView(tvSoDienThoai);

            EditText editTextSoDienThoai = new EditText(getContext());
            editTextSoDienThoai.setText(soDienThoai);
            layout.addView(editTextSoDienThoai);


            // Thêm layout vào dialog
            alertDialog.setView(layout);


            alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Thoát", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    alertDialog.cancel();
                }
            });
            alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Sửa", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    try {
                        String ten = editTextHoTen.getText().toString();
                        String ngaySinh = editTextNgaySinh.getText().toString();
                        String gioiTinh = editTextGioiTinh.getText().toString();
                        if (gioiTinh.equals("Nam"))
                            gioiTinh = "1";
                        else
                            gioiTinh = "0";
                        String soDienThoai = editTextSoDienThoai.getText().toString();
                        String sql = "UPDATE NguoiDung SET HoTen = '"+ten+"', NgaySinh = '"+ngaySinh+"', GioiTinh = '"+gioiTinh+"', SoDienThoai = '"+soDienThoai+"' WHERE idTaiKhoan = '"+mMainActivity.getmTk()+"'";
                        db.query_noresult(sql);

                        Toast.makeText(getContext(), "Sửa thành công", Toast.LENGTH_SHORT).show();
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                    //load lại dữ liệu
                    getThongTinNguoiDung(mMainActivity.getmTk());
                }
            });
            alertDialog.show();


    }

    private void getThongTinNguoiDung(String getmTk) {
        try {
            Cursor cursor = db.query_hasresult("SELECT * FROM TaiKhoan TK JOIN NguoiDung ND ON TK.userName = ND.idTaiKhoan where idTaiKhoan = '"+getmTk+"'");
            tt = "";
            if (cursor.getCount() != 0) {
                while (cursor.moveToNext()) {
                    username = cursor.getString(0);
                    password = cursor.getString(1);
                    idND = cursor.getString(2);
                    HoTen = cursor.getString(3);
                    ngaySinh = cursor.getString(4);
                    gioiTinh = cursor.getString(5);
                    soDienThoai = cursor.getString(6);

                    tt += "username: " + username + "\n" +
                            "password: " + password + "\n" +
                            "idND: " + idND + "\n" +
                            "HoTen: " + HoTen + "\n" +
                            "ngaySinh: " + ngaySinh +"\n" +
                            "gioiTinh: " + gioiTinh +"\n" +
                            "SĐT: " + soDienThoai + "\n";

                    tvTenTaiKhoan.setText(HoTen);
                    tvTitleTenTaiKhoan.setText(HoTen);
                    tvNgaySinh.setText(ngaySinh);
                    if (gioiTinh.equals("1"))
                        tvGioiTinh.setText("Nam");
                    else if (gioiTinh.equals("0"))
                        tvGioiTinh.setText("Nữ");
                    tvSoDienThoai.setText(soDienThoai);
                    tvUserName.setText(username);
                    tvPassword.setText(password);

                }
                Log.d("thongtin", tt);
            } else {
                tt = "Không có dữ liệu";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void showConFormNopBai() {
        AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
        alertDialog.setTitle("Thông báo");
        alertDialog.setIcon(R.drawable.question_mark);
        alertDialog.setMessage("Bạn có chắc chắn muốn xóa tài khoản này không?");
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Nộp", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(getContext(), Login.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Hủy bỏ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                alertDialog.cancel();
            }
        });
        alertDialog.show();
    }
}