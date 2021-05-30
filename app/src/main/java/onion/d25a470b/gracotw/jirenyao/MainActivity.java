package onion.d25a470b.gracotw.jirenyao;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private androidx.appcompat.widget.Toolbar toolbar1;
    private androidx.recyclerview.widget.RecyclerView rvPhone;
    private java.util.ArrayList<Contact> contacts;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.getSupportActionBar().setTitle("簡訊");

        rvPhone = findViewById(R.id.rv_phone);
        rvPhone.setLayoutManager(new androidx.recyclerview.widget.LinearLayoutManager(this));

        contacts = new java.util.ArrayList<Contact>();
        contacts.add(new Contact("+886223113731", getResources().getDrawable(android.R.drawable.ic_dialog_email)));
        contacts.add(new Contact("緊急提醒", getResources().getDrawable(android.R.drawable.ic_dialog_alert)));
        contacts.add(new Contact("l922", getResources().getDrawable(android.R.drawable.ic_dialog_email)));

        MyAdapter1 m1 = new MyAdapter1(contacts);
        m1.setMainActivity(this);
        rvPhone.setAdapter(m1);

    }
}

class MyAdapter1 extends androidx.recyclerview.widget.RecyclerView.Adapter<MyAdapter1.mViewHolder> {

    private java.util.ArrayList<Contact> contacts;
    private AppCompatActivity mainActivity;

    public MyAdapter1(java.util.ArrayList<Contact> contacts) {
        this.contacts = contacts;
    }

    public class mViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        private android.widget.ImageView ivIcon;
        private android.widget.TextView tvName;

        public mViewHolder(android.view.View itemView) {
            super(itemView);
            ivIcon = itemView.findViewById(R.id.iv_icon);
            tvName = itemView.findViewById(R.id.tv_name);
        }
    }

    @androidx.annotation.NonNull
    @Override
    public mViewHolder onCreateViewHolder(@androidx.annotation.NonNull android.view.ViewGroup parent, int viewType) {
        android.view.View view = android.view.LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item,parent,false);
        mViewHolder holder = new mViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@androidx.annotation.NonNull mViewHolder holder, int position) {
        holder.tvName.setText(contacts.get(position).getNumber());
        holder.ivIcon.setImageDrawable(contacts.get(position).getIcon());
        holder.itemView.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                //這裡新增點選事件
                android.content.Intent intent = new android.content.Intent(mainActivity, MessageListActivity.class);
                intent.putExtra("number", holder.tvName.getText());
                mainActivity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public void setMainActivity(AppCompatActivity mainActivity) {
        this.mainActivity = mainActivity;
    }
}
