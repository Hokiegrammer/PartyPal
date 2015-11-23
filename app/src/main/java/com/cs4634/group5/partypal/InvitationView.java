package com.cs4634.group5.partypal;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import org.w3c.dom.Text;

/**
 * Created by Philip on 11/23/2015.
 */
public class InvitationView extends Activity {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.invitation_view);
    }

    public void sendInviteButtonClicked(View view) {
        String subject = ((EditText)findViewById(R.id.whatEditText)).getText().toString();
        String body = "";

        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:"));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, body);

        startActivity(Intent.createChooser(emailIntent, "Send email via:"));
    }

    public void previewInviteButtonClicked(View view) {

    }

    public void restrictionsHintButtonClicked(View view) {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Restrictions");
        alertDialog.setMessage("This is a space to specify your child's dietary restrictions or allergies.");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    public void timeFieldClicked(View view) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getFragmentManager(), "timePicker");
    }

    public void dateFieldClicked(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
    }
}
