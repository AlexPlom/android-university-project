package data;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.mainactivity.R;



import java.util.ArrayList;

public class BmiModelAdapter extends ArrayAdapter<BmiModel> {

    private static class ViewHolder
    {
        TextView height;
        TextView weight;
        TextView result;
    }

    public BmiModelAdapter(@NonNull Context context, ArrayList<BmiModel> bmiModels) {
        super(context, R.layout.listview_item, bmiModels);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        BmiModel model = getItem(position);
        ViewHolder holder;

        if (convertView == null)
        {
            holder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.listview_item,parent,false);
            holder.height =  convertView.findViewById(R.id.textViewHeight);
            holder.weight =  convertView.findViewById(R.id.textViewWeight);
            holder.result =  convertView.findViewById(R.id.textViewResult);

            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.height.setText(String.valueOf(model.getHeight()));
        holder.weight.setText(String.valueOf(model.getWeight()));
        holder.result.setText(String.valueOf(model.getResult()));

        return convertView;
    }
}
