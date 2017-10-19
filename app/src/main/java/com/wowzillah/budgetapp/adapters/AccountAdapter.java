package com.wowzillah.budgetapp.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wowzillah.budgetapp.R;
import com.wowzillah.budgetapp.model.Account;

import java.util.List;


/**
 * Created by david on 19/10/17.
 */

public class AccountAdapter extends ArrayAdapter<Account> {

    Resources res = getContext().getResources();
    //accounts is the list of accounts to display
    public AccountAdapter(Context context, List<Account> accounts){
        super(context,0,accounts);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_account,parent, false);
        }
        AccountViewHolder viewHolder = (AccountViewHolder) convertView.getTag();
        if(viewHolder ==null){
            viewHolder = new AccountViewHolder();
            viewHolder.categoryIcon =(ImageView) convertView.findViewById(R.id.categoryIcon);
            viewHolder.accountName =(TextView) convertView.findViewById(R.id.accountName);
            viewHolder.accountBalance =(TextView) convertView.findViewById(R.id.accountBalance);
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récuperer l'item [position] de la liste accounts
        Account account = getItem(position);

        int picId = res.getIdentifier(account.getAccountType().getTypeIcon(), "drawable", getContext().getPackageName());
        viewHolder.categoryIcon.setImageResource(picId);
        viewHolder.accountName.setText(account.getAccoundName());
        viewHolder.accountBalance.setText (String.valueOf(account.getAccountBalance()));

        return convertView;
    }


    private class AccountViewHolder{
        public ImageView categoryIcon;
        public TextView accountName;
        public TextView accountBalance;
    }
}
