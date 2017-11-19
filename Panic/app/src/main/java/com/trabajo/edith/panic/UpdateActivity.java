package com.trabajo.edith.panic;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Hashtable;
import java.util.Map;

public class UpdateActivity extends AppCompatActivity {

    private TextView ContactPhone1, ContactName1, ContactPhone2, ContactName2, ContactPhone3, ContactName3, MessagePhone, MessageName;
    private EditText Message;

    private String UPDATE_URL = "http://192.168.0.5/panic/update.php";
    private String KEY_UID = "unique_id";
    private String KEY_CONTACTO_1 = "contacto_1";
    private String KEY_TELEFONO_1 = "telefono_1";
    private String KEY_CONTACTO_2 = "contacto_2";
    private String KEY_TELEFONO_2 = "telefono_2";
    private String KEY_CONTACTO_3 = "contacto_3";
    private String KEY_TELEFONO_3 = "telefono_3";
    private String KEY_CONTACTO_MSJ = "contacto_mensaje";
    private String KEY_TELEFONO_MSJ = "telefono_mensaje";
    private String KEY_MENSAJE = "mensaje";

    public static final int PICK_CONTACT_REQUEST = 1;
    private Uri contactUri;
    int state = 0;
    String telefono, nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button emergencia = findViewById(R.id.selectionMessage);
        Button setllamada1 = findViewById(R.id.selectionCall1);
        Button setllamada2 = findViewById(R.id.selectionCall2);
        Button setllamada3 = findViewById(R.id.selectionCall3);
        Button listo = findViewById(R.id.Listo);
        TextView ContactPhone1, ContactName1, ContactPhone2, ContactName2, ContactPhone3, ContactName3, MessagePhone, MessageName;
        EditText Message;
        ContactPhone1 = findViewById(R.id.contactPhoneVerde);
        ContactName1 = findViewById(R.id.contactNameVerde);
        ContactPhone2 = findViewById(R.id.contactPhoneAmarillo);
        ContactName2 = findViewById(R.id.contactNameAmarillo);
        ContactPhone3 = findViewById(R.id.contactPhoneRojo);
        ContactName3 = findViewById(R.id.contactNameRojo);
        MessagePhone = findViewById(R.id.contactPhoneAzul);
        MessageName = findViewById(R.id.contactNameAzul);
        Message = findViewById(R.id.TextoEscrito);

        listo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();
                Intent i = new Intent(UpdateActivity.this, MainActivity.class);
                startActivity(i);
                finish();

            }
        });

        emergencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, Contacts.CONTENT_URI);
                startActivityForResult(i, PICK_CONTACT_REQUEST);
                state = 1;
            }
        });

        setllamada1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, Contacts.CONTENT_URI);
                startActivityForResult(i, PICK_CONTACT_REQUEST);
                state = 2;
            }
        });

        setllamada2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, Contacts.CONTENT_URI);
                startActivityForResult(i, PICK_CONTACT_REQUEST);
                state = 3;
            }
        });

        setllamada3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, Contacts.CONTENT_URI);
                startActivityForResult(i, PICK_CONTACT_REQUEST);
                state = 4;
            }
        });
    }

    private void renderContact(Uri uri) {

        ContactPhone1 = findViewById(R.id.contactPhoneVerde);
        ContactName1 = findViewById(R.id.contactNameVerde);
        ContactPhone2 = findViewById(R.id.contactPhoneAmarillo);
        ContactName2 = findViewById(R.id.contactNameAmarillo);
        ContactPhone3 = findViewById(R.id.contactPhoneRojo);
        ContactName3 = findViewById(R.id.contactNameRojo);
        MessagePhone = findViewById(R.id.contactPhoneAzul);
        MessageName = findViewById(R.id.contactNameAzul);
        Message = findViewById(R.id.TextoEscrito);
        if (state == 1) {
            MessageName.setText(getName(uri));
            MessagePhone.setText(getPhone(uri));
            telefono = MessagePhone.getText().toString();
            nombre = MessageName.getText().toString();
            telefono = telefono.replace(" ", "");
            nombre = nombre.replace(" ", "");
            if (telefono.length() > 10) {
                int longitud = telefono.length() - 10;
                telefono = telefono.substring(longitud);
            }
            MessageName.setText(nombre);
            MessagePhone.setText(telefono);
        } else if (state == 2) {
            ContactName1.setText(getName(uri));
            ContactPhone1.setText(getPhone(uri));
            telefono = ContactPhone1.getText().toString();
            nombre = ContactName1.getText().toString();
            telefono = telefono.replace(" ", "");
            nombre = nombre.replace(" ", "");
            if (telefono.length() > 10) {
                int longitud = telefono.length() - 10;
                telefono = telefono.substring(longitud);
            }
            ContactName1.setText(nombre);
            ContactPhone1.setText(telefono);
        } else if (state == 3) {
            ContactName2.setText(getName(uri));
            ContactPhone2.setText(getPhone(uri));
            telefono = ContactPhone2.getText().toString();
            nombre = ContactName2.getText().toString();
            telefono = telefono.replace(" ", "");
            nombre = nombre.replace(" ", "");
            if (telefono.length() > 10) {
                int longitud = telefono.length() - 10;
                telefono = telefono.substring(longitud);
            }
            ContactName2.setText(nombre);
            ContactPhone2.setText(telefono);
        } else if (state == 4) {
            ContactName3.setText(getName(uri));
            ContactPhone3.setText(getPhone(uri));
            telefono = ContactPhone3.getText().toString();
            nombre = ContactName3.getText().toString();
            telefono = telefono.replace(" ", "");
            nombre = nombre.replace(" ", "");
            if (telefono.length() > 10) {
                int longitud = telefono.length() - 10;
                telefono = telefono.substring(longitud);
            }
            ContactName3.setText(nombre);
            ContactPhone3.setText(telefono);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == PICK_CONTACT_REQUEST) {
            if (resultCode == RESULT_OK) {
                contactUri = intent.getData();
                renderContact(contactUri);
            }
        }
    }

    private String getPhone(Uri uri) {
        String id = null;
        String phone = null;
        Cursor contactCursor = getContentResolver().query(
                uri,
                new String[]{Contacts._ID},
                null,
                null,
                null);
        if (contactCursor.moveToFirst()) {
            id = contactCursor.getString(0);
        }
        contactCursor.close();
        String selectionArgs =
                Phone.CONTACT_ID + " = ? AND " +
                        Phone.TYPE + "= " +
                        Phone.TYPE_MOBILE;
        Cursor phoneCursor = getContentResolver().query(
                Phone.CONTENT_URI,
                new String[]{Phone.NUMBER},
                selectionArgs,
                new String[]{id},
                null
        );
        if (phoneCursor.moveToFirst()) {
            phone = phoneCursor.getString(0);
        }
        phoneCursor.close();
        return phone;
    }

    private String getName(Uri uri) {
        String name = null;
        ContentResolver contentResolver = getContentResolver();
        Cursor c = contentResolver.query(
                uri,
                new String[]{Contacts.DISPLAY_NAME},
                null,
                null,
                null);

        if (c.moveToFirst()) {
            name = c.getString(0);
        }

        c.close();

        return name;
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

    private void uploadImage() {
        // Mostrar el dialogo del proceso
        final ProgressDialog loading = ProgressDialog.show(this, "Subiendo...", "Espere por favor...", false, false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UPDATE_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Destacar el dialogo de proceso
                loading.dismiss();
                //Mostrando el mensaje de la respuesta
                Toast.makeText(UpdateActivity.this, "Subidó correctamente", Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Descartar el dialogo de progreso.
                loading.dismiss();
                //Showing Toast
                Toast.makeText(UpdateActivity.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Obtener el nombre de la imagen
                String uid = "5a0e64deee7003.34011993";
                String contacto_1 = ContactName1.getText().toString().trim();
                String telefono_1 = ContactPhone1.getText().toString().trim();
                String contacto_2 = ContactName2.getText().toString().trim();
                String telefono_2 = ContactPhone2.getText().toString().trim();
                String contacto_3 = ContactName3.getText().toString().trim();
                String telefono_3 = ContactPhone3.getText().toString().trim();
                String contacto_mensaje = MessageName.getText().toString().trim();
                String telefono_mensaje = MessagePhone.getText().toString().trim();
                String mensaje = Message.getText().toString();

                // Creación de parámetros
                Map<String, String> params = new Hashtable<String, String>();

                // Agregando de parámetros
                params.put(KEY_UID, uid);
                params.put(KEY_CONTACTO_1, contacto_1);
                params.put(KEY_CONTACTO_2, contacto_2);
                params.put(KEY_CONTACTO_3, contacto_3);
                params.put(KEY_TELEFONO_1, telefono_1);
                params.put(KEY_TELEFONO_2, telefono_2);
                params.put(KEY_TELEFONO_3, telefono_3);
                params.put(KEY_CONTACTO_MSJ, contacto_mensaje);
                params.put(KEY_TELEFONO_MSJ, telefono_mensaje);
                params.put(KEY_MENSAJE, mensaje);

                return params;
            }
        };

        //Creacion de una cola de solicitudes
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        // Agregar solicitud a la cola
        requestQueue.add(stringRequest);
    }

}
