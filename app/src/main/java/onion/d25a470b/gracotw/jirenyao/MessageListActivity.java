package onion.d25a470b.gracotw.jirenyao;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;

public class MessageListActivity extends AppCompatActivity {
    private androidx.recyclerview.widget.RecyclerView mMessageRecycler;
    private MessageListAdapter mMessageAdapter;
    private java.util.List<Message> messageList;
    private com.google.zxing.integration.android.IntentIntegrator scanIntegrator;
    private String filepath = "MyFileStorage";
    private String filename = "l922.json";
    private java.io.File myExternalFile;

    private void prepareStaticData(){
        android.content.Intent intent = getIntent();
        String number = intent.getStringExtra("number");
        java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        if (number.compareTo("緊急提醒")==0) {
            Message m1;
            Contact c1;
            Contact c2;

            this.messageList = new java.util.ArrayList<Message>();
            c1 = new Contact("緊急提醒", getResources().getDrawable(R.mipmap.ic_launcher));
            c2 = new Contact("", getResources().getDrawable(R.mipmap.ic_launcher));            m1 = new Message();
            m1.setMessage("[地震速報 Quake Alert]02/07 01:36左右東部海域發生中型有感地震,慎防強烈搖晃,氣象局。Beware of probable shaking. CWB");
            m1.setSender(c2);
            try {
                m1.setCreatedAt(dateFormat.parse("2021-02-07 01:39:00").getTime());
            } catch (java.text.ParseException e) {
            }
            this.messageList.add(m1);
            m1 = new Message();
            m1.setMessage("[電力中斷通知]2021年5月13日14時37分興達電廠因事故全廠停機,目前系統供電能力不足,預計5月13日15時開始執行緊急分區輪流停電,電1911或台電官網查詢。電力調度處台電");
            m1.setSender(c2);
            try {
                m1.setCreatedAt(dateFormat.parse("2021-05-13 15:05:00").getTime());
            } catch (java.text.ParseException e) {
            }
            this.messageList.add(m1);
            m1 = new Message();
            m1.setMessage("[電力中斷通知]2021年5月17日20時10分因負載突升,供電能力不足,5月17日20時50分執行緊急分區輪流停電,勿搭電梯,電1911或台電官網查詢。Taipower台電");
            m1.setSender(c2);
            try {
                m1.setCreatedAt(dateFormat.parse("2021-05-17 20:15:00").getTime());
            } catch (java.text.ParseException e) {
            }
            this.messageList.add(m1);
        } else if (number.compareTo("l922")==0) {
            if (this.messageList == null) {
                this.messageList = new java.util.ArrayList<Message>();
            }
            if (isExternalStorageAvailable() && !(isExternalStorageReadOnly())){
                this.myExternalFile = new java.io.File(getExternalFilesDir(filepath), filename);
            }
            if (this.myExternalFile != null) {
                String jsonTxt = null;
                try {
                    StringBuffer output = new StringBuffer();
                    java.io.FileReader fileReader = new java.io.FileReader(this.myExternalFile.getAbsolutePath());
                    java.io.BufferedReader bufferedReader = new java.io.BufferedReader(fileReader);
                    String line = "";
                    while ((line = bufferedReader.readLine()) != null) {
                        output.append(line);
                        output.append("\n");
                    }
                    jsonTxt = output.toString();
                } catch (java.io.IOException e) {
                }
                if (jsonTxt != null) {
                    org.json.JSONObject root = null;
                    org.json.JSONArray a1 = null;
                    Contact c2;
                    c2 = new Contact("", getResources().getDrawable(R.mipmap.ic_launcher));
                    try {
                        root = new org.json.JSONObject(jsonTxt);
                        a1 = root.getJSONArray("data");
                    } catch (org.json.JSONException e) {

                    }
                    for (int i=0; i<a1.length(); i++) {
                        org.json.JSONObject m = null;
                        String m1 = null;
                        String t1 = null;
                        try {
                            m = a1.getJSONObject(i);
                            m1 = m.getString("message");
                            t1 = m.getString("time");
                        } catch (org.json.JSONException e) {
                            break;
                        }
                        java.util.Date d1 = null;
                        try {
                            d1 = dateFormat.parse(t1);
                        } catch (java.text.ParseException e) {
                            break;
                        }
                        Message m2 = new Message();
                        m2.setMessage(m1);
                        m2.setCreatedAt(d1.getTime());
                        m2.setSender(c2);
                        this.messageList.add(m2);
                    }
                }
            }
        } else if (number.compareTo("+886223113731")==0) {
            Message m1;
            Contact c2;
            this.messageList = new java.util.ArrayList<Message>();
            c2 = new Contact("", getResources().getDrawable(R.mipmap.ic_launcher));            m1 = new Message();
            m1.setMessage("為證明不是大\u9678方\u9762\u8A8D知作\u6230..本作者(台灣人)公開聲明 \"\u7FD2近\u5E73去吃\u5C4E\"");
            m1.setSender(c2);
            try {
                m1.setCreatedAt(dateFormat.parse("2021-05-20 12:00:00").getTime());
            } catch (java.text.ParseException e) {
            }
            this.messageList.add(m1);
        } else {
            this.messageList = new java.util.ArrayList<Message>();
        }
    }

    public void setMessageList(java.util.List<Message> messageList) {
        this.messageList = messageList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_list_activity);
        android.widget.Button sendButton;
        android.widget.EditText editText1;

        android.content.Intent intent = getIntent();
        String number = intent.getStringExtra("number");
        this.getSupportActionBar().setTitle(number);

        prepareStaticData();

        editText1 = (android.widget.EditText) findViewById(R.id.edit_gchat_message);

        sendButton = (android.widget.Button) findViewById(R.id.button_gchat_send);
        sendButton.setOnClickListener(new android.view.View.OnClickListener() {
            public void onClick(android.view.View v) {
                // Code here executes on main thread after user presses button
                String s = editText1.getText().toString().trim();
                Message m1;
                Contact c2;
                c2 = new Contact("", getResources().getDrawable(R.mipmap.ic_launcher));
                m1 = new Message();
                m1.setMessage(s);
                m1.setSender(c2);
                m1.setCreatedAt(new java.util.Date().getTime());
                editText1.setText("");
                mMessageAdapter.notifyDataSetChanged();
                if (s.length() > 0) {
                    messageList.add(m1);
                } else {
                    scanIntegrator = new com.google.zxing.integration.android.IntentIntegrator(MessageListActivity.this);
                    scanIntegrator.setPrompt("請\u6383描");
                    scanIntegrator.setOrientationLocked(false);
                    scanIntegrator.initiateScan();
                }
            }
        });
        mMessageRecycler = (androidx.recyclerview.widget.RecyclerView) findViewById(R.id.recycler_gchat);
        mMessageAdapter = new MessageListAdapter(this, messageList);
        if (this.getSupportActionBar().getTitle().toString().compareTo("l922")==0) {
            mMessageAdapter.setMaxMessage(2);
        }
        mMessageRecycler.setLayoutManager(new androidx.recyclerview.widget.LinearLayoutManager(this));
        mMessageRecycler.setAdapter(mMessageAdapter);
    }

    public void onActivityResult(int requestCode, int resultCode, android.content.Intent intent) {
        com.google.zxing.integration.android.IntentResult scanningResult = com.google.zxing.integration.android.IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
            if(scanningResult.getContents() != null) {
                String scanContent = scanningResult.getContents();
                android.widget.EditText editText1;
                editText1 = (android.widget.EditText) findViewById(R.id.edit_gchat_message);
                if (scanContent.length() >= 6 && scanContent.substring(0,6).equalsIgnoreCase("smsto:")) {
                    String m1 = scanContent.substring(6);
                    int i1 = m1.indexOf(":");
                    String m2 = m1.substring(i1+1);
                    editText1.setText(m2);
                } else if (scanContent.length() >= 1) {
                    editText1.setText(scanContent);
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, intent);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        String s1;
        java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        s1 = this.getSupportActionBar().getTitle().toString();
        if (s1.compareTo("l922")==0) {
            org.json.JSONObject root = new org.json.JSONObject();
            org.json.JSONArray a1 = new org.json.JSONArray();
            for (Message m1 : this.messageList) {
                org.json.JSONObject o1 = new org.json.JSONObject();
                try {
                    o1.put("message", m1.getMessage());
                    o1.put("time", dateFormat.format(new java.util.Date(m1.getCreatedAt())));
                    a1.put(o1);
                } catch (org.json.JSONException e) {
                }
            }
            try {
                root.put("data", a1);
            } catch (org.json.JSONException e) {
            }
            String s2 = root.toString();
            java.io.FileOutputStream fout;
            try {
                fout = new java.io.FileOutputStream(this.myExternalFile);
                java.io.PrintWriter fwrite = new java.io.PrintWriter(fout);
                fwrite.print(s2);
                fwrite.flush();
                fwrite.close();
            } catch (java.io.IOException e) {

            }
        }


    }

    private static boolean isExternalStorageReadOnly() {
        String extStorageState = android.os.Environment.getExternalStorageState();
        if (android.os.Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)) {
            return true;
        }
        return false;
    }

    private static boolean isExternalStorageAvailable() {
        String extStorageState = android.os.Environment.getExternalStorageState();
        if (android.os.Environment.MEDIA_MOUNTED.equals(extStorageState)) {
            return true;
        }
        return false;
    }
}

class Message {
    String message;
    Contact sender;
    long createdAt;

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public Contact getSender() {
        return sender;
    }

    public void setSender(Contact sender) {
        this.sender = sender;
    }
}

class MessageListAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter {
    private static final int VIEW_TYPE_MESSAGE_SENT = 1;
    private static final int VIEW_TYPE_MESSAGE_RECEIVED = 2;

    private int maxMessage = 100;
    private android.content.Context mContext;
    private java.util.List<Message> mMessageList;

    public void setMaxMessage(int maxMessage) {
        this.maxMessage = maxMessage;
    }

    public MessageListAdapter(android.content.Context context, java.util.List<Message> messageList) {
        mContext = context;
        mMessageList = messageList;
    }

    @Override
    public int getItemCount() {
        if (mMessageList.size() < maxMessage) {
            return mMessageList.size();
        } else {
            return maxMessage;
        }
    }
    // Determines the appropriate ViewType according to the sender of the message.
    @Override
    public int getItemViewType(int position) {
        Message message = null;
        if (mMessageList.size() < maxMessage) {
            message = (Message) mMessageList.get(position);
        } else {
            message = (Message) mMessageList.get(position+mMessageList.size()-maxMessage);
        }

        if (message.getSender().getNumber().equals("")) {
            // If the current user is the sender of the message
            return VIEW_TYPE_MESSAGE_SENT;
        } else {
            // If some other user sent the message
            return VIEW_TYPE_MESSAGE_RECEIVED;
        }
    }

    // Inflates the appropriate layout according to the ViewType.
    @Override
    public androidx.recyclerview.widget.RecyclerView.ViewHolder onCreateViewHolder(android.view.ViewGroup parent, int viewType) {
        android.view.View view;

        if (viewType == VIEW_TYPE_MESSAGE_SENT) {
            view = android.view.LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_chat_me, parent, false);
            return new SentMessageHolder(view);
        } else if (viewType == VIEW_TYPE_MESSAGE_RECEIVED) {
            view = android.view.LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_chat_other, parent, false);
            return new ReceivedMessageHolder(view);
        }

        return null;
    }

    // Passes the message object to a ViewHolder so that the contents can be bound to UI.
    @Override
    public void onBindViewHolder(androidx.recyclerview.widget.RecyclerView.ViewHolder holder, int position) {
        Message message = null;
        if (mMessageList.size() < maxMessage) {
            message = (Message) mMessageList.get(position);
        } else {
            message = (Message) mMessageList.get(position+mMessageList.size()-maxMessage);
        }

        switch (holder.getItemViewType()) {
            case VIEW_TYPE_MESSAGE_SENT:
                ((SentMessageHolder) holder).bind(message);
                break;
            case VIEW_TYPE_MESSAGE_RECEIVED:
                ((ReceivedMessageHolder) holder).bind(message);
                break;
        }
    }

    private class ReceivedMessageHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        android.widget.TextView messageText, timeText, nameText;
        android.widget.ImageView profileImage;

        ReceivedMessageHolder(android.view.View itemView) {
            super(itemView);
            messageText = (android.widget.TextView) itemView.findViewById(R.id.text_gchat_user_other);
            timeText = (android.widget.TextView) itemView.findViewById(R.id.text_gchat_timestamp_other);
            nameText = (android.widget.TextView) itemView.findViewById(R.id.text_gchat_message_other);
            profileImage = (android.widget.ImageView) itemView.findViewById(R.id.image_gchat_profile_other);
        }

        void bind(Message message) {
            messageText.setText(message.getMessage());
            java.util.Date date1 = new java.util.Date();
            date1.setTime(message.getCreatedAt());
            java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy '年' MM '月' dd '日'");
            // Format the stored timestamp into a readable String using method.
            timeText.setText(dateFormat.format(date1));
            nameText.setText(message.getSender().getNumber());

            // Insert the profile image from the URL into the ImageView.
            // Utils.displayRoundImageFromUrl(mContext, message.getSender().getProfileUrl(), profileImage);
        }
    }

    private class SentMessageHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        android.widget.TextView messageText, timeText;

        SentMessageHolder(android.view.View itemView) {
            super(itemView);

            messageText = (android.widget.TextView) itemView.findViewById(R.id.text_gchat_message_me);
            timeText = (android.widget.TextView) itemView.findViewById(R.id.text_gchat_timestamp_me);
        }

        void bind(Message message) {
            messageText.setText(message.getMessage());
            java.util.Date date1 = new java.util.Date();
            date1.setTime(message.getCreatedAt());
            java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy '年' MM '月' dd '日'");

            // Format the stored timestamp into a readable String using method.
            timeText.setText(dateFormat.format(date1));
        }
    }

}

