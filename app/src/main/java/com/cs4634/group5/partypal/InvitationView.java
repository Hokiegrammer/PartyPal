package com.cs4634.group5.partypal;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

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
        StringBuilder body = new StringBuilder();
        body.append("Hello, \n\n" +
                "You and your child are invited by " + ((EditText) findViewById(R.id.whoEditText)).getText().toString() + " to "
                + ((EditText) findViewById(R.id.whatEditText)).getText().toString() + "!\n\n");
        body.append("" +
                "\tWhen: " + ((TextView) findViewById(R.id.timeEditText)).getText().toString() + " on " + ((TextView) findViewById(R.id.dateEditText)).getText().toString() + "\n" +
                "\tWhere: " + ((EditText) findViewById(R.id.whereEditText)).getText().toString() + "\n" +
                "\n");
        body.append("For ideas of what to bring as a gift, " + ((EditText) findViewById(R.id.giftIdeasEditText)).getText().toString() + "\n\n");
        body.append("We also thought it would be important for you to know that " + ((EditText) findViewById(R.id.restrictionsEditText)).getText().toString() +
                " Thank you for taking this into consideration.\n\n");

        if ( ((ToggleButton)findViewById(R.id.requestRSVPToggleButton)).isChecked() ) {
            body.append("We would like you RSVP to the event by replying to this email.\n\n");
        }

        body.append("See you at the party!\n");

        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:"));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, body.toString());

        startActivity(Intent.createChooser(emailIntent, "Send email via:"));
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
