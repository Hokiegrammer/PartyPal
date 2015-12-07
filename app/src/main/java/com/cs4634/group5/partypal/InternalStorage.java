package com.cs4634.group5.partypal;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by Philip on 12/6/2015.
 */
public final class InternalStorage{

    private InternalStorage() {}

    public static void writeObject(Context context, String key, Object object) throws IOException {
        FileOutputStream file_output_stream = context.openFileOutput(key, Context.MODE_PRIVATE);

        ObjectOutputStream object_output_stream = new ObjectOutputStream(file_output_stream);
        object_output_stream.reset();
        object_output_stream.writeObject(object);
        object_output_stream.flush();
        object_output_stream.close();
        file_output_stream.flush();
        file_output_stream.close();
    }

    public static Object readObject(Context context, String key) throws IOException,
            ClassNotFoundException {
        FileInputStream file_input_stream = context.openFileInput(key);

        ObjectInputStream ois = new ObjectInputStream(file_input_stream);
        Object object = ois.readObject();
        ois.close();
        file_input_stream.close();

        return object;
    }
}
