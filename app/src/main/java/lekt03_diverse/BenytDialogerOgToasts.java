package lekt03_diverse;

import android.support.v7.app.AppCompatActivity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.Toast;

import dk.nordfalk.android.elementer.R;
import lekt05_grafik.Tegneprogram;

/**
 * @author Jacob Nordfalk
 */
public class BenytDialogerOgToasts extends AppCompatActivity implements OnClickListener {

  Button visStandardToast, visToastMedBillede, visSnackBar, visAlertDialog, visAlertDialog1, visAlertDialog2, visProgressDialog, visProgressDialogMedBillede, visNoitifikation;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    TableLayout tl = new TableLayout(this);

    visStandardToast = new Button(this);
    visStandardToast.setText("vis Standard Toast");
    tl.addView(visStandardToast);

    visToastMedBillede = new Button(this);
    visToastMedBillede.setText("vis Toast Med Billede");
    tl.addView(visToastMedBillede);

    visSnackBar = new Button(this);
    visSnackBar.setText("vis SnackBar");
    tl.addView(visSnackBar);

    visAlertDialog = new Button(this);
    visAlertDialog.setText("vis AlertDialog");
    tl.addView(visAlertDialog);

    visAlertDialog1 = new Button(this);
    visAlertDialog1.setText("vis AlertDialog med 1 knap");
    tl.addView(visAlertDialog1);

    visAlertDialog2 = new Button(this);
    visAlertDialog2.setText("vis AlertDialog med 2 knapper");
    tl.addView(visAlertDialog2);

    visProgressDialog = new Button(this);
    visProgressDialog.setText("vis Progress Dialog");
    tl.addView(visProgressDialog);

    visProgressDialogMedBillede = new Button(this);
    visProgressDialogMedBillede.setText("vis ProgressDialog Med Billede");
    tl.addView(visProgressDialogMedBillede);

    visNoitifikation = new Button(this);
    visNoitifikation.setText("vis Noitifikation");
    tl.addView(visNoitifikation);

    visStandardToast.setOnClickListener(this);
    visToastMedBillede.setOnClickListener(this);
    visProgressDialog.setOnClickListener(this);
    visProgressDialogMedBillede.setOnClickListener(this);
    visSnackBar.setOnClickListener(this);
    visAlertDialog1.setOnClickListener(this);
    visAlertDialog2.setOnClickListener(this);
    visNoitifikation.setOnClickListener(this);

    ScrollView sv = new ScrollView(this);
    sv.addView(tl);
    setContentView(sv);
  }

  public void onClick(View hvadBlevDerKlikketPå) {
    if (hvadBlevDerKlikketPå == visStandardToast) {
      Toast.makeText(this, "Standard-toast", Toast.LENGTH_LONG).show();
    } else if (hvadBlevDerKlikketPå == visToastMedBillede) {
      Toast t = new Toast(this);
      ImageView im = new ImageView(this);
      im.setImageResource(R.drawable.logo);
      im.setAlpha(180);
      t.setView(im);
      t.setGravity(Gravity.CENTER, 0, 0);
      t.show();
    } else if (hvadBlevDerKlikketPå == visSnackBar) {
      // Bemærk - kræver at designbiblioteket er med i build.gradle - f.eks. med
      // compile 'com.android.support:design:25.3.1'
      Snackbar.make(hvadBlevDerKlikketPå, "En kort Snackbar", Snackbar.LENGTH_LONG).setAction("Vis en mere", new OnClickListener() {
        @Override
        public void onClick(View v) {
          Snackbar.make(visSnackBar, "OK, her er en lang snackbar", Snackbar.LENGTH_SHORT).show();
        }
      }).show();
    } else if (hvadBlevDerKlikketPå == visAlertDialog) {
      AlertDialog.Builder dialog = new AlertDialog.Builder(this);
      dialog.setTitle("En AlertDialog");
      dialog.setMessage("Denne her har ingen knapper");
      dialog.show();
    } else if (hvadBlevDerKlikketPå == visAlertDialog1) {
      AlertDialog.Builder dialog = new AlertDialog.Builder(this);
      dialog.setTitle("En AlertDialog");
      dialog.setIcon(R.drawable.logo);
      dialog.setMessage("Denne her har én knap");
      dialog.setPositiveButton("Vis endnu en toast", new AlertDialog.OnClickListener() {

        public void onClick(DialogInterface arg0, int arg1) {
          Toast.makeText(BenytDialogerOgToasts.this, "Standard-toast", Toast.LENGTH_LONG).show();
        }
      });
      dialog.show();
    } else if (hvadBlevDerKlikketPå == visAlertDialog2) {
      AlertDialog.Builder dialog = new AlertDialog.Builder(this);
      dialog.setTitle("En AlertDialog");
      EditText et = new EditText(this);
      et.setText("Denne her viser et generelt view og har to knapper");
      dialog.setView(et);
      dialog.setPositiveButton("Vis endnu en toast", new AlertDialog.OnClickListener() {

        public void onClick(DialogInterface arg0, int arg1) {
          Toast.makeText(BenytDialogerOgToasts.this, "Endnu en standard-toast", Toast.LENGTH_LONG).show();
        }
      });
      dialog.setNegativeButton("Nej tak", null);
      dialog.show();
    } else if (hvadBlevDerKlikketPå == visProgressDialog) {
      ProgressDialog.show(this, "", "En ProgressDialog", true).setCancelable(true);
    } else if (hvadBlevDerKlikketPå == visProgressDialogMedBillede) {
      ProgressDialog dialog = new ProgressDialog(this);
      dialog.setIndeterminate(true); // drejende hjul
      dialog.setTitle("En ProgressDialog");
      dialog.setIcon(R.drawable.logo);
      dialog.setMessage("hej herfra");
      dialog.setOnCancelListener(new OnCancelListener() {

        public void onCancel(DialogInterface dialog) {
          Toast.makeText(BenytDialogerOgToasts.this, "Annulleret", Toast.LENGTH_LONG).show();
        }
      });
      dialog.show();
    } else if (hvadBlevDerKlikketPå == visNoitifikation) {
      Intent intent = new Intent(this, Tegneprogram.class);
      PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);

      NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
              .setContentIntent(pi)
              .setSmallIcon(R.drawable.bil)
              .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.logo))
              .setTicker("Der skal tegnes!")
              .setContentTitle("Tegn!")
              .setContentText("Du er nødt til at tegne lidt")
              .setSubText("Bla bla bla og en længere forklaring");

      long[] vibrate = {0, 100, 300, 400, 500, 510, 550, 560, 600, 610, 650, 610, -1};
      builder.setVibrate(vibrate);

      NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
      notificationManager.notify(42, builder.build());
    }
  }
}
