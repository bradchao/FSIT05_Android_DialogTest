package tw.brad.dialogtest;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private AlertDialog alertDialog;
    private Timer timer;
    private View mainView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainView = findViewById(R.id.mainView);

        timer = new Timer();
    }

    public void test1(View view) {
        timer.schedule(new CloseAlertTask(), 3*1000);
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this)
                .setTitle("Title")
                .setMessage("Message")
                .setCancelable(false);
        alertDialog = builder.create();

        alertDialog.show();

    }

    public void test5(View view) {
        final Dialog dialog = new Dialog(this);
        dialog.setTitle("標題");
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.mydialog);

        EditText account = dialog.findViewById(R.id.dialog_account);
        EditText passwd = dialog.findViewById(R.id.dialog_passwd);
        Button ok = dialog.findViewById(R.id.dialog_ok);
        Button cancel = dialog.findViewById(R.id.dialog_cancel);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
    public void test6(View view) {

//        Toast t = Toast.makeText(this, "Hello, World", Toast.LENGTH_SHORT);
//        t.setGravity(Gravity.FILL_HORIZONTAL+Gravity.CENTER_VERTICAL,
//                0 , 200);
//        t.show();

        showMyToast("失敗", false);
    }

    private void showMyToast(String mesg, boolean isSuccess){
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.mytoast,
                (ViewGroup) findViewById(R.id.toast_vg));

        TextView toastMesg = view.findViewById(R.id.toast_title);
        toastMesg.setText(mesg);
        ImageView img = view.findViewById(R.id.toast_img);
        img.setImageResource(isSuccess?R.drawable.ok:R.drawable.fail);

        Toast t2 = new Toast(this);
        t2.setGravity(Gravity.CENTER_VERTICAL,
                0 , 0);
        t2.setDuration(Toast.LENGTH_LONG);
        t2.setView(view);
        t2.show();

    }

    public void test7(View view) {
        Snackbar.make(mainView, "Hello, OK",
                Snackbar.LENGTH_SHORT)
        .setAction("OK", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("brad", "OK");
            }
        }).show();

    }


    private class CloseAlertTask extends TimerTask {
        @Override
        public void run() {
            if (alertDialog.isShowing()){
                alertDialog.dismiss();
            }
        }
    }

    public void test2(View view) {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this)
                        .setTitle("輸入帳號")
                        .setCancelable(false);
        final EditText input = new EditText(this);
        builder.setView(input);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String str = input.getText().toString();
                Log.v("brad", str);
            }
        });
        builder.setNegativeButton("Cancel", null);
        alertDialog = builder.create();

        alertDialog.show();
    }

    public void test3(View view) {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this)
                        .setTitle("我是標題")
                        .setIcon(R.drawable.ic_assignment_black_24dp)
                        .setCancelable(false);
//        builder.setItems(new String[]{"Android", "iOS", "Windows"}, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Log.v("brad", "which = " + which);
//            }
//        });

        builder.setSingleChoiceItems(new String[]{"Android", "iOS", "Windows"}, 0,
                new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.v("brad", "which = " + which);
            }
        });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });


        alertDialog = builder.create();

        alertDialog.show();
    }
    public void test4(View view) {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this)
                        .setTitle("我是標題")
                        .setIcon(R.drawable.ic_assignment_black_24dp)
                        .setCancelable(false);

        builder.setMultiChoiceItems(new String[]{"apple","android", "windows"},
                new boolean[]{true,false,false},
                new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                    }
                });

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });


        alertDialog = builder.create();

        alertDialog.show();


    }
}
